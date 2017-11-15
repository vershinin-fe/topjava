package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    List<Meal> getAll(int userId);

    List<Meal> getFiltered(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime);

    Meal get(int id, int userId);

    Meal create(Meal meal, int userId);

    void delete(int id, int userId);

    void update(Meal meal, int userId);
}