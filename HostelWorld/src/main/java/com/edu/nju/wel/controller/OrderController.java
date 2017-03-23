package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.Room;
import com.edu.nju.wel.service.HotelInfoService;
import com.edu.nju.wel.service.HotelPlanService;
import com.edu.nju.wel.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "order_hotel")
    public ModelAndView orderHotel(HttpServletRequest request, HttpServletResponse response) {
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

            view.setViewName("orderHotel");
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
}
