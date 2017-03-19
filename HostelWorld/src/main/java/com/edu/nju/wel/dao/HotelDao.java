package com.edu.nju.wel.dao;


import com.edu.nju.wel.model.Hotel;

import java.util.List;

/**
 * Created by zs on 2017/3/3.
 */

public interface HotelDao {
    //注册客栈
    public int addHotel(Hotel hotel);
    //通过id获取客栈
    public Hotel getHotelById(int hId);
    //通过识别码获取客栈
    public Hotel getHotelByCode(String code);
    //全部获取客栈
    public List<Hotel> getHotelList();
    //更新会员信息
    public void updateHotel(Hotel hotel);
    //获得申请开店的所有客栈
    public List<Hotel> getOpenHotelList();
}
