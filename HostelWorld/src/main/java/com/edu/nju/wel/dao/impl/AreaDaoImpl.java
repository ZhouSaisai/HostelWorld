package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.AreaDao;
import com.edu.nju.wel.model.Area;
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
 * Created by zs on 2017/6/27.
 */

@Repository
public class AreaDaoImpl implements AreaDao{

    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    @Override
    public List<Area> getSmallAreas(int pId) {
        session = sessionFactory.openSession();
        List<Area> result = null;
        Transaction tx = session.beginTransaction();
        //查询
        String hql = "from Area a where a.aId%10000 != 0 and a.aId%100 = 0 and a.aId like '"+pId+"%'";
        Query query=session.createQuery(hql);
        result=query.list();

        if(result==null){
            result=new ArrayList<Area>();
        }
        tx.commit();
        session.close();
        return result;
    }

    @Override
    public List<Integer> getSmallAreaIds(int pId) {
        session = sessionFactory.openSession();
        List<Integer> result = new ArrayList<Integer>();
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Area.class);
        criteria.add(Expression.eq("type",0));
        List<Area> areas =criteria.list();
        tx.commit();
        session.close();
        for(Area area:areas) {
            result.add(area.getaId());
        }
        return result;
    }

    @Override
    public Area getArea(int aId) {
        session = sessionFactory.openSession();
        Area result = null;
        Transaction tx = session.beginTransaction();
        //查询
        Criteria criteria = session.createCriteria(Area.class);
        criteria.add(Expression.eq("aId",aId));
        List<Area> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        tx.commit();
        session.close();
        return result;
    }

    @Override
    public int getProId(String pro) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //查询
        String hql = "from Area a where a.name like '%"+pro+"%'";
        Query query=session.createQuery(hql);
        List<Area> result=query.list();
        tx.commit();
        session.close();
        if(result==null){
            return 0;
        }
        return result.get(0).getaId()/10000;
    }

    @Override
    public int getAreaId(int pId, String city) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //查询
        String hql = "from Area a where a.name like '%"+city+"%' and a.aId like '"+pId+"%'";
        Query query=session.createQuery(hql);
        List<Area> result=query.list();
        tx.commit();
        session.close();
        if(result==null){
            return -1;
        }
        return result.get(0).getaId();
    }
}
