package com.edu.nju.wel.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zs on 2017/3/18.
 */
@Entity
@Table(name="hotelCash")
public class HotelCash {
    //id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int hcId;
    //内容 +1000/-1000
    private String content;
    //时间
    private Timestamp time;
    //类别 0会员卡，1现金
    private int type;

    @JoinColumn(name="hId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=Hotel.class )
    private Hotel hotel;

    public HotelCash() {
    }

    public int getHcId() {
        return hcId;
    }

    public void setHcId(int hcId) {
        this.hcId = hcId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
