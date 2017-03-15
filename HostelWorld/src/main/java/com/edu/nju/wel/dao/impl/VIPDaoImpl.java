package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.VIPDao;
import com.edu.nju.wel.model.VIP;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zs on 2017/3/14.
 */
@Repository
public class VIPDaoImpl implements VIPDao{

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public int addVIP(VIP vip) {
        session = sessionFactory.openSession();
        int result = -1;
        Transaction tx = session.beginTransaction();
        session.save(vip);
        //查询
        Criteria criteria = session.createCriteria(VIP.class);

        criteria.add(Expression.eq("code",vip.getCode()));
        List<VIP> list=criteria.list();
        result=list.get(0).getvId();
        tx.commit();
        session.close();
        return result;
    }

    public VIP getVIPById(int vId) {
        return null;
    }

    public VIP getVIPByCode(String code) {
        session = sessionFactory.openSession();
        VIP result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(VIP.class);
        criteria.add(Expression.eq("code",code));
        List<VIP> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        tx.commit();
        session.close();
        return result;
    }
}
