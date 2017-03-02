package com.edu.nju.wel.controller;

import com.edu.nju.wel.model.User;
import com.edu.nju.wel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zs on 2017/3/2.
 */

@Controller
public class UserController {
    @Resource(name = "UserService")
    UserService userService;

    @RequestMapping(value = "/users")
    public ModelAndView getUsers(HttpServletRequest request, HttpServletResponse response){
        List<User> list = userService.find();
        ModelAndView view = new ModelAndView("user");
        view.addObject("list",list);
        return view;
    }

}
