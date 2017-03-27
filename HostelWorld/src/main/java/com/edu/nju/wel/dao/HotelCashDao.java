package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.HotelCash;

import java.util.List;

/**
 * Created by zs on 2017/3/27.
 */
public interface HotelCashDao {
    //增加财务记录
    public int addCash(HotelCash cash);

    public List<HotelCash> getHotelCashByHId(int hId);
}
