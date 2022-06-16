package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Room;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RoomInviteDAO {

    public Room getRoomInviteById(int idRoom) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Room room = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Select [room_id], [hostel_id], [invite_code], [QRcode], [expiredTimeCode]\n" +
                             "From [dbo].[Rooms]\n" +
                             "Where [room_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, idRoom);
                rs = pst.executeQuery();
                if (rs!= null && rs.next()){
                    int roomId = rs.getInt("room_id");
                    int hostelId = rs.getInt("hostel_id");
                    String inviteCode = rs.getString("invite_code");
                    String QRCode = rs.getString("QRcode");

                    Timestamp endTime = rs.getTimestamp("expiredTimeCode");
                    room = Room.builder()
                            .roomId(roomId)
                            .hostelId(hostelId)
                            .inviteCode(inviteCode)
                            .QRCode(QRCode)
                            .expiredTimeCode(endTime)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return room;
    }

    public Room getRoomIdByInviteCode(String inviteCode) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Room room = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Select [room_id], [hostel_id], [invite_code], [QRcode], [expiredTimeCode], [room_status]\n" +
                             "From [dbo].[Rooms]\n" +
                             "Where [invite_code] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, inviteCode);
                rs = pst.executeQuery();
                if (rs!= null && rs.next()){
                    int roomId = rs.getInt("room_id");
                    int hostelId = rs.getInt("hostel_id");
                    String InviteCode = rs.getString("invite_code");
                    String QRCode = rs.getString("QRcode");
                    int status = rs.getInt("room_status");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Timestamp endTime = rs.getTimestamp("expiredTimeCode");
                    room = Room.builder()
                            .roomId(roomId)
                            .hostelId(hostelId)
                            .inviteCode(inviteCode)
                            .QRCode(QRCode)
                            .expiredTimeCode(endTime)
                            .roomStatus(status)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return room;
    }

    public boolean updateRoomInviteCode(int idRoom, String inviteCode, String QRCode, String endTime) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "Update [dbo].[Rooms]\n" +
                             "Set  [invite_code] = ?, [QRcode] = ? , [expiredTimeCode] = ?\n" +
                             "Where [room_id] = ? AND [room_status] = 1";
                pst = cn.prepareStatement(sql);
                pst.setString(1, inviteCode);
                pst.setString(2, QRCode);
                pst.setString(3, endTime);
                pst.setInt(4, idRoom);
                if (pst.executeUpdate() < 1) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }
}
