package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by zs on 2017/3/3.
 */
@Entity
@Table(name="plan")
public class Plan implements Serializable{
    //room数据库编号
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int pId;
    //开始时间
    private Date start;
    //结束
    private Date end;
    @Column(columnDefinition = "INT default 0",nullable = false)
    //是否删除 0-没有，1-删除,2-失效
    private int deleted;
    //价格
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int price;
    //房间数
    @Column(columnDefinition = "INT default 0",nullable = false)
    private int num;

    @JoinColumn(name="rId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=Room.class)
    private Room room;

    public Plan() {
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
