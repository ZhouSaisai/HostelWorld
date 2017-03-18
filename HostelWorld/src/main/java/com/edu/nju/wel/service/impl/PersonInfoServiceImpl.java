package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.ADDType;
import com.edu.nju.wel.info.BankCardInfo;
import com.edu.nju.wel.info.CashType;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.BankCard;
import com.edu.nju.wel.model.Cash;
import com.edu.nju.wel.model.VIP;
import com.edu.nju.wel.service.PersonInfoService;
import com.edu.nju.wel.util.helper.PointHelper;
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
                if(card.getNumber()<1000){
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
        //积分记录
        int addPoint = card.getNumber()/10;
        int level = vip.getLevel();
        int point = vip.getPoint();
        vip.setLevel(level+addPoint);
        vip.setPoint(point+addPoint);
        //更新用户
        DAOManager.vipDao.updateVIP(vip);
        //记录流水
        Cash cash = new Cash();
        cash.setVip(vip);
        cash.setTime(timestamp);
        cash.setType(CashType.ADD.ordinal());
        cash.setContent("+"+card.getNumber());
        DAOManager.cashDao.addCash(cash);

        switch (type){
            case ACTIVE:
                return "激活成功";
            case RECOVER:
                return "恢复成功";
        }
        return "充值成功";
    }

    public PersonInfo getPersonById(int id) {
        VIP vip = DAOManager.vipDao.getVIPById(id);
        PersonInfo info = new PersonInfo();
        if(vip==null)
            return info;
        info.setId(vip.getvId());
        info.setName(vip.getName());
        info.setPassword(vip.getPassword());
        info.setCode(vip.getCode());
        info.setIsActive(vip.getIsActive());
        info.setState(vip.getState());
        String level = PointHelper.calLevel(vip.getLevel());
        info.setLevel(level);
        info.setPoint(vip.getPoint());
        info.setAge(vip.getAge());
        info.setMoney(vip.getMoney());
        info.setAddress(vip.getAddress());
        return info;
    }
}
