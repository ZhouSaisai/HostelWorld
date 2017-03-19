package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.service.HotelLoginService;
import com.edu.nju.wel.service.PersonLoginService;
import com.edu.nju.wel.util.exception.NotExistException;
import com.edu.nju.wel.util.exception.WrongPasswordException;
import com.edu.nju.wel.util.helper.ManageAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    HotelLoginService hotelLoginService;

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
        String manager =(String)session.getAttribute("manager");
        if(manager!=null && manager.equals(ManageAccountHelper.CODE)){
            return "welcome-manager";
        }
        String type=(String)session.getAttribute("type");
        if(type.equals("person")){
            PersonInfo info=(PersonInfo)session.getAttribute("info");
            if(info!=null){
                String code = info.getCode();
                if(code.startsWith("v")){
                    return "welcome-person";
                }
            }
        }else if(type.equals("hotel")){
            HotelInfo info1=(HotelInfo) session.getAttribute("info");
            if(info1!=null) {
                String code = info1.getCode();
                if (code.startsWith("h")) {
                    return "welcome-hotel";
                }
            }
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
        //分别登录
        if(type.equals("person")){
            //新建PersonInfo对象
            PersonInfo info=new PersonInfo();
            info.setCode(usercode);
            info.setPassword(password);
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
            session.setAttribute("type","person");
            return personInfo.getId()+";person";
        }else if(type.equals("hotel")){
            //新建PersonInfo对象
            HotelInfo info=new HotelInfo();
            info.setCode(usercode);
            info.setPassword(password);
            //用户登录
            HotelInfo hotelInfo= null;
            try {
                hotelInfo = hotelLoginService.askLogin(info);
            } catch (NotExistException e) {
                return "-1;账号不存在！";
            } catch (WrongPasswordException e) {
                return "-1;密码错误";
            }
            if(hotelInfo==null) {
                return "-1;登录失败";
            }
            if(hotelInfo.getState()==2){
                return "-1;此账号审批未通过！";
            }
            HttpSession session=request.getSession(true);
            session.setAttribute("info",hotelInfo);
            session.setAttribute("type","hotel");
            return hotelInfo.gethId()+";hotel";
        }
        return "-1;登录失败";
    }

    @RequestMapping(value = "ask_hotel_register",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String askRegister(HttpServletRequest request, HttpServletResponse response){
        //构造返回对象
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String level = request.getParameter("level");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");

        HotelInfo info=new HotelInfo();
        info.setName(username);
        info.setPassword(password);
        info.setLevel(Integer.parseInt(level));
        info.setTel(tel);
        info.setAddress(address);
        HotelInfo hotelInfo=hotelLoginService.askRegister(info);
        if(hotelInfo.gethId()==-1) {
            return "-1;注册失败";
        }
        HttpSession session=request.getSession(true);
        session.setAttribute("info",hotelInfo);
        session.setAttribute("sign","new");
        session.setAttribute("type","hotel");
        return hotelInfo.gethId()+";hotel";
    }

    @RequestMapping(value = "ask_vip_register",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String askVIPRegister(HttpServletRequest request, HttpServletResponse response){
        //构造返回对象
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PersonInfo info=new PersonInfo();
        info.setName(username);
        info.setPassword(password);
        PersonInfo personInfo=personLoginService.askRegister(info);
        if(personInfo.getId()==-1) {
            return "-1;注册失败";
        }
        HttpSession session=request.getSession(true);
        session.setAttribute("info",personInfo);
        session.setAttribute("sign","new");
        session.setAttribute("type","person");
//            System.out.println(personInfo.toString());
        return personInfo.getId()+";person";
    }

    @RequestMapping(value = "ask_loginOut")
    public String askLoginOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("info", null);
        request.getSession().setAttribute("manager", null);
        request.getSession().setAttribute("sign", null);
        request.getSession().setAttribute("type",null);
        return "index";
    }
}
