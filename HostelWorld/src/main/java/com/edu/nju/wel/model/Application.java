package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zs on 2017/3/3.
 */
@Entity
@Table(name="application")
public class Application implements Serializable {
    //hotel数据库编号
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int aId;
    //姓名-初始为null
    private String name;
    //电话
    private String tel;
    //住址
    private String address;
    //客栈状态 0-申请中，1-申请通过，2-审批未通过，3-审批前有新的修改
    @Column(columnDefinition = "INT default 0", nullable = false)
    private int state;
    //时间-已有申请的时候，将之前的状态设置为3
    private Timestamp time;


    @JoinColumn(name="hId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=Hotel.class)
    private Hotel hotel;

    public Application() {
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Application{" +
                "aId=" + aId +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", time=" + time +
                ", hotel=" + hotel +
                '}';
    }
}



