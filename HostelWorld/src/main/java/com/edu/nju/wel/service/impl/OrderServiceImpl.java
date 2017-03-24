package com.edu.nju.wel.service.impl;


import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.CashType;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.HotelPlanService;
import com.edu.nju.wel.service.OrderService;
import com.edu.nju.wel.util.helper.DateHelper;
import com.edu.nju.wel.util.helper.IDCodeHelper;
import com.sun.xml.internal.rngom.digested.DMixedPattern;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    public String addOrder(int vId, int rId, Orders order) {
        VIP vip = DAOManager.vipDao.getVIPById(vId);
        Room room = DAOManager.roomDao.getRoom(rId);
        if(vip==null || room==null){
            return "预定失败";
        }
        //生成订单号
        String code = "0"+IDCodeHelper.getID();
        order.setCode(code);
        //生成时间
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        order.setTime(stamp);

        //现金流动
        double money = order.getNowPrice();
        double nMoney = vip.getMoney()-money;
        if(nMoney<0.0001){
            return "余额不足";
        }
        vip.setMoney(nMoney);
        //更新用户
        DAOManager.vipDao.updateVIP(vip);
        //记录流水
        Cash cash = new Cash();
        cash.setVip(vip);
        cash.setTime(order.getTime());
        cash.setType(CashType.ORDER.ordinal());
        cash.setContent("-"+money);
        DAOManager.cashDao.addCash(cash);

        //房间数量变化
        int planId = order.getPlan();
        int discountDay =0;
        if(planId!=0) {
            Plan plan = DAOManager.planDao.getPlan(planId);
            discountDay = DateHelper.calPeriodDays(order.getStart(), order.getEnd(), plan.getStart(), plan.getEnd());
            if (discountDay > 0) {
                plan.setOrderNum(plan.getOrderNum() + order.getNum());
                DAOManager.planDao.updatePlan(plan);
            }
        }
        room.setOrderNum(room.getOrderNum()+order.getNum());
        DAOManager.roomDao.updateRoom(room);
        //加入对象
        order.setVip(vip);
        order.setRoom(room);
        DAOManager.orderDao.addOrder(order);
        return "success";
    }

    public List<Orders> getMyOrdersByState(int vId, int state) {
        return DAOManager.orderDao.getOrdersByVId(vId,state);
    }

    public List<Orders> getHotelOrderByState(int hId, int state) {
        return DAOManager.orderDao.getOrdersByHId(hId, state);
    }

    public String cancelOrder(int oIdInt) {
        //获得对象
        Orders order = DAOManager.orderDao.getOrderByOId(oIdInt);
        if(order==null){
            return "取消失败";
        }
        VIP vip = order.getVip();
        Room room = order.getRoom();
        //现金流动
        double money = order.getNowPrice()*0.9;
        DecimalFormat df=new DecimalFormat(".#");
        String st=df.format(money);
        double moneyNow =Double.parseDouble(st);
        double nMoney = vip.getMoney();
        vip.setMoney(nMoney+moneyNow);
        //更新用户
        DAOManager.vipDao.updateVIP(vip);
        //记录流水
        Cash cash = new Cash();
        cash.setVip(vip);
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        cash.setTime(stamp);
        cash.setType(CashType.QUIT.ordinal());
        cash.setContent("+"+money);
        DAOManager.cashDao.addCash(cash);
        //房间数量变化
        int planId = order.getPlan();
        int discountDay =0;
        if(planId!=0) {
            Plan plan = DAOManager.planDao.getPlan(planId);
            discountDay = DateHelper.calPeriodDays(order.getStart(), order.getEnd(), plan.getStart(), plan.getEnd());
            if (discountDay > 0) {
                plan.setOrderNum(plan.getOrderNum() - order.getNum());
                DAOManager.planDao.updatePlan(plan);
            }
        }
        room.setOrderNum(room.getOrderNum()-order.getNum());
        DAOManager.roomDao.updateRoom(room);
        //加入对象
        order.setVip(vip);
        order.setRoom(room);
        order.setState(3);
        DAOManager.orderDao.updateOrder(order);
        return "取消成功！另系统收取10%的手续费！";
    }

    public String checkOrder(int hId, String code) {
        return null;
    }
}
