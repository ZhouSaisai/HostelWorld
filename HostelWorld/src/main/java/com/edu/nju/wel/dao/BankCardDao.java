package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.BankCard;

/**
 * Created by zs on 2017/3/18.
 */
public interface BankCardDao {
    //增加银行卡
    public int addCard(BankCard card);
    //根据银行卡号获取银行卡信息
    public BankCard getCard(String account);
}
