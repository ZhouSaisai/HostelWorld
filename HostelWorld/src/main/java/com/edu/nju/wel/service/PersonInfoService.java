package com.edu.nju.wel.service;

import com.edu.nju.wel.info.ADDType;
import com.edu.nju.wel.info.BankCardInfo;
import com.edu.nju.wel.info.PersonInfo;

/**
 * Created by zs on 2017/3/18.
 */
public interface PersonInfoService {
    //充值
    public String addMoney(BankCardInfo card,ADDType type);
    //获得个人信息
    public PersonInfo getPersonById(int id);
    //停止会员卡账户
    public String stopVIP(int vid);
    //兑换积分
    public String pointChange(int vipId, int number);
}
