package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "admin")
    public String users(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("user")
    public String user(){
        return "user";
    }

    @GetMapping(value = "edit")
    public String edit(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "edit")
    public String edit(User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "add")
    public String add(@RequestParam("name") String name, @RequestParam("password") String password) {
        userService.addUser(new User(name, password));
        return "redirect:/admin";
    }

    @PostMapping(value = "delete")
    public String delete(@RequestParam("id") Integer id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}
