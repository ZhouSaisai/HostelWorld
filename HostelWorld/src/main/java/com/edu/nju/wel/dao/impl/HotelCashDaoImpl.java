package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.CashDao;
import com.edu.nju.wel.dao.HotelCashDao;
import com.edu.nju.wel.model.Cash;
import com.edu.nju.wel.model.HotelCash;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Repository
public class HotelCashDaoImpl implements HotelCashDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public int addCash(HotelCash cash) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(cash);
        tx.commit();
        session.close();
        return 0;
    }

    public List<HotelCash> getHotelCashByHId(int hId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<HotelCash> list;
        //查询
        String hql = "from HotelCash cash where cash.hotel.hId = "+hId+" order by cash.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<HotelCash>();
        //事务
        tx.commit();
        session.close();
        return list;
    }
}
