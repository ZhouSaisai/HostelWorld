package com.edu.nju.wel.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zs on 2017/3/18.
 */
@Entity
@Table(name="cash")
public class Cash {
    //id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int cashId;
    //内容 +1000/-1000
    private String content;
    //时间
    private Timestamp time;
    //类别 0充值，1预定支付，2退款，3积分兑换
    private int type;

    @JoinColumn(name="vId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=VIP.class )
    private VIP vip;

    public Cash() {
    }

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
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

    public VIP getVip() {
        return vip;
    }

    public void setVip(VIP vip) {
        this.vip = vip;
    }
}
