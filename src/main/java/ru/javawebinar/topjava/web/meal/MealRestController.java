package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getFiltered(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        log.info("getFiltered (From: {} {}; To: {} {})", fromDate, fromTime, toDate, toTime);

        return MealsUtil.getFilteredWithExceeded(
                service.getAll(AuthorizedUser.id()),
                fromDate == null ? LocalDate.MIN : fromDate,
                toDate == null ? LocalDate.MAX : toDate,
                fromTime == null ? LocalTime.MIN : fromTime,
                toTime == null ? LocalTime.MAX : toTime,
                AuthorizedUser.getCaloriesPerDay()
                );
    }

    public MealWithExceed get(int id) {
        log.info("get {}", id);
        return MealsUtil.createWithExceed(service.get(id, AuthorizedUser.id()), false);
    }

    public MealWithExceed create(MealWithExceed mealWithExceed) {
        Meal meal = MealsUtil.createWithoutExceed(mealWithExceed);
        log.info("create {}", meal);
        checkNew(meal);
        return MealsUtil.createWithExceed(service.create(meal, AuthorizedUser.id()), false);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(MealWithExceed mealWithExceed, int id) {
        Meal meal = MealsUtil.createWithoutExceed(mealWithExceed);
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.id());
    }
}