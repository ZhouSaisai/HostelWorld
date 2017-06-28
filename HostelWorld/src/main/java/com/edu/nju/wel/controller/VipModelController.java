package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.MapAnalyse;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.info.TimeAnalyse;
import com.edu.nju.wel.info.VIPAnalyse;
import com.edu.nju.wel.service.AnalyseService;
import com.edu.nju.wel.service.PersonInfoService;
import com.edu.nju.wel.service.VIPModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
 * Created by zs on 2017/6/27.
 */

@Controller
public class VipModelController {

    @Autowired
    PersonInfoService person;
    @Autowired
    VIPModelService model;
    @Autowired
    AnalyseService analyseService;

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
            VIPAnalyse va = analyseService.getVipAnalyse(temp.getId());

            List<Double> rates = model.getPoints(temp.getId());

            view.setViewName("vipModel");
            view.addObject("info",info);
            view.addObject("va",va);
            view.addObject("rates",rates);
            view.addObject("vId",temp.getId());
        }
        return view;
    }

    @RequestMapping(value = "vip_map_data",method = RequestMethod.POST)
    @ResponseBody
    public List<MapAnalyse> getVIPMapData(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("vId");
        //类型转换
        int vId = Integer.parseInt(id);
        return model.getMapAnalyse(vId);
    }


    @RequestMapping(value = "vip_small_map_data",method = RequestMethod.POST)
    @ResponseBody
    public List<MapAnalyse> getVIPSmallMapData(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("vId");
        String pid = request.getParameter("pId");
        //类型转换
        int vId = Integer.parseInt(id);
        int pId = Integer.parseInt(pid);

        return model.getSmallMapAnalyse(vId,pId);
    }

    @RequestMapping(value = "vip_time_data",method = RequestMethod.POST)
    @ResponseBody
    public List<TimeAnalyse> getVIPTimeData(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("vId");
        String pro = request.getParameter("pro");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String timeType = request.getParameter("timeType").substring(4);

        //类型转换
        int vId = Integer.parseInt(id);
        int type = Integer.parseInt(timeType);

        return model.getTimeAnalyse(vId,pro,city,area,type);
    }
}
