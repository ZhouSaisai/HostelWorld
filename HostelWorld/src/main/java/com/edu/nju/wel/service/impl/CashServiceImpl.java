package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.Cash;
import com.edu.nju.wel.service.CashService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zs on 2017/3/18.
 */
@Service("CashService")
public class CashServiceImpl implements CashService{

    public List<Cash> getCashByVId(int id) {
        return DAOManager.cashDao.getCashByVId(id);
    }
}
