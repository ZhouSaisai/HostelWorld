package com.edu.nju.wel.service;


import com.edu.nju.wel.model.Orders;

import java.util.List;

/**
 * Created by zs on 2017/3/24.
 */
public interface OrderService {
    public String addOrder(int vId,int rId,Orders order);

    public List<Orders> getMyOrdersByState(int vId,int state);

    public List<Orders> getHotelOrderByState(int hId,int state);

    public String cancelOrder(int oIdInt);

    public String checkOrder(int hId,String code);

    public Orders getOrderByCode(String code);

    public String checkIn(String code, String names, String roomIds);

    public String checkOut(int oIdInt);

    public String addOrderNv(int i, int rIdInt, Orders order);
}
