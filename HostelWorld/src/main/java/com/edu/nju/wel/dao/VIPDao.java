package com.edu.nju.wel.dao;

import com.edu.nju.wel.dao.base.BaseDao;
import com.edu.nju.wel.model.BankCard;
import com.edu.nju.wel.model.VIP;
import org.springframework.stereotype.Repository;

/**
 * Created by zs on 2017/3/3.
 */

public interface VIPDao{
    //注册会员
    public int addVIP(VIP vip);
    //通过id获取会员
    public VIP getVIPById(int vId);
    //通过识别码获取会员
    public VIP getVIPByCode(String code);
    //更新会员信息
    public void updateVIP(VIP vip);
}
