package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.VIP;
import com.edu.nju.wel.service.PersonLoginService;
import com.edu.nju.wel.util.exception.NotExistException;
import com.edu.nju.wel.util.exception.WrongPasswordException;
import com.edu.nju.wel.util.helper.IDCodeHelper;
import com.edu.nju.wel.util.helper.PointHelper;
import org.springframework.stereotype.Service;


/**
 * Created by zs on 2017/3/14.
 */
@Service("PersonLoginService")
public class PersonLoginServiceImpl implements PersonLoginService {

    public PersonInfo askRegister(PersonInfo person) {
        VIP vip = new VIP();
        vip.setName(person.getName());
        vip.setPassword(person.getPassword());
        //生成7位识别码
        String code = "v"+IDCodeHelper.getID();
//        System.out.println(code);
        vip.setCode(code);
        //调用数据层
        int vid= DAOManager.vipDao.addVIP(vip);
        person.setId(vid);
        person.setCode(code);
        return person;
    }

    public PersonInfo askLogin(PersonInfo person) throws NotExistException,WrongPasswordException {
        //调用数据层
        VIP temp= DAOManager.vipDao.getVIPByCode(person.getCode());
        if (temp==null) {
            throw new NotExistException();
        }
        if(!temp.getPassword().equals(person.getPassword())){
            throw new WrongPasswordException();
        }
        //转换对象
        PersonInfo info = new PersonInfo();
        info.setId(temp.getvId());
        info.setCode(temp.getCode());
        info.setPassword(temp.getPassword());
        info.setName(temp.getName());
        info.setIsActive(temp.getIsActive());
        String level = PointHelper.calLevel(temp.getLevel());
        info.setLevel(level);
        info.setPoint(temp.getPoint());
        info.setMoney(temp.getMoney());
        info.setState(temp.getState());
        info.setAge(temp.getAge());
        info.setAddress(temp.getAddress());
        return info;
    }
}
