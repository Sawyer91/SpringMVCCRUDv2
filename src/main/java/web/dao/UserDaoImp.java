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
        String queryString = "select model from " + User.class.getName() + " model";
        List<User> list = entityManager.createQuery(queryString).getResultList();
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
        User user1 = entityManager.find(User.class, user.getId());
        entityManager.detach(user1);
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        entityManager.merge(user1);
    }

    @Override
    public User getUserById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
