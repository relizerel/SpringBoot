package com.spring.controller;


import com.spring.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/**")
public class UsersController {

    @GetMapping("userPage")
    public ModelAndView showUserForm(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("/user/userPage");
        modelAndView.addObject("userData", user);
        return modelAndView;
    }
}
