package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Plan;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
public interface PlanDao {
    //获得某酒店对应的全部计划
    public List<Plan> getPlansbyHId(int hId);

    //获得某酒店某房间对应的全部计划
    public List<Plan> getPlansbyRId(int rId);

    //获得酒店某房间对应的某计划
    public Plan getPlan(int pId);

    //增加计划
    public int addPlan(Plan plan);

    //删除计划
    public int deletePlan(int pId);

    //获得全部计划
    public List<Plan> getAllPlan();

    //更新计划
    public void updatePlan(Plan plan);
}
