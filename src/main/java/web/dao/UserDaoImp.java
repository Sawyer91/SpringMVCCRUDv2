package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;

import java.util.List;

@Repository

public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List getAllUsers() {
        List<User> list = entityManager.createQuery("From User").getResultList();
        return list;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
