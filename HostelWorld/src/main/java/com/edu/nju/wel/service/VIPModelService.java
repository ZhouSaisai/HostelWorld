package com.edu.nju.wel.service;

import com.edu.nju.wel.info.MapAnalyse;
import com.edu.nju.wel.info.TimeAnalyse;

import java.util.List;

/**
 * Created by zs on 2017/6/28.
 */
public interface VIPModelService {
    public List<Double> getPoints(int vId);

    public List<MapAnalyse> getMapAnalyse(int vId);

    public List<MapAnalyse> getSmallMapAnalyse(int vId, int pId);

    public List<TimeAnalyse> getTimeAnalyse(int vId, String pro, String city, String area, int type);

    public List<MapAnalyse> getManageMapAnalyse();
}
