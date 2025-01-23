package web.dao;


import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

@Component
public interface UserDao {
    void save(User user);
    void delete(int id);
    void update(int id, User user);
    List<User> getAll();
    User getById(int id);
}
