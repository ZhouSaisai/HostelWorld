package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.CashDao;
import com.edu.nju.wel.model.Cash;
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
public class CashDaoImpl implements CashDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public int addCash(Cash cash) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(cash);
        tx.commit();
        session.close();
        return 0;
    }

    public List<Cash> getCashByVId(int id) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Cash> list;
        //查询
        String hql = "from Cash cash where cash.vip.vId = "+id+" order by cash.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Cash>();
        //事务
        tx.commit();
        session.close();
        return list;
    }
}
