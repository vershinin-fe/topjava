package ru.javawebinar.topjava.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
@Profile("datajpa")
public class DataJpaUserServiceImpl extends UserServiceImpl{
    public DataJpaUserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User getWithMeals(int id) throws NotFoundException{
        User user = checkNotFoundWithId(repository.get(id), id);
        user.getMeals().size();

        return user;
    }
}
