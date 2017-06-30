package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.MapAnalyse;
import com.edu.nju.wel.info.TimeAnalyse;
import com.edu.nju.wel.model.Area;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.model.Orders;
import com.edu.nju.wel.model.Room;
import com.edu.nju.wel.service.VIPModelService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zs on 2017/6/28.
 */
@Service("VIPModelService")
public class VIPModelServiceImpl implements VIPModelService {

    @Override
    public List<Double> getPoints(int vId) {
        List<Double> list = new ArrayList<>();
        list.add(95.97);
        list.add(83.25);
        list.add(23*100.0/36);
        list.add(15*100.0/17);
        Double rate = DAOManager.orderDao.getVidFinishRate(vId)*100;
        list.add(rate);
        double sum =0;
        for(Double d:list){
            sum+=d;
        }
        list.add(sum/5);
        return list;
    }

    @Override
    public List<MapAnalyse> getMapAnalyse(int vId) {
        Map<Integer,MapAnalyse> provinces = new HashMap<Integer,MapAnalyse>(){
            {
                put(11, new MapAnalyse("北京",11));
                put(12, new MapAnalyse("天津",12));
                put(13, new MapAnalyse("河北",13));
                put(14, new MapAnalyse("山西",14));
                put(15, new MapAnalyse("内蒙古",15));
                put(21, new MapAnalyse("辽宁",21));
                put(22, new MapAnalyse("吉林",22));
                put(23, new MapAnalyse("黑龙江",23));
                put(31, new MapAnalyse("上海",31));
                put(32, new MapAnalyse("江苏",32));
                put(33, new MapAnalyse("浙江",33));
                put(34, new MapAnalyse("安徽",34));
                put(35, new MapAnalyse("福建",35));
                put(36, new MapAnalyse("江西",36));
                put(37, new MapAnalyse("山东",37));
                put(41, new MapAnalyse("河南",41));
                put(42, new MapAnalyse("湖北",42));
                put(43, new MapAnalyse("湖南",43));
                put(44, new MapAnalyse("广东",44));
                put(45, new MapAnalyse("广西",45));
                put(46, new MapAnalyse("海南",46));
                put(50, new MapAnalyse("重庆",50));
                put(51, new MapAnalyse("四川",51));
                put(52, new MapAnalyse("贵州",52));
                put(53, new MapAnalyse("云南",53));
                put(54, new MapAnalyse("西藏",54));
                put(61, new MapAnalyse("陕西",61));
                put(62, new MapAnalyse("甘肃",62));
                put(63, new MapAnalyse("青海",63));
                put(64, new MapAnalyse("宁夏",64));
                put(65, new MapAnalyse("新疆",65));
            }
        };

        List<Orders> orders = DAOManager.orderDao.getVipOrderAnalyse(vId);

        for(Orders o :orders) {
            Room room = o.getRoom();
            Hotel hotel = room.getHotel();
            Area area = hotel.getArea();

            int pro = area.getaId()/10000;

            MapAnalyse mapAnalyse =  provinces.get(pro);
            String roomName = room.getName();
            mapAnalyse.setNum(mapAnalyse.getNum()+1);
            switch (roomName){
                case "单人房":
                    mapAnalyse.setSingleM(mapAnalyse.getSingleM()+o.getNowPrice());
                    break;
                case "双人房":
                    mapAnalyse.setDoubleM(mapAnalyse.getDoubleM()+o.getNowPrice());
                    break;
                case "大套间":
                    mapAnalyse.setBigM(mapAnalyse.getBigM()+o.getNowPrice());
                    break;
            }
        }
        List<MapAnalyse> mapAnalyses = new ArrayList<>();
        for(int key:provinces.keySet()){
            mapAnalyses.add(provinces.get(key));
        }
        return mapAnalyses;
    }

    @Override
    public List<MapAnalyse> getSmallMapAnalyse(int vId, int pId) {
        List<Area> areas = DAOManager.areaDao.getSmallAreas(pId);
        List<Orders> orders = DAOManager.orderDao.getVipOrderAnalyse(vId);

        Map<Integer,MapAnalyse> result = new HashMap<Integer,MapAnalyse>();
        for(Area area:areas){
            String name = area.getName();
            int id = area.getaId()/100;
            result.put(id,new MapAnalyse(name,id));
        }
        for(Orders o :orders) {
            Room room = o.getRoom();
            Hotel hotel = room.getHotel();
            Area area = hotel.getArea();

            if(area.getaId()/10000!=pId)
                continue;
            int pro = area.getaId()/100;

            MapAnalyse mapAnalyse =  result.get(pro);
            String roomName = room.getName();
            mapAnalyse.setNum(mapAnalyse.getNum()+1);

            switch (roomName){
                case "单人房":
                    mapAnalyse.setSingleM(mapAnalyse.getSingleM()+o.getNowPrice());
                    break;
                case "双人房":
                    mapAnalyse.setDoubleM(mapAnalyse.getDoubleM()+o.getNowPrice());
                    break;
                case "大套间":
                    mapAnalyse.setBigM(mapAnalyse.getBigM()+o.getNowPrice());
                    break;
            }
        }
        List<MapAnalyse> mapAnalyses = new ArrayList<>();
        for(int key:result.keySet()){
            mapAnalyses.add(result.get(key));
        }
        return mapAnalyses;
    }

