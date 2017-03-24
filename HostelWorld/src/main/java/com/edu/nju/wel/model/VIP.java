package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zs on 2017/3/3.
 */
@Entity
@Table(name="vip")
public class VIP implements Serializable{
    //vip数据库编号
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int vId;
    //姓名-初始为null
    private String name;
    //密码-6~20位
    private String password;
    //7位识别码
    @Column(nullable = false,unique=true,length=7)
    private String code;
    //年纪
    private int age;
    //住址
    private String address;
    //是否激活-默认为0-未激活
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int isActive;
    //会员资格状态-/恢复0/暂停1/停止2-默认恢复-0
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int state;
    //会员资格状态，更改后的时间
    private Timestamp time;
    //级别，默认为0
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int level;
    //积分
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int point;
    //余额
    @Column(columnDefinition = "INT default 0",nullable = false)
    private double money;

    @OneToMany(targetEntity=BankCard.class,mappedBy="vip")
    private List<BankCard> cards;

    @OneToMany(targetEntity=Cash.class,mappedBy="vip")
    private List<Cash> cashs;

    @OneToMany(targetEntity=Orders.class,mappedBy="vip")
    private List<Orders> orderss;

    public VIP() {
    }

    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<BankCard> getCards() {
        return cards;
    }

    public void setCards(List<BankCard> cards) {
        this.cards = cards;
    }

    public List<Cash> getCashs() {
        return cashs;
    }

    public void setCashs(List<Cash> cashs) {
        this.cashs = cashs;
    }

    public List<Orders> getOrderss() {
        return orderss;
    }

    public void setOrderss(List<Orders> orderss) {
        this.orderss = orderss;
    }
}
