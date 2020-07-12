package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "admin")
    public String users(ModelMap model, HttpSession httpSession) {
        model.addAttribute("user", httpSession.getAttribute("user"));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping(value = "admin/add")
    public String add(User user, String[] roleIds) {
        user.setRole(roleService.getRoles(roleIds));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user")
    public String user(ModelMap modelMap, HttpSession httpSession) {
        modelMap.addAttribute("user", httpSession.getAttribute("user"));
        return "user";
    }

    @GetMapping(value = "admin/edit")
    public String edit(ModelMap model, @RequestParam("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "admin";
    }

    @PostMapping(value = "admin/edit")
    public String edit(User user, String[] roleIds) {
        user.setRole(roleService.getRoles(roleIds));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}
