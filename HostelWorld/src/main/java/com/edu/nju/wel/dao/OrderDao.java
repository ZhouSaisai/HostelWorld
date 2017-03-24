package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Orders;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
public interface OrderDao {
    //获得酒店对应的全部订单
    public List<Orders> getOrdersByHId(int rId,int state);

    //根据状态，获得用户对应的全部订单
    public List<Orders> getOrdersByVId(int vId, int state);

    //根据订单识别码获取订单
    public Orders getOrderByCode(String code);

    //增加订单
    public int addOrder(Orders orders);

    //更新订单
    public int updateOrder(Orders orders);
    //根据订单Id获得房间号
    public Orders getOrderByOId(int oIdInt);
}
