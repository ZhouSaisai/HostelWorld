package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.BankCardDao;
import com.edu.nju.wel.model.BankCard;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Repository
public class BankCardDaoImpl implements BankCardDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public int addCard(BankCard card) {
        session = sessionFactory.openSession();
        int result = -1;
        Transaction tx = session.beginTransaction();
        session.save(card);
        //查询
        Criteria criteria = session.createCriteria(BankCard.class);
        criteria.add(Expression.eq("account",card.getAccount()));
        List<BankCard> list=criteria.list();
        result=list.get(0).getcId();
        tx.commit();
        session.close();
        return result;
    }

    public BankCard getCard(String account) {
        session = sessionFactory.openSession();
        BankCard result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(BankCard.class);
        criteria.add(Expression.eq("account",account));
        List<BankCard> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        tx.commit();
        session.close();
        return result;
    }
}
