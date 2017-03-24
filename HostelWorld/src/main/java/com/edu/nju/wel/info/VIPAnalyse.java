package com.edu.nju.wel.info;

import java.util.List;

/**
 * Created by zs on 2017/3/24.
 */
public class VIPAnalyse {
    //订单总数
    private int orderNum;
    //光顾的客栈数
    private int hotelNum;
    //消费总金额
    private double moneyNum;

    private List<MonthAnalyse> months;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getHotelNum() {
        return hotelNum;
    }

    public void setHotelNum(int hotelNum) {
        this.hotelNum = hotelNum;
    }

    public double getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(double moneyNum) {
        this.moneyNum = moneyNum;
    }

    public List<MonthAnalyse> getMonths() {
        return months;
    }

    public void setMonths(List<MonthAnalyse> months) {
        this.months = months;
    }
}
