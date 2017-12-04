package ru.javawebinar.topjava.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

@Service
@Profile("datajpa")
public class DataJpaMealServiceImpl extends MealServiceImpl{
    public DataJpaMealServiceImpl(MealRepository repository) {
        super(repository);
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        Meal meal = ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);
        meal.getUser().getRoles();

        return meal;
    }
}
