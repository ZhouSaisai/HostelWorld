package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Application;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
public interface ApplicationDao {
    //增加申请记录
    public int addApplication(Application application);
    //获得全部客栈的申请信息
    public List<Application> getEffectApplicationList();
    //根据酒店id获取申请信息，是否有
    public Application getApplicationById(int hId);
    //更新申请信息
    public void updateApplication(Application application);
}
