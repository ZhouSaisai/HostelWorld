package com.edu.nju.wel.info;

/**
 * Created by zs on 2017/3/18.
 */
public class BankCardInfo {
    private int cId;
    //账号
    private String account;
    //对应用户
    private int vId;
    //金额
    private int number;

    public BankCardInfo() {}

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

    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
