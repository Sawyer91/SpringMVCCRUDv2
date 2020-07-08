package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    public List<User> getAllUsers();

    List<Role> readRole();

    Set<Role> getRoles(String[] ids);

    public void addUser(User user);

    public void removeUser(long id);

    public void updateUser(User user);

    public User getUserById(long id);

    public UserDetails findByUsername(String login);
}
