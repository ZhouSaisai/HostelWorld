package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
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
        List<MonthAnalyse> mons = new ArrayList<MonthAnalyse>();

        MonthAnalyse ma = new MonthAnalyse();
        double monMoney=0;
        int monNum=0;

        //获得date列表
        String start = orders.get(0).getTime().toString().substring(0,7);
        ma.setMon(start);
        for(Orders order:orders){
            money+=order.getNowPrice();
            String str = order.getTime().toString().substring(0,7);
            if(start.equals(str)){
                monMoney+=order.getNowPrice();
                monNum++;
                continue;
            }
            ma.setMoney(monMoney);
            ma.setNum(monNum);
            mons.add(ma);
            System.out.println(ma);
            monMoney=0;
            monNum=0;
            start=str;
        }
        va.setMoneyNum(money);
        va.setMonths(mons);
        for (MonthAnalyse monthAnalyse:mons){
            System.out.println(monthAnalyse.toString());
        }
        return va;
    }

//    public static void main(String[] args){
//        AnalyseService analyse = new AnalyseServiceImpl();
//        analyse.getVipAnalyse(1);
//    }
}
