package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.UserDao;
import com.edu.nju.wel.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zs on 2017/3/2.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public List<User> find() {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        List users=criteria.list();

//        String hql = "from com.edu.nju.wel.model.Uesr";
//        Query query = session.createQuery(hql);
//        @SuppressWarnings("unchecked")
//        List<User> list = query.list();

        tx.commit();
        session.close();
        return users;
    }
}
