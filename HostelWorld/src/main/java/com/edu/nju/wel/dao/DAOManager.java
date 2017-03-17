package com.edu.nju.wel.dao;

import com.edu.nju.wel.util.helper.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;

/**
 * Created by Jerry Wang on 2016/12/30.
 */
public class DAOManager {

    public final static VIPDao vipDao;
    public final static BankCardDao bankcardDao;

    static {
        ApplicationContext context = ApplicationContextHelper.getApplicationContext();
        vipDao = context.getBean(VIPDao.class);
        bankcardDao = context.getBean(BankCardDao.class);
    }

}
