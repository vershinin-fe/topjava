package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import java.util.Comparator;

import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles("datajpa")
public class SpringDataJpaUserServiceTest extends UserServiceTest{

    @Test
    public void testGetWithMealsMatchMeals() throws Exception {
        User user = service.getWithMeals(UserTestData.ADMIN_ID);
        user.getMeals().sort(Comparator.comparing(Meal::getDateTime).reversed());
        MealTestData.assertMatch(user.getMeals(), ADMIN_WITH_MEALS.getMeals());
    }

    @Test
    public void testGetWithMealsMatchUser() throws Exception {
        assertMatch(service.getWithMeals(UserTestData.ADMIN_ID), ADMIN_WITH_MEALS);
    }
}
