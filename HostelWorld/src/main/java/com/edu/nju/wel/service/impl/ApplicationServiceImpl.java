package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.info.HotelInfo;
import com.edu.nju.wel.model.Hotel;
import com.edu.nju.wel.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
@Service("ApplicationService")
public class ApplicationServiceImpl implements ApplicationService {

    public List<Hotel> getOpenApplicationHotels() {
        List<Hotel> hotels = null;
        hotels=DAOManager.hotelDao.getOpenHotelList();
        for (hotels)
        return hotels;
    }

    public List<Hotel> getModifyApplicationHotels() {
        return null;
    }
}
