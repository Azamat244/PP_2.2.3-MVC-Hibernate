package web.service;


import web.model.User;

import java.util.List;


public interface UserService {

    void save(User user);

    void delete(int id);

    void update(int id, User user);

    List<User> getAll();

    User getById(int id);
}
