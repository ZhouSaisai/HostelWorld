package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.Application;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.service.HotelInfoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Service("HotelInfoService")
public class HotelInfoServiceImpl implements HotelInfoService {

    public HotelInfo getHotelById(int id) {
        Hotel hotel = DAOManager.hotelDao.getHotelById(id);
        HotelInfo info = new HotelInfo();
        if(hotel==null){
            return info;
        }
        info.sethId(hotel.gethId());
        info.setCode(hotel.getCode());
        info.setPassword(hotel.getPassword());
        info.setState(hotel.getState());
        info.setName(hotel.getName());
        info.setAddress(hotel.getAddress());
        info.setLevel(hotel.getLevel());
        info.setTel(hotel.getTel());
        info.setMoney(hotel.getMoney());
        info.setOutMoney(hotel.getOutMoney());
        info.setTime(hotel.getTime());
        return info;
    }

    public String modifyInfo(HotelInfo info) {
        //取得客栈的信息
        Hotel hotel = DAOManager.hotelDao.getHotelById(info.gethId());
        if(hotel==null){
            return "申请失败！";
        }
        Application application=DAOManager.applicationDao.getApplicationById(info.gethId());
        //修改前有新的修改
        if(application!=null){
            application.setState(3);
            DAOManager.applicationDao.updateApplication(application);
        }
        application = new Application();
        application.setName(info.getName());
        application.setAddress(info.getAddress());
        application.setTel(info.getTel());
        application.setHotel(hotel);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        application.setTime(timestamp);
        DAOManager.applicationDao.addApplication(application);
        return "申请成功！";
    }

    public String modifyPSW(int hId, String password, String passwordNew) {
        //取得客栈的信息
        Hotel hotel = DAOManager.hotelDao.getHotelById(hId);
        if(hotel==null){
            return "修改失败！";
        }
        if(!password.equals(hotel.getPassword())){
            return "原密码输入错误！";
        }
        hotel.setPassword(passwordNew);
        DAOManager.hotelDao.updateHotel(hotel);
        return "修改成功！";
    }

    public List<Hotel> getHotels() {
        return DAOManager.hotelDao.getHotelList();
    }

}
