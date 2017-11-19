package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test(expected = NotFoundException.class)
    public void get() throws Exception {
        service.get(100010, 100000);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(100010, 100000);
    }

    @Test(expected = NotFoundException.class)
    public void update() throws Exception {
        Meal meal = new Meal(100004, LocalDateTime.of(2015, Month.MAY,30, 20, 0,0), "Ужин", 510);
        service.update(meal, 100001);
    }

//    @Test
//    public void getBetweenDateTimes() throws Exception {
//    }
//
//    @Test
//    public void getAll() throws Exception {
//    }
//
//    @Test
//    public void create() throws Exception {
//    }

}