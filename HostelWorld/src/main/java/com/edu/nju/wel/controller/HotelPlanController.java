package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.service.HotelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zs on 2017/3/15.
 */
@Controller
public class HotelPlanController {
    @Autowired
    HotelInfoService hotel;

    @RequestMapping(value = "hotel_plan")
    public ModelAndView hotelPlan(HttpServletRequest request, HttpServletResponse response) {
        //构造ModelAndView
        ModelAndView view = new ModelAndView("index");
        //限制进入
        HttpSession session = request.getSession(false);
        if (session == null) {
            try {
                response.sendRedirect("/HotelWorld/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return view;
            }
            return view;
        } else {
            HotelInfo temp = (HotelInfo) session.getAttribute("info");
            if (temp == null) {
                return view;
            }
            HotelInfo info = hotel.getHotelById(temp.gethId());
            //设置modelAndView
            view.setViewName("hotelPlan");
            view.addObject("info", info);
        }
        return view;
    }

}
