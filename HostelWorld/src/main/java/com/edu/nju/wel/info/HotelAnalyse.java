package com.edu.nju.wel.info;

import java.util.List;

/**
 * Created by zs on 2017/3/24.
 */
public class HotelAnalyse {
    //订单总数
    private int orderNum;
    //消费的会员数
    private int vipNum;
    //收益总金额
    private double moneyNum;

    private List<MonthAnalyse> months;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getVipNum() {
        return vipNum;
    }

    public void setVipNum(int vipNum) {
        this.vipNum = vipNum;
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

    @Override
    public String toString() {
        return "HotelAnalyse{" +
                "orderNum=" + orderNum +
                ", vipNum=" + vipNum +
                ", moneyNum=" + moneyNum +
                ", months=" + months.toString() +
                '}';
    }
}
