package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Contract;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;

public class ContractDAO {

    private static final String ADD_AN_CONTRACT =
            "INSERT INTO [dbo].[Contracts]([room_id], [price], [start_date], [expiration], [deposit], [hostel_owner_id], [renter_id])\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public boolean addContract(Contract contract) {
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
                pst.setDouble(2, contract.getPrice());
                pst.setString(3, contract.getStartDate());
                pst.setString(4, contract.getExpiration());
                pst.setDouble(5, contract.getDeposit());
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


    public Contract getContract(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Contract contract = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT contract_id, room_id, price, start_date, expiration, deposit\n" +
                             "FROM Contracts\n" +
                             "WHERE room_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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

    public Contract getContractByRenterId(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Contract contract = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [contract_id], C.[room_id], [price], [start_date], [expiration], [deposit], [renter_id], [hostel_owner_id]\n" +
                        "FROM [dbo].[Contracts] AS C JOIN [dbo].[Accounts] AS A ON C.[renter_id] = A.[account_id]\n" +
                        "WHERE A.[account_id] = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, accId);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int contract_id = rs.getInt("contract_id");
                    int roomId = rs.getInt("room_id");
                    int price = rs.getInt("price");
                    String startDate = rs.getString("start_date");
                    String expiration = rs.getString("expiration");
                    int deposit = rs.getInt("deposit");
                    int renterId = rs.getInt("renter_id");
                    int ownerId = rs.getInt("hostel_owner_id");
                    contract = Contract.builder()
                            .contract_id(contract_id)
                            .room_id(roomId)
                            .price(price)
                            .startDate(startDate)
                            .expiration(expiration)
                            .deposit(deposit)
                            .renterId(renterId)
                            .hostelOwnerId(ownerId)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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
