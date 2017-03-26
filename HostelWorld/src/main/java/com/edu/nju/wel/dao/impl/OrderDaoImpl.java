package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.OrderDao;
import com.edu.nju.wel.info.MonthAnalyse;
import com.edu.nju.wel.info.VIPAnalyse;
import com.edu.nju.wel.model.Orders;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zs on 2017/3/24.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public List<Orders> getOrdersByHId(int hId,int state) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Orders> list;
        //查询
        String hql = "from Orders orders where orders.room.hotel.hId= "+hId+" and orders.state = "+ state +" order by orders.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Orders>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public List<Orders> getOrdersByVId(int vId, int state) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Orders> list;
        //查询
        String hql = "from Orders orders where orders.vip.vId= "+vId+" and orders.state = "+ state +" order by orders.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Orders>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public Orders getOrderByCode(String code) {
        session = sessionFactory.openSession();
        Orders result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Orders.class);
        criteria.add(Expression.eq("code",code));
        List<Orders> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        //事务
        tx.commit();
        session.close();
        return result;
    }

    public int addOrder(Orders orders) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("添加订单信息");
        session.save(orders);
        tx.commit();
        session.close();
        return 0;
    }

    public int updateOrder(Orders orders) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("更新订单信息");
        session.update(orders);
        tx.commit();
        session.close();
        return 0;
    }

    public Orders getOrderByOId(int oIdInt) {
        session = sessionFactory.openSession();
        Orders result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Orders.class);
        criteria.add(Expression.eq("oId",oIdInt));
        List<Orders> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        //事务
        tx.commit();
        session.close();
        return result;
    }

    public List<Orders> getVipOrderAnalyse(int vId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Orders> list;
        //查询
        String hql = "from Orders o where o.vip.vId= "+vId+" and o.state != 3 order by o.time asc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Orders>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public int getVipHotelNum(int vId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List list;
        //查询
        String hql = "select o.room.hotel.hId from Orders o where o.vip.vId= "+vId+" and o.state != 3 order by o.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList();
        //事务
        tx.commit();
        session.close();
        HashSet<Integer> hs = new HashSet<Integer>(list);
        return hs.toArray().length;
    }

    public int getHotelVipNum(int hId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List list;
        //查询
        String hql = "select distinct o.vip.vId from Orders o where o.room.hotel.hId= "+hId+" and o.state != 3 order by o.time desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList();
        //事务
        tx.commit();
        session.close();
        HashSet<Integer> hs = new HashSet<Integer>(list);
        return hs.toArray().length;
    }
}
