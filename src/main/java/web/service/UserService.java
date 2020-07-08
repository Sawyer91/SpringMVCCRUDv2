package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> getAllUsers();

    public boolean addUser(User user);

    public void removeUser(long id);

    public void updateUser(User user);

    public User getUserById(long id);

    public Set<Role> getRoles(String[] ids);

    public List<Role> getAllRoles();
}
