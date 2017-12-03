package ru.javawebinar.topjava.repository.datajpa;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class CrudMealRepositoryCustomImpl implements CrudMealRepositoryCustom{

    @PersistenceContext
    public EntityManager em;

    @Override
    public Optional<Meal> save(Meal meal, int userId) {
        meal.setUser(em.getReference(User.class, userId));

        if(!meal.isNew()){
            Meal actualMeal = em.find(Meal.class, meal.getId());

            if(actualMeal == null || actualMeal.getUser().getId() != userId){
                return Optional.empty();
            } else {
                return Optional.of(em.merge(meal));
            }
        }

        em.persist(meal);

        return Optional.of(meal);
    }
}
