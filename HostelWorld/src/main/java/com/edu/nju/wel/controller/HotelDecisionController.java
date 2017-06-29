package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.*;
import com.edu.nju.wel.service.AnalyseService;
import com.edu.nju.wel.service.HotelDecisionService;
import com.edu.nju.wel.service.HotelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zs on 2017/6/29.
 */

@Controller
public class HotelDecisionController {
    @Autowired
    HotelInfoService hotelService;

    @Autowired
    AnalyseService analyseService;

    @Autowired
    HotelDecisionService decisionService;


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

    @RequestMapping(value = "get_hotel_line_data",method = RequestMethod.POST)
    @ResponseBody
    public List<MonthAnalyse> getHotelLinepData(HttpServletRequest request, HttpServletResponse response){
        String hId = request.getParameter("hId");
        String timeType = request.getParameter("timeType").substring(4);
        //类型转换
        int hIdInt = Integer.parseInt(hId);
        int type = Integer.parseInt(timeType);
        return decisionService.getHotelLineAnalyse(hIdInt,type);
    }

    @RequestMapping(value = "get_hotel_type_data",method = RequestMethod.POST)
    @ResponseBody
    public List<TypeAnalyse> getHotelTypepData(HttpServletRequest request, HttpServletResponse response){
        String hId = request.getParameter("hId");
        String timeType = request.getParameter("timeType").substring(4);
        //类型转换
        int hIdInt = Integer.parseInt(hId);
        int type = Integer.parseInt(timeType);

        return decisionService.getHotelTypeAnalyse(hIdInt,type);
    }

    @RequestMapping(value = "get_hotel_pie_data",method = RequestMethod.POST)
    @ResponseBody
    public PieInfo getHotelPieData(HttpServletRequest request, HttpServletResponse response){
        String hId = request.getParameter("hId");
        //类型转换
        int hIdInt = Integer.parseInt(hId);

        return decisionService.getHotelPieAnalyse(hIdInt);
    }

    @RequestMapping(value = "get_hotel_add_data",method = RequestMethod.POST)
    @ResponseBody
    public List<AddInfo> getHotelADDData(HttpServletRequest request, HttpServletResponse response){
        String hId = request.getParameter("hId");
        //类型转换
        int hIdInt = Integer.parseInt(hId);
        return decisionService.getHotelADDAnalyse(hIdInt);
    }
}
