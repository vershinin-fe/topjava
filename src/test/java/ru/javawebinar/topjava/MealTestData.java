package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final List<Meal> USER_MEALS = new ArrayList<>();
    public static final List<Meal> ADMIN_MEALS = new ArrayList<>();
    public static final Map<Integer, List<Meal>> MEALS = new HashMap<>();

    static {
        USER_MEALS.add(new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY,31, 20, 0,0), "Ужин", 510));
        USER_MEALS.add(new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY,31, 13, 0,0), "Обед", 500));
        USER_MEALS.add(new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY,31, 10, 0,0), "Завтрак", 1000));

        USER_MEALS.add(new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY,30, 20, 0,0), "Ужин", 500));
        USER_MEALS.add(new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY,30, 13, 0,0), "Обед", 1000));
        USER_MEALS.add(new Meal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY,30, 10, 0,0), "Завтрак", 500));

        ADMIN_MEALS.add(new Meal(START_SEQ + 10, LocalDateTime.of(2015, Month.MAY,25, 20, 0,0), "Ужин", 500));
        ADMIN_MEALS.add(new Meal(START_SEQ + 9, LocalDateTime.of(2015, Month.MAY,25, 13, 0,0), "Обед", 1001));
        ADMIN_MEALS.add(new Meal(START_SEQ + 8, LocalDateTime.of(2015, Month.MAY,25, 10, 0,0), "Завтрак", 500));

        MEALS.put(100000, USER_MEALS);
        MEALS.put(100001, ADMIN_MEALS);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
