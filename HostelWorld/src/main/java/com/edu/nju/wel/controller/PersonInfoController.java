package com.edu.nju.wel.controller;

import com.edu.nju.wel.info.ADDType;
import com.edu.nju.wel.info.BankCardInfo;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.Cash;
import com.edu.nju.wel.service.CashService;
import com.edu.nju.wel.service.PersonInfoService;
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
 * 个人用户的个人空间页对应的Controller
 * Created by zs on 2017/3/14.
 */
@Controller
public class PersonInfoController {
    @Autowired
    PersonInfoService person;
    @Autowired
    CashService cashService;
    /**
     * 进入个空间页
     * 第一次进入，即注册完成时显示识别码提示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "person_zone")
    public ModelAndView welcome(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
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
            PersonInfo temp = (PersonInfo) session.getAttribute("info");
            if(temp==null || (!id.equals(temp.getId()+""))){
                return view;
            }
            PersonInfo info = person.getPersonById(temp.getId());
            //刷新session
            session.setAttribute("info",info);
            view.setViewName("personInfo");
            view.addObject("info",info);
            List<Cash> cashs = cashService.getCashByVId(info.getId());
            view.addObject("cashs",cashs);
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

    @RequestMapping(value = "add_money",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String addMoney(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String account = request.getParameter("account");
        String money = request.getParameter("money");
        String vid = request.getParameter("vid");
        String type = request.getParameter("type");
        //转换类型
        int num=Integer.parseInt(money);
        int vipId=Integer.parseInt(vid);
        int typeInt=Integer.parseInt(type);
        //错误处理
        if(account=="" || num<0 || vipId<0 || typeInt<0){
            return "充值失败";
        }
        BankCardInfo info = new BankCardInfo();
        info.setAccount(account);
        info.setNumber(num);
        info.setvId(vipId);
        ADDType addType = (ADDType.values()[typeInt]);
        String result = person.addMoney(info, addType);
        return result;
    }

    @RequestMapping(value = "stop_vip",method = RequestMethod.POST)
    @ResponseBody
    public String stopVIP(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String vid = request.getParameter("vid");
        //转换类型
        int vipId=Integer.parseInt(vid);
        //错误处理
        if(vipId<0){
            return "停止失败";
        }
        String result = person.stopVIP(vipId);
        if(result.equals("-1")){
            request.getSession().setAttribute("info", null);
            request.getSession().setAttribute("manager", null);
            request.getSession().setAttribute("sign", null);
        }
        return result;
    }

    @RequestMapping(value = "point_change",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String pointChange(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String vid = request.getParameter("vid");
        String num = request.getParameter("num");
        //转换类型
        int vipId=Integer.parseInt(vid);
        int number=Integer.parseInt(num);
        //错误处理
        if(vipId<0 || number<0){
            return "兑换失败";
        }
        String result = person.pointChange(vipId,number);
        return result;
    }

    @RequestMapping(value = "modify_info",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String modifyInfo(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String vid = request.getParameter("vid");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        //转换类型
        int vipId=Integer.parseInt(vid);
        int ageInt=Integer.parseInt(age);
        //错误处理
        if(vipId<0){
            return "修改失败";
        }
        PersonInfo info = new PersonInfo();
        info.setAddress(address);
        info.setAge(ageInt);
        info.setName(name);
        info.setId(vipId);
        String result = person.modifyInfo(info);
        return result;
    }

    @RequestMapping(value = "modify_psw",produces="text/html;charset=UTF-8;",method = RequestMethod.POST)
    @ResponseBody
    public String modifyPSW(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String password = request.getParameter("password");
        String passwordNew = request.getParameter("passwordN");
        String vid = request.getParameter("vid");
        //转换类型
        int vipId=Integer.parseInt(vid);
        String result = person.modifyPSW(vipId,password,passwordNew);
        return result;
    }
}
