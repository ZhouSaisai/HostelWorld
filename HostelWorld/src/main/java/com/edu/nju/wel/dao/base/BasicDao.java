package com.edu.nju.wel.dao.base;

import com.edu.nju.wel.util.exception.NotExistException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zs on 2017/3/9.
 */
public interface BasicDao<T> {
    /*
    这里是真正会使用到的方法
    其中对于删改查三种操作，数据库中可能并不存在其对应项，抛出NotExistException
     */

    /*
    增
     */
    public boolean insertOne(T o);

    public boolean insertList(List<T> list);

    /*
    删
     */
    public boolean deleteOne(Serializable id) throws NotExistException;

    public boolean deleteList(List<T> list) throws NotExistException;

    /*
    改
     */
    public boolean updateOne(T entity) throws NotExistException;

    public boolean updateList(List<T> list) throws NotExistException;

    /*
    查
     */
    public T getById(Serializable id) throws NotExistException;

    public List<T> findByProperty(String name, Object value) throws NotExistException;

    public List<T> findByProperty(Map<String, Object> conditionMap) throws NotExistException;
}
