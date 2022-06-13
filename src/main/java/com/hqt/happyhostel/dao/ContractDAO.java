package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Contract;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;

public class ContractDAO {
    private static final String ADD_AN_CONTRACT = "INSERT INTO [dbo].[Contracts]([room_id], [price], [start_date], [expiration], [deposit], [hostel_owner_id], [renter_id])\n" +
            "VALUES ?, ?, ?, ?, ?, ?, ?";

    public static boolean addContract(Contract contract) {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Stop auto commit for rollback if transaction insert data have any problem
                cn.setAutoCommit(false);

                // Add into Contracts table
                pst = cn.prepareStatement(ADD_AN_CONTRACT);
                // Return key Identity of data just inserted
                pst.setInt(1, contract.getRoom_id());
                pst.setInt(2, contract.getPrice());
                pst.setString(3, contract.getStartDate());
                pst.setString(4, contract.getExpiration());
                pst.setInt(5, contract.getDeposit());
                pst.setInt(6, contract.getHostelOwnerId());
                pst.setInt(7, contract.getRenterId());

                if (pst.executeUpdate() > 0) {
                    check = true;
                    cn.setAutoCommit(true);
                } else {
                    cn.rollback();
                    cn.setAutoCommit(true);
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
        return check;
    }


    public static Contract getContract(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Contract contract = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT contract_id, room_id, price, start_date, expiration, deposit\n" +
                        "FROM Contracts\n" +
                        "WHERE room_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int contract_id = rs.getInt("contract_id");
                    int price = rs.getInt("price");
                    String startDate = rs.getString("start_date");
                    String expiration = rs.getString("expiration");
                    int deposit = rs.getInt("deposit");
                    contract = Contract.builder()
                            .contract_id(contract_id)
                            .room_id(roomID)
                            .price(price)
                            .startDate(startDate)
                            .expiration(expiration)
                            .deposit(deposit)
                            .build();
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
        return contract;
    }
}
