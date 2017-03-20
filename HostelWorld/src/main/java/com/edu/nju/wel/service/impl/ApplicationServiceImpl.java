package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.Application;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
@Service("ApplicationService")
public class ApplicationServiceImpl implements ApplicationService {

    public List<Hotel> getOpenApplicationHotels() {
        List<Hotel> hotels = null;
        hotels=DAOManager.hotelDao.getOpenHotelList();
        return hotels;
    }

    public List<Application> getModifyApplicationHotels() {
        List<Application> apps = null;
        apps=DAOManager.applicationDao.getEffectApplicationList();
        return apps;
    }

    public List<Application> getALLModifyApplication(int hId) {
        List<Application> apps = null;
        apps=DAOManager.applicationDao.getAllApplicationById(hId);
        return apps;
    }

    public String manageOpenApplication(int hidInt, int typeInt) {
        //取得客栈的信息
        Hotel hotel = DAOManager.hotelDao.getHotelById(hidInt);
        if(hotel==null){
            return "操作失败！";
        }
        hotel.setState(typeInt);
        DAOManager.hotelDao.updateHotel(hotel);
        return "操作成功！";
    }

    public String manageModifyApplication(int hidInt, int typeInt) {
        //取得客栈和申请的信息
        Hotel hotel = DAOManager.hotelDao.getHotelById(hidInt);
        Application app = DAOManager.applicationDao.getApplicationById(hidInt);
        if(hotel==null || app == null){
            return "操作失败！";
        }
        if(typeInt==1){
            hotel.setName(app.getName());
            hotel.setAddress(app.getAddress());
            hotel.setTel(app.getTel());
        }
        DAOManager.hotelDao.updateHotel(hotel);
        app.setState(typeInt);
        DAOManager.applicationDao.updateApplication(app);
        return "操作成功！";
    }


}
