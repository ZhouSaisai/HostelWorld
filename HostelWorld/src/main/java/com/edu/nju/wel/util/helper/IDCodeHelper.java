package com.edu.nju.wel.util.helper;

/**
 * Created by zs on 2017/3/14.
 */
public class IDCodeHelper {
    public static String getID(){
        String base = "0123456789";
        int size = base.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * size);
            result.append(base.charAt(index));
        }
        return result.toString();
    }

}
