package com.edu.nju.wel.service;

import com.edu.nju.wel.info.AddInfo;
import com.edu.nju.wel.info.MonthAnalyse;
import com.edu.nju.wel.info.PieInfo;
import com.edu.nju.wel.info.TypeAnalyse;

import java.util.List;

/**
 * Created by zs on 2017/6/29.
 */
public interface HotelDecisionService {

    public List<MonthAnalyse> getHotelLineAnalyse(int hIdInt, int type);

    public List<TypeAnalyse> getHotelTypeAnalyse(int hIdInt, int type);

    public PieInfo getHotelPieAnalyse(int hIdInt);

    public List<AddInfo> getHotelADDAnalyse(int hIdInt);
}
