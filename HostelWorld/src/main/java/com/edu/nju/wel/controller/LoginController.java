package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.service.PersonLoginService;
import com.edu.nju.wel.util.exception.NotExistException;
import com.edu.nju.wel.util.exception.WrongPasswordException;
import com.edu.nju.wel.util.helper.ManageAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession(false);
        if(session==null){
            return "index";
        }
        PersonInfo info=(PersonInfo)session.getAttribute("info");
        if(info==null){
            return "index";
        }
        System.out.println(info.toString());
        String code = info.getCode();
        if(code.startsWith("v")){
            return "welcome-person";
        }else if(code.startsWith("h")){
            return "welcome-hotel";
        }
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


    @RequestMapping(value = "ask_login",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String askLogin(HttpServletRequest request, HttpServletResponse response){
        String usercode = request.getParameter("usercode");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
//        System.out.println(usercode+password+type);
        //新建PersonInfo对象
        PersonInfo info=new PersonInfo();
        info.setCode(usercode);
        info.setPassword(password);
        //分别登录
        if(type.equals("person")){
            //经理登录
            if(usercode.equals(ManageAccountHelper.CODE)){
                if(password.equals(ManageAccountHelper.PASSWORD)){
                    HttpSession session=request.getSession(true);
                    session.setAttribute("manager",usercode);
                    return usercode+";manager";
                }
                return "-1;密码错误";
            }
            //用户登录
            PersonInfo personInfo= null;
            try {
                personInfo = personLoginService.askLogin(info);
            } catch (NotExistException e) {
                return "-1;账号不存在！";
            } catch (WrongPasswordException e) {
                return "-1;密码错误";
            }
            if(personInfo==null) {
                return "-1;登录失败";
            }
            if(personInfo.getState()==2){
                return "-1;此账号已停用！";
            }
            HttpSession session=request.getSession(true);
            session.setAttribute("info",personInfo);
            return personInfo.getId()+";person";
        }else if(type.equals("hotel")){

        }
        return "-1;登录失败";
    }

    @RequestMapping(value = "ask_register",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String askRegister(HttpServletRequest request, HttpServletResponse response){
        //构造返回对象
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        PersonInfo info=new PersonInfo();
        info.setName(username);
        info.setPassword(password);
        if(type.equals("person")){
            PersonInfo personInfo=personLoginService.askRegister(info);
            if(personInfo.getId()==-1) {
                return "-1;注册失败";
            }
            HttpSession session=request.getSession(true);
            session.setAttribute("info",personInfo);
            session.setAttribute("sign","new");
//            System.out.println(personInfo.toString());
            return personInfo.getId()+";person";
        }else if(type.equals("hotel")){

        }
        return "-1;注册失败";
    }

    @RequestMapping(value = "ask_loginOut")
    public String askLoginOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("info", null);
        request.getSession().setAttribute("manager", null);
        request.getSession().setAttribute("sign", null);
        return "index";
    }
}
