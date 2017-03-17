package com.edu.nju.wel.service;

import com.edu.nju.wel.info.ADDType;
import com.edu.nju.wel.info.BankCardInfo;

/**
 * Created by zs on 2017/3/18.
 */
public interface PersonInfoService {
    //充值
    public String addMoney(BankCardInfo card,ADDType type);
}
