package com.edu.nju.wel.service;

import com.edu.nju.wel.model.Cash;
import com.edu.nju.wel.model.HotelCash;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
public interface CashService {
    public List<Cash> getCashByVId(int id);

    public List<HotelCash> getCashByHId(int hId);
}
