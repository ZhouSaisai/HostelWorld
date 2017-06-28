package com.edu.nju.wel.util.helper;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by zs on 2017/6/27.
 */
public class RomdomTextHelper {
    public static String getRandomChar() {
        String str = "";
        int hightPos, lowPos; // 定义高低位
        Random random = new Random();
        for(int i=0;i<2;i++){
            hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = str+new String(b, "GBk");//转成中文
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(str);
        return str;
    }

//    public static void main(String[] args){
//        RomdomTextHelper.getRandomChar();
//    }
}
