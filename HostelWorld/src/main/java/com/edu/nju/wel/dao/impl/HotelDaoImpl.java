package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.HotelDao;
import com.edu.nju.wel.model.Hotel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 2017/3/14.
 */
@Repository
public class HotelDaoImpl implements HotelDao{

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public int addHotel(Hotel hotel) {
        session = sessionFactory.openSession();
        int result = -1;
        Transaction tx = session.beginTransaction();
        session.save(hotel);
        //查询
        Criteria criteria = session.createCriteria(Hotel.class);
        criteria.add(Expression.eq("code",hotel.getCode()));
        List<Hotel> list=criteria.list();
        result=list.get(0).gethId();
        tx.commit();
        session.close();
        return result;
    }

    public Hotel getHotelById(int hId) {
        session = sessionFactory.openSession();
        Hotel result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Hotel.class);
        criteria.add(Expression.eq("hId",hId));
        List<Hotel> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        tx.commit();
        session.close();
        return result;
    }

    public Hotel getHotelByCode(String code) {
        session = sessionFactory.openSession();
        Hotel result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Hotel.class);
        criteria.add(Expression.eq("code",code));
        List<Hotel> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        //事务
        tx.commit();
        session.close();
        return result;
    }

    public List<Hotel> getHotelList() {
        session = sessionFactory.openSession();
        List<Hotel> result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Hotel.class);
        result=criteria.list();
        if(result==null){
            result=new ArrayList<Hotel>();
        }
        tx.commit();
        session.close();
        return result;
    }

    public void updateHotel(Hotel hotel) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("更新客栈信息");
        session.update(hotel);
        tx.commit();
        session.close();
    }

    public List<Hotel> getOpenHotelList() {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Hotel> list;
        //查询
        int state = 0;
        String hql = "from Hotel hotel where hotel.state = "+ state +"order by hotel.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Hotel>();
        //事务
        tx.commit();
        session.close();
        return list;
    }
}