    @Override
    public List<TimeAnalyse> getTimeAnalyse(int vId, String pro, String city, String area, int type) {
        int areaId = 1;
        List<TimeAnalyse> timeAnalyses = new ArrayList<>();

        if(pro.equals("")){
            areaId = 0;
        }else if(pro.equals("北京市") || pro.equals("天津市") || pro.equals("上海市")){
            int pId = DAOManager.areaDao.getProId(pro);
            if(area.equals("")){
                areaId=pId;
            }else{
                areaId = DAOManager.areaDao.getAreaId(pId,area);
                if(areaId==-1)
                    return timeAnalyses;
            }
        } else{
            int pId = DAOManager.areaDao.getProId(pro);
            if(city.equals("")){
                areaId=pId;
            }else{
                areaId = DAOManager.areaDao.getAreaId(pId,city);
                if(areaId==-1)
                    return timeAnalyses;
                if(area.equals("")){
                    areaId=areaId/100;
                } else{
                    pId = areaId/100;
                    areaId = DAOManager.areaDao.getAreaId(pId,area);
                    if(areaId==-1)
                        return timeAnalyses;
                }
            }
        }

        return DAOManager.orderDao.getVipOrderByTime(vId,areaId,type);
    }

    @Override
    public List<MapAnalyse> getManageMapAnalyse() {
        Map<Integer,MapAnalyse> provinces = new HashMap<Integer,MapAnalyse>(){
            {
                put(11, new MapAnalyse("北京",11));
                put(12, new MapAnalyse("天津",12));
                put(13, new MapAnalyse("河北",13));
                put(14, new MapAnalyse("山西",14));
                put(15, new MapAnalyse("内蒙古",15));
                put(21, new MapAnalyse("辽宁",21));
                put(22, new MapAnalyse("吉林",22));
                put(23, new MapAnalyse("黑龙江",23));
                put(31, new MapAnalyse("上海",31));
                put(32, new MapAnalyse("江苏",32));
                put(33, new MapAnalyse("浙江",33));
                put(34, new MapAnalyse("安徽",34));
                put(35, new MapAnalyse("福建",35));
                put(36, new MapAnalyse("江西",36));
                put(37, new MapAnalyse("山东",37));
                put(41, new MapAnalyse("河南",41));
                put(42, new MapAnalyse("湖北",42));
                put(43, new MapAnalyse("湖南",43));
                put(44, new MapAnalyse("广东",44));
                put(45, new MapAnalyse("广西",45));
                put(46, new MapAnalyse("海南",46));
                put(50, new MapAnalyse("重庆",50));
                put(51, new MapAnalyse("四川",51));
                put(52, new MapAnalyse("贵州",52));
                put(53, new MapAnalyse("云南",53));
                put(54, new MapAnalyse("西藏",54));
                put(61, new MapAnalyse("陕西",61));
                put(62, new MapAnalyse("甘肃",62));
                put(63, new MapAnalyse("青海",63));
                put(64, new MapAnalyse("宁夏",64));
                put(65, new MapAnalyse("新疆",65));
            }
        };

        List<Orders> orders = DAOManager.orderDao.getAllOrderAnalyse();

        for(Orders o :orders) {
            Room room = o.getRoom();
            Hotel hotel = room.getHotel();
            Area area = hotel.getArea();

            int pro = area.getaId()/10000;

            MapAnalyse mapAnalyse =  provinces.get(pro);
            mapAnalyse.setNum(mapAnalyse.getNum()+1);

        }
        List<MapAnalyse> mapAnalyses = new ArrayList<>();
        for(int key:provinces.keySet()){
            MapAnalyse analyse = provinces.get(key);
            analyse.setSingleM(analyse.getNum()*1.0/610000);
            mapAnalyses.add(analyse);
        }
        return mapAnalyses;
    }
}
