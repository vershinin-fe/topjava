package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
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
    private static final int MAX_CALORIES_PER_DAY = 2000;
    private MealsDao mealsDao = new MealsDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        List<MealWithExceed> mealWithExceededList = MealsUtil.getFilteredWithExceeded(mealsDao.getAllMeals(), LocalTime.MIN,
                LocalTime.MAX, MAX_CALORIES_PER_DAY);

        request.setAttribute("mealList", mealWithExceededList);
        request.getRequestDispatcher("WEB-INF/views/meals.jsp").forward(request, response);
    }
}
