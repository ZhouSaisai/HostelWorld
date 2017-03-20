package com.edu.nju.wel.info;


import java.sql.Timestamp;

/**
 * Created by zs on 2017/3/3.
 */
public class HotelInfo{
    //vip数据库编号
    private int hId;
    //姓名-初始为null
    private String name;
    //密码-6~20位
    private String password;
    //7位识别码
    private String code;
    //电话
    private String tel;
    //住址
    private String address;
    //客栈状态 0-申请，1-营业
    private int state;
    //星级，默认为0
    private int level;
    //收入
    private double money;
    //待结算
    private double outMoney;
    //申请时间
    private Timestamp time;
    public HotelInfo() {
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(double outMoney) {
        this.outMoney = outMoney;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HotelInfo{" +
                "hId=" + hId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", level=" + level +
                ", money=" + money +
                ", outMoney=" + outMoney +
                ", time=" + time +
                '}';
    }
}
