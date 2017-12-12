package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class MealController extends AbstractMealController {

    public MealController(MealService service) {
        super(service);
    }

    @GetMapping("")
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/delete")
    public String deleteMeal(@RequestParam("id") int id) {
        delete(id);
        return "redirect:/meals";
    }

    @PostMapping("")
    public String setFilterBetween(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

        request.setAttribute("meals", getBetween(startDate, startTime, endDate, endTime));

        return "meals";
    }

    @GetMapping("/create")
    public String toCreateForm(HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);

        return "mealForm";
    }

    @GetMapping("/update")
    public String toUpdateForm(@RequestParam("id") int id, HttpServletRequest request) {
        final Meal meal = get(id);
        request.setAttribute("meal", meal);

        return "mealForm";
    }

    @PostMapping("/save")
    public String saveMeal(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
            LocalDateTime.parse(request.getParameter("dateTime")),
            request.getParameter("description"),
            Integer.parseInt(request.getParameter("calories"))
        );

        if (request.getParameter("id").isEmpty()) {
            create(meal);
        } else {
            update(meal, Integer.parseInt(request.getParameter("id")));
        }

        return "redirect:/meals";
    }
}