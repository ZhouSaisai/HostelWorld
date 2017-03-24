package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zs on 2017/3/3.
 */
@Entity
@Table(name="orders")
public class Orders implements Serializable{
    //room数据库编号
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int oId;
    //订单代号
    @Column(nullable = false,unique=true,length=7)
    private String code;
    @Column(columnDefinition = "INT default 0",nullable = false)
    //是否删除 0-预定，1-入住，2-完成，3-取消
    private int state;
    //现在价格
    @Column(columnDefinition = "double default 0",nullable = false)
    private double nowPrice;
    //原始价格
    @Column(columnDefinition = "double default 0",nullable = false)
    private double originPrice;
    //对应计划的pId,0-代表没有计划
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int plan;
    //房间数
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int num;
    //房间编号们
    private String roomIds;
    //房客姓名们
    private String names;
    //入住时间
    private String start;
    //离店时间
    private String end;
    //预定时间
    private Timestamp time;

    @JoinColumn(name="rId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=Room.class)
    private Room room;

    @JoinColumn(name="vId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=VIP.class)
    private VIP vip;



    public Orders() {
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(String roomIds) {
        this.roomIds = roomIds;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public VIP getVip() {
        return vip;
    }

    public void setVip(VIP vip) {
        this.vip = vip;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId=" + oId +
                ", code='" + code + '\'' +
                ", state=" + state +
                ", nowPrice=" + nowPrice +
                ", originPrice=" + originPrice +
                ", num=" + num +
                ", roomIds='" + roomIds + '\'' +
                ", names='" + names + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", time=" + time +
                '}';
    }
}
