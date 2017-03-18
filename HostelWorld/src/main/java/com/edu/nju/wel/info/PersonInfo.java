package com.edu.nju.wel.info;

import com.edu.nju.wel.model.Cash;

import java.util.List;

/**
 * Created by zs on 2017/3/14.
 */
public class PersonInfo {
    //对应id
    private int id;
    //姓名-初始为null
    private String name;
    //密码-6~20位
    private String password;
    //7位识别码
    private String code;
    //是否激活-默认为0-未激活
    private int isActive;
    //会员资格状态-恢复0/暂停1/停止2-默认恢复-0
    private int state;
    //级别，默认为0
    private String level;
    //积分
    private int point;
    //余额
    private double money;
    //年纪
    private int age;
    //住址
    private String address;

    public PersonInfo() {
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", isActive=" + isActive +
                ", state=" + state +
                ", level=" + level +
                ", point=" + point +
                ", money=" + money +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
