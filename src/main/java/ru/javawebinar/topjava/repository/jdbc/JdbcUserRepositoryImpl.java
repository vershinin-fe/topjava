package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else {
            jdbcTemplate.update("DELETE FROM user_roles WHERE user_id = user_id");

            int rowsAffected = namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", parameterSource);

            if (rowsAffected == 0) {
                return null;
            }
        }

        if(user.getRoles() != null && !user.getRoles().isEmpty()) {
            insertRoles(new ArrayList<>(user.getRoles()), user.getId());
        }

        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users u JOIN user_roles ur ON u.id = ur.user_id WHERE id=?", getResultList(), id);

        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users u JOIN user_roles ur ON u.id = ur.user_id WHERE email=?", getResultList(), email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users u JOIN user_roles ur ON u.id = ur.user_id ORDER BY u.name, u.email",
                getResultList());

        results.sort(Comparator.comparing(User::getName).thenComparing(User::getEmail));

        return results;
    }

    private ResultSetExtractor<List<User>> getResultList(){
        return rs -> {
            Map<Integer, User> users = new HashMap<>();
            while(rs.next()) {
                Integer userId = rs.getInt("id");
                User user = users.get(userId);
                if (user == null) {
                    user = ROW_MAPPER.mapRow(rs, userId);
                    users.put(userId, user);
                }
                Role role = Role.valueOf(rs.getString("role"));

                if(user.getRoles() == null){
                    Set<Role> roleSet = new HashSet<>();
                    roleSet.add(role);
                    user.setRoles(roleSet);
                } else {
                    user.getRoles().add(role);
                }
            }
            return new ArrayList<>(users.values());
        };
    }

    private void insertRoles(final List<Role> roles, final Integer userId){

        String sql = "INSERT INTO user_roles (user_id, role) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Role role = roles.get(i);
                ps.setInt(1, userId);
                ps.setString(2, role.name());
            }

            @Override
            public int getBatchSize() {
                return roles.size();
            }
        });
    }
}