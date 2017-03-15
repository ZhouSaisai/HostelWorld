package com.edu.nju.wel.service;

import com.edu.nju.wel.info.PersonInfo;
import com.edu.nju.wel.util.exception.NotExistException;
import com.edu.nju.wel.util.exception.WrongPasswordException;

/**
 * Created by zs on 2017/3/14.
 */
public interface PersonLoginService{
    public PersonInfo askRegister(PersonInfo person);

    public PersonInfo askLogin(PersonInfo person)throws NotExistException,WrongPasswordException;
}
