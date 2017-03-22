package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.RoomDao;
import com.edu.nju.wel.model.Room;
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
 * Created by zs on 2017/3/19.
 */
@Repository
public class RoomDaoImpl implements RoomDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public List<Room> getRooms(int hId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Room> list;
        //查询
        String hql = "from Room r where r.deleted = 0 and r.hotel.hId= "+ hId;
        Query query=session.createQuery(hql);
        list=query.list();
        if(list==null)
            list=new ArrayList<Room>();
        //事务
        tx.commit();
        session.close();
        return list;
    }

    public Room getRoom(int rId) {
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Room result = null;
        //查询
        Criteria criteria = session.createCriteria(Room.class);
        criteria.add(Expression.eq("rId",rId));
        List<Room> list=criteria.list();
        if(list!=null && !list.isEmpty()){
            result=list.get(0);
        }
        //事务
        tx.commit();
        session.close();
        return result;
    }

    public int addRoom(Room room) {
        //添加房型
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(room);
        //事务
        tx.commit();
        session.close();
        return 0;
    }

    public int deleteRoom(int rId) {
        Room room = getRoom(rId);
        if(room == null){
            return -1;
        }
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        room.setDeleted(1);
        session.update(room);
        tx.commit();
        session.close();
        return 0;
    }
}
