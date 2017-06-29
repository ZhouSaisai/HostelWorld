package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelAnalyse;
import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.HotelCash;
import com.edu.nju.wel.service.AnalyseService;
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
 * Created by zs on 2017/6/29.
 */

@Controller
public class HotelDecisionController {
    @Autowired
    HotelInfoService hotelService;

    @Autowired
    AnalyseService analyseService;


    @RequestMapping(value = "hotel_decision")
    public ModelAndView HotelDecision(HttpServletRequest request, HttpServletResponse response) {
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
            HotelInfo temp = (HotelInfo) session.getAttribute("info");
            HotelInfo info = hotelService.getHotelById(temp.gethId());
            //刷新session
            session.setAttribute("info",info);
            HotelAnalyse ha = analyseService.getHotelAnalyse(temp.gethId());
            view.setViewName("hotelDecision");
            view.addObject("info",info);
            view.addObject("ha",ha);
        }
        return view;
    }
}
