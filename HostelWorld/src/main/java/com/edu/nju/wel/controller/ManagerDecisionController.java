package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.ManageAnalyse;
import com.edu.nju.wel.info.MapAnalyse;
import com.edu.nju.wel.info.TimeAnalyse;
import com.edu.nju.wel.service.AnalyseService;
import com.edu.nju.wel.service.VIPModelService;
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
 * Created by zs on 2017/6/29.
 */

@Controller
public class ManagerDecisionController {

    @Autowired
    AnalyseService analyseService;
    @Autowired
    VIPModelService model;

    @RequestMapping(value = "manage_decision")
    public ModelAndView managerDecision(HttpServletRequest request, HttpServletResponse response) {
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

            view.setViewName("manageDecision");
            view.addObject("ma",ma);
        }
        return view;
    }

    @RequestMapping(value = "manage_map_data",method = RequestMethod.POST)
    @ResponseBody
    public List<MapAnalyse> getManageMapData(HttpServletRequest request, HttpServletResponse response){
        //类型转换
        return model.getManageMapAnalyse();
    }

    @RequestMapping(value = "manage_time_data",method = RequestMethod.POST)
    @ResponseBody
    public List<TimeAnalyse> getManageTimeData(HttpServletRequest request, HttpServletResponse response){
        String pro = request.getParameter("pro");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String timeType = request.getParameter("timeType").substring(4);

        //类型转换
        int type = Integer.parseInt(timeType);

        return model.getTimeAnalyse(-1,pro,city,area,type);
    }
}
