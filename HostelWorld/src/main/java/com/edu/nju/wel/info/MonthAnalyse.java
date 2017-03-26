package com.edu.nju.wel.info;

/**
 * Created by zs on 2017/3/24.
 */
public class MonthAnalyse {
    private String mon;
    private double money;
    private int num;

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MonthAnalyse{" +
                "mon='" + mon + '\'' +
                ", money=" + money +
                ", num=" + num +
                '}';
    }
}
