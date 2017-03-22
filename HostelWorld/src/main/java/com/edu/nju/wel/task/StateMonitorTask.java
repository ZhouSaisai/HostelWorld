package com.edu.nju.wel.task;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.Plan;
import com.edu.nju.wel.model.VIP;
import com.edu.nju.wel.util.helper.DateHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Component
public class StateMonitorTask {
    //每天凌晨5点触发
    @Scheduled(cron="0 0 5 * * ? ")
//    @Scheduled(cron="0 0/12 16 * * ? ")//间隔5min执行
    public void stateMonitor(){

        System.out.println("这是一个定时器更新输出");
        List<VIP> list = DAOManager.vipDao.getVIPList();
        for(VIP vip : list){
            //获取客户的存储时间和状态
            Timestamp vTime = vip.getTime();
            int state = vip.getState();
            double money = vip.getMoney();
            //正常状态下
            if(state==0 && money-1000<0.00001){
                Calendar calendar = Calendar.getInstance();
                //把当前时间减去1年
                calendar.add(Calendar.YEAR,-1);
                Timestamp time = new Timestamp(calendar.getTimeInMillis());
                //判断是否过期，true就是超过1年了需要暂停
                if(time.after(vTime)){
                    vip.setState(1);
                    //更新状态
                    DAOManager.vipDao.updateVIP(vip);
                    continue;
                }
            }
            if(state==1){
                Calendar calendar = Calendar.getInstance();
                //把当前时间减去1年
                calendar.add(Calendar.YEAR,-2);
                Timestamp time = new Timestamp(calendar.getTimeInMillis());
                //判断是否过期，true就是超过1年了需要暂停
                if(time.after(vTime)){
                    vip.setState(2);
                    //更新状态
                    DAOManager.vipDao.updateVIP(vip);
                }
            }
        }
        System.out.println("这是一个定时器更新输出-2");
//        List<Plan> plans = DAOManager.planDao.getAllPlan();
//        for(Plan plan : plans){
//            System.out.println(plan.toString());
//            if(plan.getDeleted()==0){
//                Date end = plan.getEnd();
//                System.out.println(plan.toString());
//                if(DateHelper.isAfter(end)){
//                    plan.setDeleted(2);
//                    DAOManager.planDao.updatePlan(plan);
//                }
//            }
//        }
    }


}
