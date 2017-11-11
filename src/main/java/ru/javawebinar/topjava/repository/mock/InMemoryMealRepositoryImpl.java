package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS_ALICE.forEach(m -> this.save(m, UsersUtil.ALICES_ID));
        MealsUtil.MEALS_SARAH.forEach(m -> this.save(m, UsersUtil.SARAHS_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if(!repository.containsKey(userId)) {
            repository.put(userId, new HashMap<>());
        }

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }

        repository.get(userId).put(meal.getId(), meal);

        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        boolean result = repository.get(userId).remove(id) != null;

        if(repository.get(userId).size() < 1)
            repository.remove(userId);

        return result;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return Collections.unmodifiableList(repository.get(userId).values().stream().
                sorted(Comparator.comparing(Meal::getDateTime).reversed()).
                collect(Collectors.toList()));
    }
}

