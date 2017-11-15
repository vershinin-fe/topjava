package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal, int userId);

    //false if not exist for userId
    boolean delete(int id, int userId);

    //null if not exist for userId
    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getFiltered(int userId, LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime);
}
