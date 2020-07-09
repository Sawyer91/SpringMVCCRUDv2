package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "admin")
    public String users(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", userService.getAllRoles());
        return "admin";
    }

    @PostMapping(value = "admin/add")
    public String add(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
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
        List<Role> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        return "admin/edit";
    }

    @PostMapping(value = "admin/edit")
    public String edit(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}
