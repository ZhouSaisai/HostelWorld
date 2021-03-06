package com.edu.nju.wel.service;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.Hotel;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
public interface HotelInfoService {
    //获得客栈信息
    public HotelInfo getHotelById(int id);
    //修改资料
    public String modifyInfo(HotelInfo info);
    //修改密码
    public String modifyPSW(int hId, String password, String passwordNew);

    //获得全部酒店信息
    public List<Hotel> getHotels();
}
