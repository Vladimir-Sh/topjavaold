package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.exception.*;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMealService service;

    public UserMeal save(UserMeal userMeal){
        userMeal.setId(null);
        LOG.info("create " + userMeal);
        return service.save(userMeal);

    }

    public void delete(int id){
        LOG.info("delete " + id);
        ExceptionUtil.checkNotFound((LoggedUser.id() == service.get(id).getUserId()), String.valueOf(id));
        service.delete(id);

    }

    public UserMeal get(int id){
        ExceptionUtil.checkNotFound((LoggedUser.id() == service.get(id).getUserId()), String.valueOf(id));
        LOG.info("get " + id);
        return service.get(id);
    }

    public Collection<UserMeal> getAll(){
    LOG.info("getAll");
    return service.getAll().stream()
            .filter(m -> m.getUserId() == LoggedUser.id())
            .collect(Collectors.toList());
    }
}
