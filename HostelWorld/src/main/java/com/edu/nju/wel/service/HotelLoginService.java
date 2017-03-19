package com.edu.nju.wel.service;

import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.util.exception.NotExistException;
import com.edu.nju.wel.util.exception.WrongPasswordException;

/**
 * Created by zs on 2017/3/19.
 */
public interface HotelLoginService {
    public HotelInfo askRegister(HotelInfo hotel);

    public HotelInfo askLogin(HotelInfo info)throws NotExistException,WrongPasswordException;
}
