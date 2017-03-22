package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.PlanDao;
import com.edu.nju.wel.model.Plan;
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
 * Created by zs on 2017/3/22.
 */
@Repository
public class PlanDaoImpl implements PlanDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public List<Plan> getPlansbyHId(int hId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Plan> list;
        //查询
        String hql = "from Plan p where p.deleted != 1 and p.room.hotel.hId= "+ hId+" order by p.pId desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Plan>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public List<Plan> getPlansbyRId(int rId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Plan> list;
        //查询
        String hql = "from Plan p where p.deleted != 1 and p.room.rId= "+ rId+" order by p.pId desc";
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Plan>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public Plan getPlan(int pId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Plan result = null;
        //查询
        Criteria criteria = session.createCriteria(Plan.class);
        criteria.add(Expression.eq("pId",pId));
        List<Plan> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        //事务
        tx.commit();
        session.close();
        return result;
    }

    public int addPlan(Plan plan) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(plan);
        //事务
        tx.commit();
        session.close();
        return 0;
    }

    public int deletePlan(int pId) {
        Plan plan = getPlan(pId);
        if(plan == null){
            return -1;
        }
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        plan.setDeleted(1);
        session.update(plan);
        tx.commit();
        session.close();
        return 0;
    }

    public List<Plan> getAllPlan() {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Plan> list;
        //查询
        int state = 1;
        String hql = "from Plan p where p.deleted != "+state;
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null && list.isEmpty())
            list=new ArrayList<Plan>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public void updatePlan(Plan plan) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(plan);
        //事务
        tx.commit();
        session.close();
    }

//    public static void  main(String[] args){
//        List<Plan> plans = DAOManager.planDao.getAllPlan();
//        for(Plan p : plans){
//            System.out.println(p.toString());
//        }
//    }
}
