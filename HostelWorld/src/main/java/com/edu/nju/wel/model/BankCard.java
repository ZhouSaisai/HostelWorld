package com.edu.nju.wel.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zs on 2017/3/17.
 */
@Entity
@Table(name="card")
public class BankCard implements Serializable{
    //vip数据库编号
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private int cId;
    //账号
    private String account;

    @JoinColumn(name="vId")//指定在本实体所映射的那个表中关联的外键
    @ManyToOne(targetEntity=VIP.class)
    private VIP vip;

    public BankCard() {
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public VIP getVip() {
        return vip;
    }

    public void setVip(VIP vip) {
        this.vip = vip;
    }
}
