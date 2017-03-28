package com.edu.nju.wel.service;

import com.edu.nju.wel.model.Application;
import com.edu.nju.wel.model.Hotel;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
public interface ApplicationService {
    //获得所以申请开店的店铺信息
    public List<Hotel> getOpenApplicationHotels();

    //获得所有申请修改资料的店铺信息
    public List<Application> getModifyApplicationHotels();

    //获得客栈全部状态资料申请
    public List<Application> getALLModifyApplication(int hId);

    //审批开店状态
    public String manageOpenApplication(int hidInt, int typeInt);

    //审批修改资料状态
    public String manageModifyApplication(int hidInt, int typeInt);

    //结算客栈的余额
    public String manageSettleMoney(int hidInt);
}
