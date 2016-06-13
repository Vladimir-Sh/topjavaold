package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

  /*  {
        UsersUtil.USER_LIST.forEach(this::save);
    }
*/
    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }


    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        repository.remove(id);
        return true;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }


    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repository.values().stream().sorted((u1, u2) -> -u2.getName().compareTo(u1.getName())).collect(Collectors.toList());
    }


    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        if (repository.values().stream().filter(email::equals).findFirst().isPresent())
        return repository.values().stream().filter(email::equals).findFirst().get();
        else
            return null;
    }
}
