package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.javawebinar.topjava.model.Meal;

public class MealsDaoImpl implements MealsDao {

    private List<Meal> meals;

    public MealsDaoImpl() {
        meals = new ArrayList<>();

        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        meals.removeIf(meal -> meal.getId() == mealId);
    }

    @Override
    public void updateMeal(Meal meal) {
        deleteMeal(meal.getId());
        addMeal(meal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return Collections.unmodifiableList(meals);
    }

    @Override
    public Meal getMealById(int mealId) {
        return meals.stream()
                .filter(fm -> fm.getId() == mealId)
                .findFirst()
                .orElse(null);
    }
}