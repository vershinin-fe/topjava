package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoImpl;
import ru.javawebinar.topjava.model.Meal;
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
    private static final String TO_MEALS_LIST = "WEB-INF/views/meals.jsp";
    private static final String TO_MEAL_FORM = "WEB-INF/views/meal.jsp";
    private MealsDao mealsDao = new MealsDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String forwardPath;
        String action = request.getParameter("action");
        if(action != null){
            action = action.toLowerCase();
        } else {
            action = "";
        }

        switch (action){
            case "edit":
                forwardPath = TO_MEAL_FORM;
                int mealForEditId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealsDao.getMealById(mealForEditId);
                request.setAttribute("meal", meal);
                break;
            case "insert":
                forwardPath = TO_MEAL_FORM;
                break;
            case "delete":
                int mealForDeleteId = Integer.parseInt(request.getParameter("mealId"));
                mealsDao.deleteMeal(mealForDeleteId);
            default:
                forwardPath = TO_MEALS_LIST;
                request.setAttribute("mealList", MealsUtil.getAllWithExceeded(mealsDao.getAllMeals(), MAX_CALORIES_PER_DAY));
        }

        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String
//        User user = new User();
//        user.setFirstName(request.getParameter("firstName"));
//        user.setLastName(request.getParameter("lastName"));
//        try {
//            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
//            user.setDob(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        user.setEmail(request.getParameter("email"));
//        String userid = request.getParameter("userid");
//        if(userid == null || userid.isEmpty())
//        {
//            dao.addUser(user);
//        }
//        else
//        {
//            user.setUserid(Integer.parseInt(userid));
//            dao.updateUser(user);
//        }

        request.setAttribute("mealList", MealsUtil.getAllWithExceeded(mealsDao.getAllMeals(), MAX_CALORIES_PER_DAY));
        request.getRequestDispatcher(TO_MEALS_LIST).forward(request, response);
    }
}
