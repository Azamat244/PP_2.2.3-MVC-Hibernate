package web.DAO;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;



@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = getById(id);
        if (user != null) {
            entityManager.remove(user);
        }

    }

    @Override
    @Transactional
    public void update(int id, User user) {
         entityManager.merge(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }

    @Override
    @Transactional
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}
