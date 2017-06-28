package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Area;

import java.util.List;

/**
 * Created by zs on 2017/6/27.
 */

public interface AreaDao {
    //获得所有全部具体地址，type=0
    public List<Area> getSmallAreas(int pId);

    public List<Integer> getSmallAreaIds(int pId);

    public Area getArea(int aId);

    public int getProId(String pro);

    public int getAreaId(int pId, String city);
}
