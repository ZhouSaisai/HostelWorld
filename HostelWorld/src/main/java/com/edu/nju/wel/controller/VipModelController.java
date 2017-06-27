package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.info.VIPAnalyse;
import com.edu.nju.wel.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zs on 2017/6/27.
 */

@Controller
public class VipModelController {

    @Autowired
    PersonInfoService person;

    @RequestMapping(value = "vip_model")
    public ModelAndView vipAnalyse(HttpServletRequest request, HttpServletResponse response) {
        //构造ModelAndView
        ModelAndView view = new ModelAndView("index");
        //限制进入
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
            PersonInfo temp = (PersonInfo) session.getAttribute("info");
            PersonInfo info = person.getPersonById(temp.getId());
            //刷新session
            session.setAttribute("info",info);
            //获得用户分析数据
           //TODO

            view.setViewName("vipModel");
            view.addObject("info",info);
            view.addObject("vId",temp.getId());
        }
        return view;
    }
}
