package com.edu.nju.wel.service;

import com.edu.nju.wel.model.Cash;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
public interface CashService {
    public List<Cash> getCashByVId(int id);
}
