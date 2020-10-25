package com.spring.controller;

import com.spring.model.User;
import com.spring.service.RoleService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin/**")
public class AdminController {

    final
    UserService userService;
    final
    RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ModelAndView index() {
        List<User> listUsers = userService.listUsers();
        ModelAndView modelAndView = new ModelAndView("admin/users");
        modelAndView.addObject("allUsers", listUsers);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/saveUser/{id}")
    public String updateUser(@PathVariable long id, User user, @RequestParam("roles") Set<String> roles) {
        user.setId(id);
        user.setRoleSet(roleService.getRoleSet(roles));
        userService.updateUser(user);
        return "redirect:admin/users";
    }

    @GetMapping("/editUser/{id}")
    public ModelAndView getUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("admin/editUser");
        modelAndView.addObject("getUser", userService.getUserById(id));
        return modelAndView;
    }


    @GetMapping("/signup")
    public ModelAndView showAdminForm(User user) {
        ModelAndView modelAndView = new ModelAndView("admin/signup");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/addUser")
    public String createUser(User user) {
        user.getRoleSet().add(roleService.getDefaultRole());
        userService.addUser(user);
        return "redirect:/admin/users";
    }
}