package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.javawebinar.topjava.model.Meal;

public class MealsDao {

    private List<Meal> meals;

    public MealsDao() {
        meals = new ArrayList<>();

        meals.add(new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public void addMeal(Meal meal) {
        meals.add(new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    public void deleteMeal(int mealId) {
        meals.removeIf(meal -> meal.getId() == mealId);
    }

    public void updateMeal(Meal meal) {
        deleteMeal(meal.getId());
        addMeal(meal);
    }

    public List<Meal> getAllMeals() {
        return Collections.unmodifiableList(meals);
    }

    public Meal getMealById(int mealId) {
        return meals.stream()
                .filter(fm -> fm.getId() == mealId)
                .findFirst()
                .map(mm -> new Meal(mm.getId(), mm.getDateTime(), mm.getDescription(), mm.getCalories()))
                .orElse(null);
    }
}