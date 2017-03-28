package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.model.Orders;
import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.Room;
import com.edu.nju.wel.service.HotelInfoService;
import com.edu.nju.wel.service.HotelPlanService;
import com.edu.nju.wel.service.OrderService;
import com.edu.nju.wel.service.PersonInfoService;
import com.edu.nju.wel.util.helper.DateHelper;
import com.edu.nju.wel.util.helper.IDCodeHelper;
import com.edu.nju.wel.util.helper.PointHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
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
    @Autowired
    OrderService orderService;


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

    @RequestMapping(value = "my_order")
    public ModelAndView myOrder(HttpServletRequest request, HttpServletResponse response) {
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
            PersonInfo temp = (PersonInfo) session.getAttribute("info");
            PersonInfo info = person.getPersonById(temp.getId());
            //刷新session
            session.setAttribute("info", info);
            //获得订单信息
            List<Orders> orderingOrders = orderService.getMyOrdersByState(temp.getId(), 0);
            List<Orders> orderedOrders = orderService.getMyOrdersByState(temp.getId(), 1);
            List<Orders> checkedOrders = orderService.getMyOrdersByState(temp.getId(), 2);
            List<Orders> cancelOrders = orderService.getMyOrdersByState(temp.getId(), 3);

            view.setViewName("personOrder");
            view.addObject("info", info);
            view.addObject("orderingOrders", orderingOrders);
            view.addObject("orderedOrders", orderedOrders);
            view.addObject("checkedOrders", checkedOrders);
            view.addObject("cancelOrders", cancelOrders);
            view.addObject("vId", temp.getId());
        }
        return view;
    }
    @RequestMapping(value = "hotel_check")
    public ModelAndView hotelCheck(HttpServletRequest request, HttpServletResponse response) {
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
            //获得订单信息
            List<Orders> orderingOrders = orderService.getHotelOrderByState(temp.gethId(),0);
            List<Orders> orderedOrders = orderService.getHotelOrderByState(temp.gethId(),1);
            List<Orders> checkedOrders = orderService.getHotelOrderByState(temp.gethId(),2);
            List<Room> rooms = planService.getRoomsByHId(temp.gethId());
            view.setViewName("checkOrder");
            view.addObject("info",info);
            view.addObject("orderingOrders",orderingOrders);
            view.addObject("orderedOrders",orderedOrders);
            view.addObject("checkedOrders",checkedOrders);
            view.addObject("rooms",rooms);
            view.addObject("hId",temp.gethId());
        }
        return view;
    }


    @RequestMapping(value = "get_price",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
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

        return calOrderPrice(vIdInt,pIdInt,rIdInt,start,end);
    }

    @RequestMapping(value = "get_price_nv",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String getPriceNv(HttpServletRequest request, HttpServletResponse response) {
        String rId = request.getParameter("rId");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String num = request.getParameter("num");

        //转换类型
        int rIdInt = Integer.parseInt(rId);
        int numInt = Integer.parseInt(num);
        Room room = planService.getRoomByRId(rIdInt);
        if(room==null){
            return "0.0";
        }
        int day = DateHelper.calDays(start,end);
        double price = room.getPrice()*numInt*day;
        return price+"";
    }

    @RequestMapping(value = "check_in",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String checkIn(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String names = request.getParameter("names");
        String roomIds = request.getParameter("roomIds");
        String result = orderService.checkIn(code,names,roomIds);
        return result;
    }

    @RequestMapping(value = "check_out",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String checkOut(HttpServletRequest request, HttpServletResponse response) {
        String oId = request.getParameter("oId");
        int oIdInt = Integer.parseInt(oId);
        String result = orderService.checkOut(oIdInt);
        return result;
    }

    @RequestMapping(value = "get_order",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String getOrder(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        Orders result = orderService.getOrderByCode(code);
        if(result==null){
            return "0";
        }else{
            return "1;"+result.getRoom().getName()+";"+result.getStart()+";"+result.getNum()
                    +";"+result.getEnd()+";"+result.getNowPrice()+";"+result.getTime().toString().substring(0,19);
        }
    }



    @RequestMapping(value = "order_hotel",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String orderHotel(HttpServletRequest request, HttpServletResponse response) {
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
        String[] price = calOrderPrice(vIdInt,pIdInt,rIdInt,start,end).split(";");
        Double n_price = Double.parseDouble(price[0]);
        Double o_price = Double.parseDouble(price[1]);
        //生成订单
        Orders order = new Orders();
        order.setState(0);
        order.setNum(numInt);
        order.setStart(start);
        order.setEnd(end);
        order.setPlan(pIdInt);
        order.setOriginPrice(o_price);
        order.setNowPrice(n_price);
        String result = orderService.addOrder(vIdInt,rIdInt,order);
        return result;
    }

    @RequestMapping(value = "order_hotel_nv",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String orderHotelNv(HttpServletRequest request, HttpServletResponse response) {
        String rId = request.getParameter("rId");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String num = request.getParameter("num");
        String names = request.getParameter("names");
        String roomIds = request.getParameter("roomIds");

        //转换类型
        int rIdInt = Integer.parseInt(rId);
        int numInt = Integer.parseInt(num);

        Room room = planService.getRoomByRId(rIdInt);
        if(room==null){
            return "入住失败";
        }
        int day = DateHelper.calDays(start,end);
        double price = room.getPrice()*numInt*day;
        //生成订单
        Orders order = new Orders();
        order.setState(1);
        order.setNum(numInt);
        order.setStart(start);
        order.setEnd(end);
        order.setPlan(0);
        order.setOriginPrice(price);
        order.setNowPrice(price);
        order.setNames(names);
        order.setRoomIds(roomIds);
        //生成订单号
        String code = "8"+ IDCodeHelper.getID();
        order.setCode(code);
        //生成时间
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        order.setTime(stamp);
        String result = orderService.addOrderNv(1,rIdInt,order);
        return result;
    }

    @RequestMapping(value = "cancel_order",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String cancelOrder(HttpServletRequest request, HttpServletResponse response) {
        String oId = request.getParameter("oId");
        int oIdInt = Integer.parseInt(oId);
        return orderService.cancelOrder(oIdInt);
    }

    private String calOrderPrice(int vIdInt,int pIdInt,int rIdInt,String start,String end){
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
