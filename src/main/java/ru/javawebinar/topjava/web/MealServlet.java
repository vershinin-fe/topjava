package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.data.FakeData;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet{
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<MealWithExceed> mealWithExceededList = MealsUtil.getFilteredWithExceeded(FakeData.FAKE_MEALS, LocalTime.MIN,
                LocalTime.MAX, FakeData.MAX_CALORIES_PER_DAY);

        request.setAttribute("mealList", mealWithExceededList);
        request.getRequestDispatcher("WEB-INF/views/meals.jsp").forward(request, response);
    }
}
