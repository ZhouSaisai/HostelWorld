package com.edu.nju.wel.info;

import java.util.List;

/**
 * Created by zs on 2017/3/24.
 */
public class ManageAnalyse {
    //会员数
    private int vipNum;
    //客栈数
    private int hotelNum;
    //订单
    private int orderNum;

    private List<MonthAnalyse> months;

    public int getVipNum() {
        return vipNum;
    }

    public void setVipNum(int vipNum) {
        this.vipNum = vipNum;
    }

    public int getHotelNum() {
        return hotelNum;
    }

    public void setHotelNum(int hotelNum) {
        this.hotelNum = hotelNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public List<MonthAnalyse> getMonths() {
        return months;
    }

    public void setMonths(List<MonthAnalyse> months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "ManageAnalyse{" +
                "vipNum=" + vipNum +
                ", hotelNum=" + hotelNum +
                ", orderNum=" + orderNum +
                ", months=" + months.toString() +
                '}';
    }
}
