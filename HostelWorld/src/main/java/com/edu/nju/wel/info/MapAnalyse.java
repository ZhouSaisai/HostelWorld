package com.edu.nju.wel.info;

/**
 * Created by zs on 2017/6/28.
 */
public class MapAnalyse {
    private String name;
    private int aId;
    private int num;
    private double singleM;
    private double doubleM;
    private double bigM;

    public MapAnalyse(String name, int aId) {
        this.name = name;
        this.aId = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSingleM() {
        return singleM;
    }

    public void setSingleM(double singleM) {
        this.singleM = singleM;
    }

    public double getDoubleM() {
        return doubleM;
    }

    public void setDoubleM(double doubleM) {
        this.doubleM = doubleM;
    }

    public double getBigM() {
        return bigM;
    }

    public void setBigM(double bigM) {
        this.bigM = bigM;
    }

    @Override
    public String toString() {
        return "MapAnalyse{" +
                "name='" + name + '\'' +
                ", aId=" + aId +
                ", num=" + num +
                ", singleM=" + singleM +
                ", doubleM=" + doubleM +
                ", bigM=" + bigM +
                '}';
    }
}
