package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();

        mealList.stream().forEach((meal) -> map.put(meal.getDateTime().toLocalDate(),
                map.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (a, b) -> a + b)));

        List<UserMealWithExceed> resultList = new ArrayList<>();
        mealList.stream().filter(userMeal -> userMeal.getDateTime().toLocalTime().isAfter(startTime)
                && userMeal.getDateTime().toLocalTime().isBefore(endTime)).forEach((meal) ->
                resultList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(),
                        meal.getCalories(), map.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)));

        for (UserMealWithExceed meal : resultList) {
            System.out.println(meal.exceed);
        }

        return resultList;
    }
}
