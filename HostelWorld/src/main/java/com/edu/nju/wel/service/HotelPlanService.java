package com.edu.nju.wel.service;

import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.Room;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
public interface HotelPlanService {
    //获得客栈的全部房间信息
    public List<Room> getRoomsByHId(int hId);

    //获得客栈某房型的信息
    public Room getRoomByRId(int rId);

    //获得客栈的全部计划信息
    public List<Plan> getPlansByHId(int hId);

    //获得客栈某房型的计划信息
    public List<Plan> getPlansByRId(int rId);

    //获得客栈某房型的某计划信息
    public Plan getPlanByPId(int pId);

    //增加房间
    public String addRoom(int hId,Room room);

    //删除房间
    public String deleteRoom(int rId);

    //增加计划
    public String addPlan(int rId,Plan plan);

    //删除计划
    public String deletePlan(int pId);
}


