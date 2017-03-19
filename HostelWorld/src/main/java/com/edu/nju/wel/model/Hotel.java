package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zs on 2017/3/3.
 */
@Entity
@Table(name="hotel")
public class Hotel implements Serializable{
    //hotel数据库编号
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int hId;
    //姓名-初始为null
    private String name;
    //密码-6~20位
    private String password;
    //7位识别码
    @Column(nullable = false,unique=true,length=7)
    private String code;
    //电话
    private String tel;
    //住址
    private String address;
    //客栈状态 0-申请，1-营业，2-审批未通过
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int state;
    //星级，默认为0
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int level;
    //收入
    @Column(columnDefinition = "INT default 0",nullable = false)
    private double money;
    //待结算
    @Column(columnDefinition = "INT default 0",nullable = false)
    private double outMoney;
    //申请时间
    private Timestamp time;

    @OneToMany(targetEntity=Room.class,mappedBy="hotel")
    private List<Room> rooms;

    @OneToMany(targetEntity = Application.class, mappedBy = "hotel")
    private List<Application> applications;

    public Hotel() {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setOutMoney(double outMoney) {
        this.outMoney = outMoney;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
