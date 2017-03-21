package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.RoomDao;
import com.edu.nju.wel.model.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
@Repository
public class RoomDaoImpl implements RoomDao {
    public List<Room> getRooms(int hId) {
        return null;
    }

    public Room getRoom(int rId) {
        return null;
    }

    public int addRoom(Room room) {
        return 0;
    }

    public int deleteRoom(int rId) {
        return 0;
    }
}
