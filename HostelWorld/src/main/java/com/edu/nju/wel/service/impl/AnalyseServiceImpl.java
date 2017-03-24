package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.info.MonthAnalyse;
import com.edu.nju.wel.info.VIPAnalyse;
import com.edu.nju.wel.service.AnalyseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/3/24.
 */
@Service("AnalyseService")
public class AnalyseServiceImpl implements AnalyseService {
    public VIPAnalyse getVipAnalyse(int vId) {
        VIPAnalyse va = new VIPAnalyse();
        va.setOrderNum(11);
        va.setHotelNum(10);
        va.setMoneyNum(1000);
        List<MonthAnalyse> mons = new ArrayList<MonthAnalyse>();
        MonthAnalyse ma = new MonthAnalyse();
        ma.setNum(8);
        ma.setMoney(222);
        ma.setMon("2017-01");
        mons.add(ma);
        va.setMonths(mons);
        return va;
    }
}
