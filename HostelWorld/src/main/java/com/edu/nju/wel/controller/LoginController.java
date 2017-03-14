package com.edu.nju.wel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zs on 2017/3/13.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }

    @RequestMapping(value = "ask_login")
    public String askLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }

    @RequestMapping(value = "ask_register")
    public String askRegister(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }

    @RequestMapping(value = "ask_loginOut")
    public String askLoginOut(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}
