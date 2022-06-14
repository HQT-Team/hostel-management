package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HostelOwnerDAO {

    public static boolean checkOwnerHostel(int accId, int hostelId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [hostel_id]\n" +
                        "FROM [dbo].[Hostels] JOIN [dbo].[Accounts] ON [owner_account_id] = [account_id]\n" +
                        "WHERE [hostel_id] = ? AND [account_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelId);
                pst.setInt(2, accId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    result = true;
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
        return result;
    }

    public static boolean checkOwnerRoom(int accId, int roomId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT R.[room_id]\n" +
                        "FROM [dbo].[Rooms] AS R JOIN [dbo].[Hostels] AS H ON R.[hostel_id] = H.[hostel_id]\n" +
                        "WHERE R.[room_id] = ? AND H.[owner_account_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomId);
                pst.setInt(2, accId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    result = true;
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
        return result;
    }
}