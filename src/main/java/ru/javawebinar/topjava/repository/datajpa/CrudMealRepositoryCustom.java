package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.util.Optional;

@Transactional
public interface CrudMealRepositoryCustom {

    @Modifying
    Optional<Meal> save(Meal meal, int userId);
}
