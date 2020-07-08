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

    private UserService userService;

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
    public String addPost(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user")
    public String userGet(ModelMap modelMap, HttpSession httpSession) {
        modelMap.addAttribute("user", httpSession.getAttribute("user"));
        return "user";
    }

    @GetMapping(value = "admin/edit")
    public String editGet(ModelMap model, @RequestParam("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = userService.getAllRoles();
        roles.forEach(role -> role.setInUser(user.isRoleInUser(role)));
        model.addAttribute("roles", roles);
        return "admin/edit";
    }

    @PostMapping(value = "admin/edit")
    public String editPost(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @PostMapping(value = "admin/delete")
    public String deletePost(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}
