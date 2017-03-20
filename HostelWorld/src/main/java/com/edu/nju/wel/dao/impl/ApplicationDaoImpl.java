package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.ApplicationDao;
import com.edu.nju.wel.dao.CashDao;
import com.edu.nju.wel.model.Application;
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
public class ApplicationDaoImpl implements ApplicationDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public int addApplication(Application application) {
        //增加一条记录
        System.out.println("增加一条申请记录");
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(application);
        tx.commit();
        session.close();
        return 0;
    }

    public List<Application> getEffectApplicationList() {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Application> list;
        //查询
        int state=0;
        String hql = "from Application a where a.state = "+state+" order by a.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Application>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public Application getApplicationById(int hId) {
        session = sessionFactory.openSession();
        Application result = null;
        Transaction tx = session.beginTransaction();
        //查询
        List<Application> list;
        String hql = "from Application a where a.state = 0 and a.hotel.hId = "+hId;
        Query query=session.createQuery(hql);
        list=query.list();
        if(list!=null&&!list.isEmpty())
            result=list.get(0);
        //事务
        tx.commit();
        session.close();
        return result;
    }

    public void updateApplication(Application application) {
        //这里重复了
        System.out.println("更新申请信息");
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(application);
        tx.commit();
        session.close();
    }

    public List<Application> getAllApplicationById(int hId) {
        session = sessionFactory.openSession();
        List<Application> result = null;
        Transaction tx = session.beginTransaction();
        //查询
        List<Application> list;
        String hql = "from Application a where a.hotel.hId = "+hId+" order by a.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            result=new ArrayList<Application>();
        //事务
        tx.commit();
        session.close();
        return result;
    }
}
