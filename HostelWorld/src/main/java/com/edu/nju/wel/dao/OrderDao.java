package com.edu.nju.wel.dao;

import com.edu.nju.wel.info.*;
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



    //增加订单
    public int addOrder(Orders orders);
    //更新订单
    public int updateOrder(Orders orders);

    //根据订单Id获得订单信息
    public Orders getOrderByOId(int oIdInt);
    //根据订单识别码获取订单
    public Orders getOrderByCode(String code);

    //用户订单分析
    public List<Orders> getVipOrderAnalyse(int vId);

    //会员订单完成率
    public double getVidFinishRate(int vId);

    //订单单客户下单的客栈数
    public int getVipHotelNum(int vId);
    //订单单客栈下单的客户数
    public int getHotelVipNum(int hId);

    //客栈订单分析
    public List<Orders> getHotelOrderAnalyse(int id);

    //网站订单分析
    public List<Orders> getAllOrderAnalyse();

    public List<TimeAnalyse> getVipOrderByTime(int vId, int areaId, int type);

    public List<MonthAnalyse> getHotelOrderByTime(int hIdInt, int type);

    public List<TypeAnalyse> getHotelTypeByTime(int hIdInt, int type);

    public PieInfo getHotelPie(int hIdInt);

    public List<AddInfo> getHotelAdd(int hIdInt);
}
