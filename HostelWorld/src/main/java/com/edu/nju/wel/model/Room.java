package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zs on 2017/3/3.
 */
@Entity
@Table(name="room")
public class Room implements Serializable{
    //room数据库编号
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int rId;
    //名称-初始为null
    private String name;
    //价格
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int price;
    //房间数
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int num;
    //被预定房间数
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int orderNum;

    @JoinColumn(name="hId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=Hotel.class)
    private Hotel hotel;

    public Room() {
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
