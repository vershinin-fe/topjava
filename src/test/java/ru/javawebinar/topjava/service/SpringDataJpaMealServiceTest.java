package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.MealTestData.MEAL7;
import static ru.javawebinar.topjava.MealTestData.assertMatchWithUser;

@ActiveProfiles("datajpa")
public class SpringDataJpaMealServiceTest extends MealServiceTest{
    @Test
    public void testGetWithUser() throws Exception {
        assertMatchWithUser(service.getWithUser(100002, 100000), MEAL7);
    }
}
