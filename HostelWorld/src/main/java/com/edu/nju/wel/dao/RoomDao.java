package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Room;

import java.util.List;

/**
 * Created by zs on 2017/3/19.
 */
public interface RoomDao {
    //获得酒店对应的全部房间
    public List<Room> getRooms(int hId);

    //获得酒店对应的某房间
    public Room getRoom(int rId);

    //增加房间
    public int addRoom(Room room);

    //删除房间
    public int deleteRoom(int rId);

    //更新房间信息
    public int updateRoom(Room room);
}
