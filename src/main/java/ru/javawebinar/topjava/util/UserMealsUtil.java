package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Collections.sort(mealList, new Comparator<UserMeal>() {
            public int compare(UserMeal o1, UserMeal o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
        Map exceededMealList = new HashMap<LocalDate, Integer>();
        for (UserMeal meal : mealList){
            int mealCalories = meal.getCalories();
            int savedCalories;
            if (exceededMealList.get(meal.getDateTime().toLocalDate()) == null)
            savedCalories = 0;
            else
            savedCalories = (int)exceededMealList.get(meal.getDateTime().toLocalDate());
            exceededMealList.put(meal.getDateTime().toLocalDate(), savedCalories + mealCalories);
        }
        List<UserMealWithExceed> userMealWithExceedList = new LinkedList<>();
        for (UserMeal meal : mealList) {
        if ((meal.getDateTime().toLocalTime().isAfter(startTime)) && (meal.getDateTime().toLocalTime().isBefore(endTime))) {
            boolean exceeded;
            exceeded = ((int)exceededMealList.get(meal.getDateTime().toLocalDate()) > caloriesPerDay);
            userMealWithExceedList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded));
        }
        }

        return userMealWithExceedList;
    }
}
