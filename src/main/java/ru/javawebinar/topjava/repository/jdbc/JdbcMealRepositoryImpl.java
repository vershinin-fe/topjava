package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (meal.isNew()) {
            String sqlQuery = "INSERT INTO meals (date_time, description, calories, user_id) VALUES (?, ?, ?, ?);";
            jdbcTemplate.update(connection -> {
                        PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                        ps.setTimestamp(1, new Timestamp(meal.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli()));
                        ps.setString(2, meal.getDescription());
                        ps.setInt(3, meal.getCalories());
                        ps.setInt(4, userId);
                        return ps; }
                    , keyHolder);
            int newKey = (int) keyHolder.getKeys().get("id");
            meal.setId(newKey);
        } else {
            jdbcTemplate.update("UPDATE meals SET date_time=?, description=?, calories=? WHERE id=? AND user_id=?;",
                    new Timestamp(meal.getDateTime().toInstant(ZoneOffset.UTC).toEpochMilli()),
                    meal.getDescription(),
                    meal.getCalories(),
                    meal.getId(),
                    userId);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> users = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? AND date_time>=? AND date_time<=?" +
                " ORDER BY date_time DESC", ROW_MAPPER, userId, startDate, endDate);
    }
}