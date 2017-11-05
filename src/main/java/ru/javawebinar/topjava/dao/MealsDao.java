package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsDao {
    void addMeal(Meal meal);

    void deleteMeal(int mealId);

    void updateMeal(int id, Meal meal);

    List<Meal> getAllMeals();

    Meal getMealById(int mealId);
}
