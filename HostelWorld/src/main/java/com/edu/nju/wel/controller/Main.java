package com.edu.nju.wel.controller;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.HotelDecisionService;
import com.edu.nju.wel.service.OrderService;
import com.edu.nju.wel.service.PersonInfoService;
import com.edu.nju.wel.service.VIPModelService;
import com.edu.nju.wel.service.impl.HotelDecisionServiceImpl;
import com.edu.nju.wel.service.impl.OrderServiceImpl;
import com.edu.nju.wel.service.impl.PersonInfoServiceImpl;
import com.edu.nju.wel.service.impl.VIPModelServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by zs on 2017/6/27.
 */
public class Main {

    public static void main(String[] args) {
        HotelDecisionService service = new HotelDecisionServiceImpl();
        service.getHotelTypeAnalyse(26,3);
    }
//    public static void main(String[] args){
//        List<VIP> vips = DAOManager.vipDao.getVIPList();
//        for(int i=1;i<vips.size();i++){
//            if(i%3==0 || i%5==0){
//                continue;
//            }
//            VIP vip = vips.get(i);
//
//            List<Room> rooms = DAOManager.roomDao.getRooms(26);
//
//            int vIdInt = vip.getvId();
//            int numInt;
//
//            PersonInfoService person = new PersonInfoServiceImpl();
//            PersonInfo personInfo = person.getPersonById(vIdInt);
//            int level = personInfo.getLevel().charAt(3)-'0';
//
//            for(Room room:rooms){
//                if(i%27==0){
//                    numInt=2;
//                }else{
//                    numInt=1;
//                }
//                int rIdInt = room.getrId();
//
//                if(i%7==0){
//                    continue;
//                }
//
//                Double o_price = room.getPrice()*numInt*1.0;
//                Double n_price = o_price*(1-level*0.02);
//
//                Date randomDate=randomDate("2010-01-01","2017-06-21");
//                long time = randomDate.getTime()+1000*60*60*24;
//                Date endDate = new Date(time);
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                String start = format.format(randomDate);
//                String end = format.format(endDate);
//
//                Orders order = new Orders();
//                order.setNum(numInt);
//                order.setStart(start);
//                order.setEnd(end);
//                order.setPlan(0);
//                order.setOriginPrice(o_price);
//                order.setNowPrice(n_price);
//                order.setNames(vip.getName());
//                order.setState(2);
//
//                OrderService service = new OrderServiceImpl();
//                service.addOrder(vIdInt,rIdInt,order);
//            }
//        }
//
//
//    }


    private static Date randomDate(String beginDate,String  endDate ){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            //构造开始日期
            Date end = format.parse(endDate);
            //构造结束日期  //getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }




}
