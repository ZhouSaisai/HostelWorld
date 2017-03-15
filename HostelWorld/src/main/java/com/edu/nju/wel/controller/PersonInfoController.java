package com.edu.nju.wel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 个人用户的个人空间页对应的Controller
 * Created by zs on 2017/3/14.
 */
@Controller
public class PersonInfoController {
    /**
     * 进入个空间页
     * 第一次进入，即注册完成时显示识别码提示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "person_zone")
    public String welcome(@RequestParam String id, HttpServletRequest request, HttpServletResponse response){
        //TODO 限制进入

        return "personInfo";
    }
}
