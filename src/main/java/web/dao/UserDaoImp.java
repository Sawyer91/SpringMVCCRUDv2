package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.*;

import java.util.List;


@Repository(value = "userDaoImp")
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query= entityManager.createQuery("from User", User.class);
        return query.getResultList();
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
        return entityManager.find(User.class, id);
    }

    @Override
    public UserDetails findByUsername(String login) {
        return entityManager.createQuery("from User where name = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }
}
