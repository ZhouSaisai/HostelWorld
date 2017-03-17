package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.ADDType;
import com.edu.nju.wel.info.BankCardInfo;
import com.edu.nju.wel.model.BankCard;
import com.edu.nju.wel.model.VIP;
import com.edu.nju.wel.service.PersonInfoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by zs on 2017/3/18.
 */
@Service("PersonInfoService")
public class PersonInfoServiceImpl implements PersonInfoService {

    public String addMoney(BankCardInfo card, ADDType type) {
        //取得用户的信息
        VIP vip = DAOManager.vipDao.getVIPById(card.getvId());
        if(vip==null){
            return "充值失败";
        }
        //查找银行卡是否存在
        BankCard temp = DAOManager.bankcardDao.getCard(card.getAccount());
        if(temp==null){
            //不存在则构造数据层Model存入数据库
            BankCard bankCard = new BankCard();
            bankCard.setAccount(card.getAccount());
            bankCard.setVip(vip);
            int result = DAOManager.bankcardDao.addCard(bankCard);
            if(result==-1){
                return "充值失败";
            }
        }
        //存在则判断是否为本人
        else if(temp.getVip().getvId()!=vip.getvId()){
            return "充值失败";
        }
        //充值/状态转换
        double money = vip.getMoney()+card.getNumber();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        switch (type){
            case ACTIVE:
                if(vip.getMoney()<1000){
                    return "激活会员卡需要充值至少1000元";
                }
                vip.setIsActive(1);
                vip.setTime(timestamp);
                break;
            case RECOVER:
                if(money<1000){
                    return "恢复暂停的会员卡需要充值到1000元";
                }
                vip.setState(0);
                vip.setTime(timestamp);
                break;
            case ADD:
                break;
        }
        vip.setMoney(money);
        DAOManager.vipDao.updateVIP(vip);
        //TODO 记录流水

        return "充值成功";
    }
}
