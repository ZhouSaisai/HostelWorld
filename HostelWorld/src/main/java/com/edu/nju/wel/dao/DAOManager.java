package com.edu.nju.wel.dao;

import com.edu.nju.wel.util.helper.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;

/**
 * Created by Jerry Wang on 2016/12/30.
 */
public class DAOManager {

    public final static VIPDao vipDao;
    public final static BankCardDao bankcardDao;
    public final static CashDao cashDao;
    public final static HotelCashDao hcashDao;
    public final static HotelDao hotelDao;
    public final static RoomDao roomDao;
    public final static PlanDao planDao;
    public final static ApplicationDao applicationDao;
    public final static OrderDao orderDao;
    static {
        ApplicationContext context = ApplicationContextHelper.getApplicationContext();
        vipDao = context.getBean(VIPDao.class);
        bankcardDao = context.getBean(BankCardDao.class);
        cashDao = context.getBean(CashDao.class);
        hotelDao = context.getBean(HotelDao.class);
        roomDao = context.getBean(RoomDao.class);
        planDao = context.getBean(PlanDao.class);
        applicationDao = context.getBean(ApplicationDao.class);
        orderDao = context.getBean(OrderDao.class);
        hcashDao = context.getBean(HotelCashDao.class);
    }

}
