package ru.javawebinar.topjava.dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.javawebinar.topjava.model.Meal;

public class MealsDaoListImpl implements MealsDao {
    private static AtomicInteger counter = new AtomicInteger(1);

    private List<Meal> meals;

    public MealsDaoListImpl() {
        meals = Collections.synchronizedList(new ArrayList<Meal>());

        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void add(Meal meal) {
        meal.setId(counter.getAndIncrement());
        meals.add(meal);
    }

    @Override
    public void delete(int mealId) {
        meals.removeIf(meal -> meal.getId() == mealId);
    }

    @Override
    public void update(Meal meal) {
        for (int i = 0; i < meals.size(); i++) {
            if(meals.get(i).getId() == meal.getId()){
                meals.set(i, meal);
            }
        }
    }

    @Override
    public List<Meal> getAll() {
        return Collections.unmodifiableList(meals);
    }

    @Override
    public Meal getById(int mealId) {
        return meals.stream()
                .filter(fm -> fm.getId() == mealId)
                .findFirst()
                .orElse(null);
    }
}