package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.PersonInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 个人用户的个人空间页对应的Controller
 * Created by zs on 2017/3/14.
 */
@Controller
public class PersonInfoController {
    /**
     * 进入个空间页
     * 第一次进入，即注册完成时显示识别码提示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "person_zone")
    public ModelAndView welcome(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        //构造ModelAndView
        ModelAndView view = new ModelAndView("index");
        //TODO 限制进入
        HttpSession session = request.getSession(false);
        if(session==null){
            try {
                response.sendRedirect("/HotelWorld/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return view;
            }
            return view;
        }else{
            PersonInfo info = (PersonInfo) session.getAttribute("info");
            if(info==null || (!id.equals(info.getId()+""))){
                return view;
            }
            view.setViewName("personInfo");
            view.addObject("info",info);
            String sign=(String) session.getAttribute("sign");
            if(sign!=null && sign.equals("new")){
                session.setAttribute("sign",null);
                view.addObject("sign","new");
            }else {
                view.addObject("sign","old");
            }
        }
        return view;
    }
}
