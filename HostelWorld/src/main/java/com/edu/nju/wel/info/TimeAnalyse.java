package com.edu.nju.wel.info;

/**
 * Created by zs on 2017/6/28.
 */
public class TimeAnalyse {
    private String date;
    private int num;
    private double money;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "TimeAnalyse{" +
                "date='" + date + '\'' +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
