package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.RoleDao;
import web.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;

    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao dao, RoleDao roleDao) {
        this.dao = dao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public Set<Role> getRoles(String[] ids) {
        return roleDao.getRoles(ids);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.readRole();
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

    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }
}
