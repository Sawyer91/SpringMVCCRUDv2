package web.service;


import web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserDao dao;

    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Transactional(readOnly = true)
    public Set<Role> getRoles(String[] ids) {
        return dao.getRoles(ids);
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return dao.readRole();
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        } else {
            dao.addUser(user);
            return true;
        }
    }

    @Transactional
    @Override
    public void removeUser(long id) {
        dao.removeUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }
}
