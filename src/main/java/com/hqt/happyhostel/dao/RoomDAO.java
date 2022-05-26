package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDAO {

    public static boolean addNewRoom(int hostelID, String roomNumber, int capacity, double roomArea, int attic,
                                     String infrastructure1, int quantity1, int status1,
                                     String infrastructure2,int quantity2, int status2,
                                     String infrastructure3, int quantity3, int status3) {
        Connection cn = null;
        Boolean isInserted = false;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, has_attic, room_status)\n" +
                        "VALUES (?, ?, ?, ?, ?, 1)\n" +
                        "DECLARE @roomID varchar(20) = SCOPE_IDENTITY()\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setString(2, roomNumber);
                pst.setInt(3, capacity);
                pst.setDouble(4, roomArea);
                pst.setInt(5, attic);

                pst.setString(6, infrastructure1);
                pst.setInt(7, quantity1);
                pst.setInt(8, status1);

                pst.setString(9, infrastructure2);
                pst.setInt(10, quantity2);
                pst.setInt(11, status2);

                pst.setString(12, infrastructure3);
                pst.setInt(13, quantity3);
                pst.setInt(14, status3);

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
