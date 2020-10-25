package com.spring.controller;

import com.spring.model.User;
import com.spring.service.RoleService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reg")
public class RegController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping
    public ModelAndView showRegistrationForm(User user) {
        ModelAndView modelAndView = new ModelAndView("reg");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/newUser")
    public String createUser(User user) {
        user.getRoleSet().add(roleService.getDefaultRole());
        userService.addUser(user);
        return "redirect:/index";
    }

}
