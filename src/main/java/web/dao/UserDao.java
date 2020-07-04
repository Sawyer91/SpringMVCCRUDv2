package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void addUser(User user);

    public void removeUser(long id);

    public void updateUser(User user);

    public User getUserById(long id);

//    public UserDetails findByUsername(String login);
}
