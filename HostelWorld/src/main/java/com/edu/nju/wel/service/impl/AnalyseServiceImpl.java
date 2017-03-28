package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.HotelAnalyse;
import com.edu.nju.wel.info.ManageAnalyse;
import com.edu.nju.wel.info.MonthAnalyse;
import com.edu.nju.wel.info.VIPAnalyse;
import com.edu.nju.wel.model.Orders;
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
        List<Orders> orders = DAOManager.orderDao.getVipOrderAnalyse(vId);
        int hNum = DAOManager.orderDao.getVipHotelNum(vId);
        double money = 0;

        //构造分析列表
        VIPAnalyse va = new VIPAnalyse();
        va.setOrderNum(orders.size());
        va.setHotelNum(hNum);

        //月份统计
        List<MonthAnalyse> mons = calMons(orders);
        va.setMonths(mons);
        for(MonthAnalyse mon:mons){
            money+=mon.getMoney();
        }
        va.setMoneyNum(money);
        return va;
    }

    public HotelAnalyse getHotelAnalyse(int id) {
        List<Orders> orders = DAOManager.orderDao.getHotelOrderAnalyse(id);
        int vNum = DAOManager.orderDao.getHotelVipNum(id);
        double money = 0;

        //构造分析列表
        HotelAnalyse ha = new HotelAnalyse();
        ha.setOrderNum(orders.size());
        ha.setVipNum(vNum);

        List<MonthAnalyse> mons = calMons(orders);
        ha.setMonths(mons);
        for(MonthAnalyse mon:mons){
            money+=mon.getMoney();
        }
        ha.setMoneyNum(money);
        return ha;
    }

    public ManageAnalyse getManageAnalyse() {
        List<Orders> orders = DAOManager.orderDao.getAllOrderAnalyse();
        int vNum = DAOManager.vipDao.getVIPList().size()-1;
        int hNum = DAOManager.hotelDao.getHotelList().size();
        //构造分析列表
        ManageAnalyse ma = new ManageAnalyse();
        ma.setOrderNum(orders.size());
        ma.setVipNum(vNum);
        ma.setHotelNum(hNum);
        List<MonthAnalyse> mons = calMons(orders);
        ma.setMonths(mons);
        return ma;
    }

    private List<MonthAnalyse> calMons(List<Orders> orders){
        //月份统计
        List<MonthAnalyse> mons = new ArrayList<MonthAnalyse>();
        MonthAnalyse ma = new MonthAnalyse();
        double monMoney=0;
        int monNum=0;

        //订单为空
        if(orders.size()==0){
            return mons;
        }

        //获得date列表
        String start = orders.get(0).getTime().toString().substring(0,7);
        ma.setMon(start);
        for(int i=0;i<orders.size();i++){
            Orders order=orders.get(i);
            String str = order.getTime().toString().substring(0,7);
            if(!start.equals(str)){
                ma.setMoney(monMoney);
                ma.setNum(monNum);
                ma.setMon(start);
                mons.add(ma);

                ma = new MonthAnalyse();
                monMoney = 0;
                monNum = 0;
                start = str;
            }
            monMoney+=order.getNowPrice();
            monNum++;
        }
        ma.setMoney(monMoney);
        ma.setNum(monNum);
        ma.setMon(start);
        mons.add(ma);

        return mons;
    }

//    public static void main(String[] args){
//        AnalyseService analyse = new AnalyseServiceImpl();
////        HotelAnalyse a=analyse.getHotelAnalyse(14);
//        ManageAnalyse a = analyse.getManageAnalyse();
//        System.out.println(a);
//    }
}
