package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.*;
import com.edu.nju.wel.model.HotelCash;
import com.edu.nju.wel.service.AnalyseService;
import com.edu.nju.wel.service.CashService;
import com.edu.nju.wel.service.HotelInfoService;
import com.edu.nju.wel.service.PersonInfoService;
import com.edu.nju.wel.util.helper.ManageAccountHelper;
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
 * Created by zs on 2017/3/24.
 */
@Controller
public class AnalyseController {
    @Autowired
    PersonInfoService person;
    @Autowired
    HotelInfoService hotelService;
    @Autowired
    AnalyseService analyseService;
    @Autowired
    CashService cashService;

    @RequestMapping(value = "vip_analyse")
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
            VIPAnalyse va = analyseService.getVipAnalyse(temp.getId());

            view.setViewName("vipAnalyse");
            view.addObject("info",info);
            view.addObject("va",va);
            view.addObject("vId",temp.getId());
        }
        return view;
    }

    @RequestMapping(value = "hotel_analyse")
    public ModelAndView hotelAnalyse(HttpServletRequest request, HttpServletResponse response) {
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
            List<HotelCash> cashs = cashService.getCashByHId(temp.gethId());
            view.setViewName("hotelAnalyse");
            view.addObject("info",info);
            view.addObject("ha",ha);
            view.addObject("cashs",cashs);
        }
        return view;
    }

    @RequestMapping(value = "manage_analyse")
    public ModelAndView manageAnalyse(HttpServletRequest request, HttpServletResponse response) {
        //构造ModelAndView
        ModelAndView view = new ModelAndView("index");
        //
        HttpSession session = request.getSession(false);
        if(session==null){
            try {
                response.sendRedirect("/HotelWorld/welcome");
            } catch (IOException e) {
                e.printStackTrace();
                return view;
            }
        }else{
            String manager = (String)session.getAttribute("manager");
            if(manager==null || !manager.equals(ManageAccountHelper.CODE)){
                try {
                    response.sendRedirect("/HotelWorld/welcome");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ManageAnalyse ma = analyseService.getManageAnalyse();
            view.setViewName("manageAnalyse");
            view.addObject("ma",ma);
        }
        return view;
    }

    @RequestMapping(value = "get_line_data",method = RequestMethod.POST)
    @ResponseBody
    public List<MonthAnalyse> getLineData(HttpServletRequest request, HttpServletResponse response) {
        String Id = request.getParameter("id");
        String t = request.getParameter("type");
        //类型转换
        int id = Integer.parseInt(Id);
        int type = Integer.parseInt(t);
        switch (type){
            case 1:
                return analyseService.getVipAnalyse(id).getMonths();
            case 2:
                return analyseService.getHotelAnalyse(id).getMonths();
            default:
                return analyseService.getManageAnalyse().getMonths();
        }
    }
}
