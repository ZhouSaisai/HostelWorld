package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.service.HotelLoginService;
import com.edu.nju.wel.util.exception.NotExistException;
import com.edu.nju.wel.util.exception.WrongPasswordException;
import com.edu.nju.wel.util.helper.IDCodeHelper;
import org.springframework.stereotype.Service;


/**
 * Created by zs on 2017/3/14.
 */
@Service("HotelLoginService")
public class HotelLoginServiceImpl implements HotelLoginService {

    public HotelInfo askRegister(HotelInfo hotelInfo) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelInfo.getName());
        hotel.setPassword(hotelInfo.getPassword());
        hotel.setLevel(hotelInfo.getLevel());
        hotel.setTel(hotelInfo.getTel());
        hotel.setAddress(hotelInfo.getAddress());
        //生成7位识别码
        String code = "h"+IDCodeHelper.getID();
//        System.out.println(code);
        hotel.setCode(code);
        //调用数据层
        int hid= DAOManager.hotelDao.addHotel(hotel);
        hotelInfo.sethId(hid);
        hotelInfo.setCode(code);
        return hotelInfo;
    }

    public HotelInfo askLogin(HotelInfo hotelInfo) throws NotExistException,WrongPasswordException {
        //调用数据层
        Hotel temp= DAOManager.hotelDao.getHotelByCode(hotelInfo.getCode());
        if (temp==null) {
            throw new NotExistException();
        }
        if(!temp.getPassword().equals(hotelInfo.getPassword())){
            throw new WrongPasswordException();
        }
        //转换对象
        HotelInfo info = new HotelInfo();
        info.sethId(temp.gethId());
        info.setCode(temp.getCode());
        info.setPassword(temp.getPassword());
        info.setName(temp.getName());
        info.setMoney(temp.getMoney());
        info.setState(temp.getState());
        info.setLevel(temp.getLevel());
        info.setAddress(temp.getAddress());
        info.setOutMoney(temp.getOutMoney());
        info.setTel(temp.getTel());
        return info;
    }
}
