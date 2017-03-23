package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.Room;
import com.edu.nju.wel.service.HotelInfoService;
import com.edu.nju.wel.service.HotelPlanService;
import com.edu.nju.wel.service.PersonInfoService;
import com.edu.nju.wel.util.helper.DateHelper;
import com.edu.nju.wel.util.helper.PointHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zs on 2017/3/22.
 */
@Controller
public class OrderController {
    @Autowired
    PersonInfoService person;
    @Autowired
    HotelInfoService hotelService;
    @Autowired
    HotelPlanService planService;

    @RequestMapping(value = "hotel_list")
    public ModelAndView hotelList(HttpServletRequest request, HttpServletResponse response) {
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
            //获得全部酒店
            List<Hotel> hotels=hotelService.getHotels();

            view.setViewName("hotelList");
            view.addObject("hotels",hotels);
            view.addObject("info",info);
            view.addObject("vId",temp.getId());
        }
        return view;
    }

    @RequestMapping(value = "hotel_detail")
    public ModelAndView hotelDetail(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
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
            //获得酒店信息
            int hId = Integer.parseInt(id);
            HotelInfo hotel=hotelService.getHotelById(hId);
            //获得酒店房间信息
            List<Room> rooms = planService.getRoomsByHId(hId);
            //获得房间对应的计划信息
            for(Room room:rooms){
                List<Plan> plans = planService.getPlansByRId(room.getrId());
                room.setPlans(plans);
            }

            view.setViewName("hotelDetail");
            view.addObject("hotel",hotel);
            view.addObject("rooms",rooms);
            view.addObject("info",info);
            view.addObject("vId",temp.getId());
        }
        return view;
    }

    @RequestMapping(value = "hotel_order")
    public ModelAndView hotelOrder(@RequestParam String rId, HttpServletRequest request, HttpServletResponse response) {
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
            //获得房间信息
            int rIdInt = Integer.parseInt(rId);
            Room room = planService.getRoomByRId(rIdInt);
            //获得房间对应的计划信息
            List<Plan> plans = planService.getPlansByRId(rIdInt);

            view.setViewName("hotelOrder");
            view.addObject("room",room);
            view.addObject("info",info);
            view.addObject("plans",plans);
            view.addObject("vId",temp.getId());
        }
        return view;
    }


    @RequestMapping(value = "get_price")
    @ResponseBody
    public String getPrice(HttpServletRequest request, HttpServletResponse response) {
        String vId = request.getParameter("vId");
        String rId = request.getParameter("rId");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String num = request.getParameter("num");
        String pId = request.getParameter("plan");

        //转换类型
        int rIdInt = Integer.parseInt(rId);
        int vIdInt = Integer.parseInt(vId);
        int numInt = Integer.parseInt(num);
        int pIdInt = Integer.parseInt(pId);

        int day = DateHelper.calDays(start,end);
        if(day<0){
            return "0;0";
        }
        //获得房间信息
        Room room = planService.getRoomByRId(rIdInt);
        int o_price = room.getPrice()*day;
        //获得用户信息
        PersonInfo personInfo = person.getPersonById(vIdInt);
        int level = personInfo.getLevel().charAt(3)-'0';
        System.out.println(level);
        //获得计划
        if(pIdInt==0){
            double price = (double)o_price*(1-level*0.02);
            return price+";"+o_price;
        }
        Plan plan = planService.getPlanByPId(pIdInt);
        int day2 = DateHelper.calPeriodDays(start,end,plan.getStart(),plan.getEnd());
        if(day2<0){
            return o_price*(1-level*0.02)+";"+o_price;
        }
        //享受计划的价格*享受天数 加 未享受的价格*剩余天数
        int n_price = plan.getPrice()*day2+room.getPrice()*(day-day2);
        return n_price*(1-level*0.02)+";"+o_price;
    }
}
