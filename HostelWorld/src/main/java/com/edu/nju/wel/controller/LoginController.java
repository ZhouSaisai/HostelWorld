package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.service.PersonLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by zs on 2017/3/13.
 */
@Controller
public class LoginController {
    @Autowired
    PersonLoginService personLoginService;

    /**
     * 进入首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }

    @RequestMapping(value = "person_login")
    public String personLogin(HttpServletRequest request, HttpServletResponse response){
        return "personLogin";
    }
    @RequestMapping(value = "hotel_login")
    public String hotelLogin(HttpServletRequest request, HttpServletResponse response){
        return "hotelLogin";
    }


    @RequestMapping(value = "ask_login")
    public String askLogin(HttpServletRequest request, HttpServletResponse response){
        String usercode = request.getParameter("usercode");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        System.out.println(usercode+password+type);
        return "login";
    }

    @RequestMapping(value = "ask_register",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String askRegister(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        System.out.println(username+password+type);
        PersonInfo info=new PersonInfo();
        info.setName(username);
        info.setPassword(password);
        if(type.equals("person")){
            int id=personLoginService.askRegister(info);
            if(id==-1) {
                return "注册失败";
            }
            HttpSession session=request.getSession(true);
            session.setAttribute("username",username);
            session.setAttribute("id",id);
            return id+"";
        }else if(type.equals("hotel")){

        }
        return "注册失败";
    }

    @RequestMapping(value = "ask_loginOut")
    public String askLoginOut(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}
