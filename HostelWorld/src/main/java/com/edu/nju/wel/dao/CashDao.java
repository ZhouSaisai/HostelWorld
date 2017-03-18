package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Cash;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
public interface CashDao {
    //增加财务记录
    public int addCash(Cash cash);

    public List<Cash> getCashByVId(int id);
}
