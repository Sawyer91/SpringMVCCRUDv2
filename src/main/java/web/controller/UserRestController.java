package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/rest/*")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "add")
    public void addUser(@RequestParam String name, String password, String role) {
        List<Role> roleId = roleService.getAllRoles();
        String id = null;
        for (Role r: roleId) {
            if (r.getRole().equals(role)) {
                id = r.getId().toString();
            }
        }
        userService.addUser(new User(name, password, roleService.getRoles(id)));
    }

    @GetMapping(value = "getUser")
    public ResponseEntity<List<User>> getUser(HttpSession session) {
        List<User> userList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        userList.add(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("update")
    public void updateUser(@RequestParam Long id, String name,
                           String password, String role) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setPassword(password);
        if(!role.isEmpty()) {
            List<Role> roleId = roleService.getAllRoles();
            for (Role r: roleId) {
                if (r.getRole().equals(role)) {
                     user.setRole(roleService.getRoles(r.getId().toString()));
                }
            }
        }
        userService.updateUser(user);
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam("id") Long id) {
        userService.removeUser(id);
    }

}
