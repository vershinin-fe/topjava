package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDaoMapImpl implements MealsDao{
    private static MealsDao instance;
    private static AtomicInteger counter = new AtomicInteger(1);

    private Map<Integer, Meal> meals;

    private MealsDaoMapImpl() {
        meals = new ConcurrentHashMap<>();

        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public static synchronized MealsDao getInstance(){
        if(instance == null){
            instance = new MealsDaoMapImpl();
        }

        return instance;
    }

    @Override
    public void add(Meal meal) {
        meal.setId(counter.getAndIncrement());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int mealId) {
        meals.remove(mealId);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getById(int mealId) {
        return meals.get(mealId);
    }
}
