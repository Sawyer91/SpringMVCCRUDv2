package web.dao;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    private EntityManager entityManager;

    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query= entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public List<Role> readRole() {
        TypedQuery<Role> query= entityManager.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Set<Role> getRoles(String[] ids) {
        TypedQuery<Role> query= entityManager.createQuery("from Role where id = :id", Role.class);
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ids).forEach(roleId -> {query.setParameter("id", Long.parseLong(roleId)); roles.add(query.getSingleResult());});
        return roles;
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

    @Override
    public UserDetails findByUsername(String login) {
       User user = (User) entityManager.createQuery("from User where name = :login")
               .setParameter("login", login)
               .getSingleResult();
       return user;
    }
}
