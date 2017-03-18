package com.edu.nju.wel.util.helper;

/**
 * Created by zs on 2017/3/18.
 */
public class PointHelper {
    public static String calLevel(int point){
        int result=0;
        //规则
        if(point<100){
            result = 0;
        }else if(point<200){
            result = 1;
        }else if(point<500){
            result = 2;
        }else if(point<1000){
            result = 3;
        }else if(point<1500){
            result = 4;
        }else if(point<2000){
            result = 5;
        }else if(point<3000){
            result = 6;
        }else if(point<5000){
            result = 7;
        }else if(point<8000){
            result = 8;
        }else if(point<12000){
            result = 9;
        }else{
            result = 10;
        }
        return "lv."+result;
    }
}
