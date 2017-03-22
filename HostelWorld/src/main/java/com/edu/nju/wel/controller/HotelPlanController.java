package com.edu.nju.wel.controller;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.Room;
import com.edu.nju.wel.service.HotelInfoService;
import com.edu.nju.wel.service.HotelPlanService;
import com.edu.nju.wel.util.helper.DateHelper;
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
import java.sql.Date;
import java.util.List;

/**
 * Created by zs on 2017/3/15.
 */
@Controller
public class HotelPlanController {
    @Autowired
    HotelInfoService hotel;
    @Autowired
    HotelPlanService planService;

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
            List<Room> rooms = planService.getRoomsByHId(temp.gethId());
            List<Plan> plans = planService.getPlansByHId(temp.gethId());
            //设置modelAndView
            view.setViewName("hotelPlan");
            view.addObject("info", info);
            view.addObject("rooms", rooms);
            view.addObject("plans",plans);
        }
        return view;
    }

    @RequestMapping(value = "add_room",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String addRoom(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String hId = request.getParameter("hId");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String num = request.getParameter("num");
        //转换类型
        int hotelId=Integer.parseInt(hId);
        int priceInt=Integer.parseInt(price);
        int numInt = Integer.parseInt(num);
        //错误处理
        if(hotelId<0){
            return "添加失败";
        }
        Room info = new Room();
        info.setName(name);
        info.setNum(numInt);
        info.setPrice(priceInt);
        String result = planService.addRoom(hotelId,info);
        return result;
    }

    @RequestMapping(value = "delete_room",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String deleteRoom(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String rId = request.getParameter("rId");
        //转换类型
        int rIdInt=Integer.parseInt(rId);
        return planService.deleteRoom(rIdInt);
    }

    @RequestMapping(value = "add_plan",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String addPlan(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String rId = request.getParameter("rId");
        String price = request.getParameter("price");
        String num = request.getParameter("num");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        //转换类型
        int rIdInt=Integer.parseInt(rId);
        int priceInt=Integer.parseInt(price);
        int numInt = Integer.parseInt(num);

        //错误处理
        if(rIdInt<0){
            return "添加失败";
        }
        Plan info = new Plan();
        info.setNum(numInt);
        info.setPrice(priceInt);
        info.setStart(start);
        info.setEnd(end);
        String result = planService.addPlan(rIdInt,info);
        return result;
    }

    @RequestMapping(value = "delete_plan",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String deletePlan(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String pId = request.getParameter("pId");
        //转换类型
        int pIdInt=Integer.parseInt(pId);
        return planService.deletePlan(pIdInt);
    }
}
