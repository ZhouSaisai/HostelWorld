package com.edu.nju.wel.service;

import com.edu.nju.wel.model.Hotel;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
public interface ApplicationService {
    //获得所以申请开店的店铺信息
    public List<Hotel> getOpenApplicationHotels();

    //获得所有申请修改资料的店铺信息
    public List<Hotel> getModifyApplicationHotels();

    //审批开店状态
    public String manageOpenApplication(int hidInt, int typeInt);
}
