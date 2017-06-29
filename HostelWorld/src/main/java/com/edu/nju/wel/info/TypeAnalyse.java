package com.edu.nju.wel.info;

/**
 * Created by zs on 2017/6/29.
 */
public class TypeAnalyse {
    private String date;
    private int singleM;
    private int doubleM;
    private int bigM;
    private double money;

    public TypeAnalyse() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSingleM() {
        return singleM;
    }

    public void setSingleM(int singleM) {
        this.singleM = singleM;
    }

    public int getDoubleM() {
        return doubleM;
    }

    public void setDoubleM(int doubleM) {
        this.doubleM = doubleM;
    }

    public int getBigM() {
        return bigM;
    }

    public void setBigM(int bigM) {
        this.bigM = bigM;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "TypeAnalyse{" +
                "date='" + date + '\'' +
                ", singleM=" + singleM +
                ", doubleM=" + doubleM +
                ", bigM=" + bigM +
                ", money=" + money +
                '}';
    }
}
