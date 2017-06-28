package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.OrderDao;
import com.edu.nju.wel.info.TimeAnalyse;
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

    @Override
    public double getVidFinishRate(int vId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Double> list;
        //查询
        String hql = "select sum(case when o.state != 3 then 1 else 0 end)*1.0/count(*) from Orders o where o.vip.vId= "+vId;
        Query query=session.createQuery(hql);
        list=query.list();
        Double a = list.get(0);
        //事务
        tx.commit();
        session.close();
        return a;
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
        String hql = "select o.vip.vId from Orders o where o.room.hotel.hId= "+hId+" and o.state != 3 and o.vip.vId != 1 order by o.time desc";
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

    @Override
    public List<Orders> getHotelOrderAnalyse(int id) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Orders> list;
        //查询
        String hql = "from Orders o where o.room.hotel.hId= "+id+" and o.state != 3 order by o.time asc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Orders>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public List<Orders> getAllOrderAnalyse() {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Orders> list;
        //查询
        int state=3;
        String hql = "from Orders o where o.state != "+state+" order by o.time asc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Orders>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public List<TimeAnalyse> getVipOrderByTime(int vId, int areaId, int type) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<TimeAnalyse> result = new ArrayList<>();
        List<Object[]> list;
        //查询
        String hql="";
        String place = " ";
        if(areaId!=0){
            place = " and o.room.hotel.area.aId like '"+areaId+"%' ";
        }
        switch (type){
            case 0:
                hql = "select DATE_FORMAT(o.time,'%Y-%m-%d') as tdate, sum(o.nowPrice) as money,count(o.oId) as num from Orders o where o.vip.vId= "+vId+place +" group by DATE_FORMAT(o.time,'%Y-%m-%d') order by DATE_FORMAT(o.time,'%Y-%m-%d')";
                break;
            case 1:
                hql = "select DATE_FORMAT(o.time,'%Y-%u') as tdate, sum(o.nowPrice) as money,count(o.oId) as num from Orders o where o.vip.vId= "+vId+place +" group by DATE_FORMAT(o.time,'%Y-%u') order by DATE_FORMAT(o.time,'%Y-%u')";
                break;
            case 2:
                hql = "select DATE_FORMAT(o.time,'%Y-%m') as tdate, sum(o.nowPrice) as money,count(o.oId) as num from Orders o where o.vip.vId= "+vId+place +" group by DATE_FORMAT(o.time,'%Y-%m') order by DATE_FORMAT(o.time,'%Y-%m')";
                break;
            case 3:
                hql = "select DATE_FORMAT(o.time,'%Y') as tdate, sum(o.nowPrice) as money,count(o.oId) as num from Orders o where o.vip.vId= "+vId+place +" group by DATE_FORMAT(o.time,'%Y') order by DATE_FORMAT(o.time,'%Y')";
                break;
        }
        Query query=session.createQuery(hql);
        list=query.list();

        for(Object[] objects:list){
            TimeAnalyse timeAnalyse = new TimeAnalyse();
            String t_time = (String) objects[0];
            double money =(Double) objects[1];
            int num=((Number) objects[2]).intValue();
            timeAnalyse.setDate(t_time);
            timeAnalyse.setMoney(money);
            timeAnalyse.setNum(num);
            result.add(timeAnalyse);
        }
        //事务
        tx.commit();
        session.close();
        return result;
    }
}
