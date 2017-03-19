package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.VIP;

import java.util.List;

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
    //获取全部会员
    public List<VIP> getVIPList();
    //更新会员信息
    public void updateVIP(VIP vip);
}
