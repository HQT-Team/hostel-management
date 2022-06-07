//package com.hqt.happyhostel.dao;
//
//import com.hqt.happyhostel.dto.Account;
//import com.hqt.happyhostel.dto.Contract;
//import com.hqt.happyhostel.utils.DBUtils;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class ContractDAO {
//    private static final String ADD_AN_CONTRACT = "INSERT INTO [dbo].[Contracts]([room_id], [price], [start_date], [expiration], [deposit], [hostel_owner_id], [renter_id])\n" +
//            "     VALUES(?, ?, ?, ?, ?, ?, ?)";
//
//    public static boolean addContract(Contract contract) {
//        boolean check = false;
//        Connection cn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        try {
//            cn = DBUtils.makeConnection();
//            if (cn != null) {
//                // Stop auto commit for rollback if transaction insert data have any problem
//                cn.setAutoCommit(false);
//
//                // Add into Contracts table
//                pst = cn.prepareStatement(ADD_AN_CONTRACT, Statement.RETURN_GENERATED_KEYS);
//                // Return key Identity of data just inserted
//                pst.setString(1, account.getUsername());
//                pst.setString(2, account.getPassword());
//                pst.setInt(3, account.getStatus());
//                pst.setInt(4, account.getRole());
//
//                if (pst.executeUpdate() > 0) {
//
//                    int accountId = -1;
//                    rs = pst.getGeneratedKeys();
//
//                    if (rs.next()) {
//                        accountId = rs.getInt(1);
//                    }
//
//                    // Add into AccountInformations table
//
//                    if (pst.executeUpdate() > 0) {
//                        check = true;
//                        cn.commit();
//                    } else {
//                        cn.rollback();
//                    }
//                    cn.setAutoCommit(true);
//                } else {
//                    cn.rollback();
//                    cn.setAutoCommit(true);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pst != null) {
//                try {
//                    pst.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if (cn != null) {
//                try {
//                    cn.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return check;
//    }
//}
