package com.edu.nju.wel.controller;

import com.edu.nju.wel.util.helper.ManageAccountHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 管理员空间页对应的Controller
 * Created by zs on 2017/3/14.
 */
@Controller
public class ManagerInfoController {
    /**
     * 进入个空间页
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
        }
        return view;
    }

}
