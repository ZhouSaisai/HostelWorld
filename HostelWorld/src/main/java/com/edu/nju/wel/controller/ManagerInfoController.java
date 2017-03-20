package com.edu.nju.wel.controller;

import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.service.ApplicationService;
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
 * 管理员空间页对应的Controller
 * Created by zs on 2017/3/14.
 */
@Controller
public class ManagerInfoController {

    @Autowired
    ApplicationService applicationService;
    /**
     * 进入申请管理页
     * 第一次进入，即注册完成时显示识别码提示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "manage_zone")
    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
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
                    return view;
                }
            }
            view.setViewName("manageApprove");
            List<Hotel> hotels = applicationService.getOpenApplicationHotels();
            view.addObject("hotels",hotels);
        }
        return view;
    }

    @RequestMapping(value = "manage_open_app",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String manageOpenApplication(HttpServletRequest request, HttpServletResponse response) {
        //获取参数
        String hid = request.getParameter("hId");
        //1是通过，2是拒绝
        String type = request.getParameter("type");
        //转换类型
        int hidInt = Integer.parseInt(hid);
        int typeInt = Integer.parseInt(type);
        String result = applicationService.manageOpenApplication(hidInt,typeInt);
        return result;
    }

}
