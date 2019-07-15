package com.codegym.cms.controller;

import com.codegym.cms.model.User;
import com.codegym.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/")
    public ModelAndView showForm(){
        ModelAndView modelAndView=new ModelAndView("index");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView result(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView=new ModelAndView("index");
            return modelAndView;
        } else {
            userService.save(user);
            ModelAndView modelAndView=new ModelAndView("result");
            return modelAndView;
        }
    }

    @GetMapping("/users")
    public ModelAndView listUser(){
        ModelAndView modelAndView=new ModelAndView("list");
        Iterable<User> users=userService.findAll();
        modelAndView.addObject("users",users);
        return modelAndView;
    }
}