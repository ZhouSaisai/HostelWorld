package com.edu.nju.wel.service.impl;


import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.Room;
import com.edu.nju.wel.service.HotelPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Service("HotelPlanService")
public class HotelPlanServiceImpl implements HotelPlanService {


    public List<Room> getRoomsByHId(int hId) {
        return DAOManager.roomDao.getRooms(hId);
    }

    public Room getRoomByRId(int rId) {
        return DAOManager.roomDao.getRoom(rId);
    }

    public List<Plan> getPlansByHId(int hId) {
        return DAOManager.planDao.getPlansbyHId(hId);
    }

    public List<Plan> getPlansByRId(int rId) {
        return DAOManager.planDao.getPlansbyRId(rId);
    }

    public Plan getPlanByPId(int pId) {
        return DAOManager.planDao.getPlan(pId);
    }

    public String addRoom(int hId, Room room) {
        Hotel hotel = DAOManager.hotelDao.getHotelById(hId);
        if(hotel==null){
            return "添加失败";
        }
        room.setHotel(hotel);
        DAOManager.roomDao.addRoom(room);
        return "添加成功";
    }

    public String deleteRoom(int rId) {
        //删除对应房型的计划
        List<Plan> plans = DAOManager.planDao.getPlansbyRId(rId);
        for(Plan plan:plans){
            DAOManager.planDao.deletePlan(plan.getpId());
        }
        int result = DAOManager.roomDao.deleteRoom(rId);
        return result==-1? "删除失败" : "删除成功";
    }

    public String addPlan(int rId, Plan plan) {
        Room room = DAOManager.roomDao.getRoom(rId);
        if(room==null){
            return "添加失败";
        }
        plan.setRoom(room);
        DAOManager.planDao.addPlan(plan);
        return "添加成功";
    }

    public String deletePlan(int pId) {
        int result = DAOManager.planDao.deletePlan(pId);
        return result==-1? "删除失败" : "删除成功";
    }
}
