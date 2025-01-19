package web.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.models.User;

import java.util.List;

@Component
public interface UserService {

    public void save(User user);
    public void delete(int id);
    public void update(int id, User user);
    public List<User> getAll();
    public User getById(int id);
}
