package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDAO {

    public static boolean addNewRoom(int hostelID, String roomNumber, int capacity, double roomArea) {
        Connection cn = null;
        Boolean isInserted = false;
        PreparedStatement pst = null;
        Integer roomStatus = 1;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, room_status) \n" +
                        "VALUES (?, ?, ?, ?, roomStatus)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setString(2, roomNumber);
                pst.setInt(3, capacity);
                pst.setDouble(4, roomArea);
                int rows = pst.executeUpdate();
                if (rows != 0) {
                    isInserted = true;
                } else {
                    isInserted = false;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isInserted;
    }

}
