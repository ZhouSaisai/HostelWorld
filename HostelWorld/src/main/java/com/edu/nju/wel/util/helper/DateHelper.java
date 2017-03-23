package com.edu.nju.wel.util.helper;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by zs on 2017/3/22.
 */
public class DateHelper {
    public static int calDays(String start,String end){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Long c = null;
        try {
            c = sf.parse(end).getTime()-sf.parse(start).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long d = c/1000/60/60/24;//天
        System.out.println(d+"天");
        return (int)d;
    }

    /**
     * 计算当前日期是否在计划内
     * @param start 计划开始时间
     * @param end 计划结束时间
     * @param from 入住时间
     * @param to 离店时间
     * @return
     */
    public static int calPeriodDays(String from,String to,String start,String end){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Long c = null;
        try {
            c = sf.parse(start).getTime()-sf.parse(to).getTime();
            //计划还未开始
            if(c>=0){
                return -1;
            }
            c = sf.parse(end).getTime()-sf.parse(to).getTime();
            //计划有交叉
            if(c>=0){
                c = sf.parse(start).getTime()-sf.parse(from).getTime();
                if(c>=0){
                    c = sf.parse(to).getTime()-sf.parse(start).getTime();
                    return (int)(c/1000/60/60/24);
                }
                return DateHelper.calDays(from,to);
            }
            c = sf.parse(end).getTime()-sf.parse(from).getTime();
            //计划有交叉
            if(c>=0) {
                return (int)(c/1000/60/60/24)+1;
            }
            return -1;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

//    public static void main(String[] args){
//        DateHelper.calDays("2017-03-01","2017-03-01");
//        DateHelper.calDays("2017-03-01","2017-03-02");
//        DateHelper.calDays("2017-02-01","2017-03-01");
//        System.out.println(DateHelper.calPeriodDays("2017-02-01","2017-03-01","2017-03-01","2017-04-01"));
//        System.out.println(DateHelper.calPeriodDays("2017-02-01","2017-03-10","2017-03-01","2017-04-01"));
//        System.out.println(DateHelper.calPeriodDays("2017-03-10","2017-03-20","2017-03-01","2017-04-01"));
//        System.out.println(DateHelper.calPeriodDays("2017-03-20","2017-04-21","2017-03-01","2017-04-01"));
//        System.out.println(DateHelper.calPeriodDays("2017-04-01","2017-05-01","2017-03-01","2017-04-01"));
//    }
}
