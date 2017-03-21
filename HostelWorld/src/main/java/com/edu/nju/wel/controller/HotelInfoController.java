package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.Application;
import com.edu.nju.wel.service.ApplicationService;
import com.edu.nju.wel.service.HotelInfoService;
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
import java.util.List;

/**
 * Created by zs on 2017/3/15.
 */
@Controller
public class HotelInfoController {
    @Autowired
    HotelInfoService hotel;
    @Autowired
    ApplicationService app;
    /**
     * 进入个空间页
     * 第一次进入，即注册完成时显示识别码提示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "hotel_zone")
    public ModelAndView hotelZone(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        //构造ModelAndView
        ModelAndView view = new ModelAndView("index");
        //TODO 限制进入
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
            if(temp==null || (!id.equals(temp.gethId()+""))){
                return view;
            }
            System.out.println(temp.toString());
            HotelInfo info = hotel.getHotelById(temp.gethId());
            //刷新session
            session.setAttribute("info",info);
            //设置modelAndView
            view.setViewName("hotelInfo");
            view.addObject("info",info);
            List<Application> apps = app.getALLModifyApplication(temp.gethId());
            view.addObject("apps",apps);
            //财务流水
//            List<Cash> cashs = cashService.getCashByVId(info.getId());
//            view.addObject("cashs",cashs);
            String sign=(String) session.getAttribute("sign");
            if(sign!=null && sign.equals("new")){
                session.setAttribute("sign",null);
                view.addObject("sign","new");
            }else {
                view.addObject("sign","old");
            }
        }
        return view;
    }

    @RequestMapping(value = "modify_info_hotel",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String modifyInfo(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String hid = request.getParameter("hid");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        //转换类型
        int hotelId=Integer.parseInt(hid);
        //错误处理
        if(hotelId<0){
            return "申请失败";
        }
        HotelInfo info = new HotelInfo();
        info.setAddress(address);
        info.setTel(tel);
        info.setName(name);
        info.sethId(hotelId);
        String result = hotel.modifyInfo(info);
        return result;
    }

    @RequestMapping(value = "modify_psw_hotel",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String modifyPSW(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String password = request.getParameter("password");
        String passwordNew = request.getParameter("passwordN");
        String hid = request.getParameter("hid");
        //转换类型
        int hotelId=Integer.parseInt(hid);
        String result = hotel.modifyPSW(hotelId,password,passwordNew);
        return result;
    }
}
