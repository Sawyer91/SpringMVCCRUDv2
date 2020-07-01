package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String usersGet(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping(value = "edit")
    public String editGet(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "edit")
    public String editPost(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "add")
    public String addPost(@RequestParam("name") String name, @RequestParam("password") String password) {
        userService.addUser(new User(name, password));
        return "redirect:/";
    }

    @PostMapping(value = "delete")
    public String deletePost(@RequestParam("id") Integer id) {
        userService.removeUser(id);
        return "redirect:/";
    }

}
