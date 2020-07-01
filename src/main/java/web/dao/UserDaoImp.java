package web.dao;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
//    @Autowired
//    private SessionFactory sessionFactory;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("web.model");

    @Autowired
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    @Override
    public List getAllUsers() {
        EntityManager entityManager = getEntityManager();
        List<User> list = entityManager.createQuery("SELECT name, password from User").getResultList();
//                .getCurrentSession().createQuery("From User");
        return list;
    }

    @Override
    public void addUser(User user) {
//        sessionFactory.getCurrentSession().save(user);
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeUser(long id) {
//        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, id));
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
//        sessionFactory.getCurrentSession().update(user);
        EntityManager entityManager = getEntityManager();
        User user1 = entityManager.find(User.class, user.getId());
        entityManager.detach(user1);
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        entityManager.getTransaction().begin();
        entityManager.merge(user1);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserById(long id) {
//        Query query =  sessionFactory.getCurrentSession().createQuery("From User where id =:pId")
//                .setParameter("pId", id);
//        return (User) query.getResultList();
        EntityManager entityManager = getEntityManager();
        User user = entityManager.find(User.class, id);
        return user;
    }
}
