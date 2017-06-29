package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.MonthAnalyse;
import com.edu.nju.wel.info.PieInfo;
import com.edu.nju.wel.info.TypeAnalyse;
import com.edu.nju.wel.service.HotelDecisionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zs on 2017/6/29.
 */

@Service("HotelDecisionService")
public class HotelDecisionServiceImpl implements HotelDecisionService {

    @Override
    public List<MonthAnalyse> getHotelLineAnalyse(int hIdInt, int type) {
        return DAOManager.orderDao.getHotelOrderByTime(hIdInt,type);
    }

    @Override
    public List<TypeAnalyse> getHotelTypeAnalyse(int hIdInt, int type) {
        return DAOManager.orderDao.getHotelTypeByTime(hIdInt,type);
    }

    @Override
    public PieInfo getHotelPieAnalyse(int hIdInt) {
        return DAOManager.orderDao.getHotelPie(hIdInt);
    }
}
