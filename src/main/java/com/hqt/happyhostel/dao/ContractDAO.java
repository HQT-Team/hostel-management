package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Contract;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class ContractDAO {

    private static final String ADD_AN_CONTRACT =
            "INSERT INTO [dbo].[Contracts]([room_id], [price], [start_date], [expiration], [deposit], [hostel_owner_id], [renter_id], [status])\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CONTRACT_STATUS = "UPDATE Contracts SET status = 0\n" +
                    "WHERE room_id = ? AND renter_id = ? AND status = 1";

    public boolean updateContractStatus (int roomId, int renterAccountId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                pst = cn.prepareStatement(UPDATE_CONTRACT_STATUS);
                // Return key Identity of data just inserted
                pst.setInt(1, roomId);
                pst.setInt(2, renterAccountId);

                check = pst.executeUpdate() > 0;
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
        return check;
    }

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
                pst.setInt(8, contract.getStatus());

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
                String sql = "SELECT contract_id, room_id, price, start_date, expiration, deposit, hostel_owner_id, renter_id, status\n" +
                             "FROM Contracts\n" +
                             "WHERE room_id = ? AND status = 1";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int contract_id = rs.getInt("contract_id");
                    int price = rs.getInt("price");
                    String startDate = rs.getString("start_date");
                    String expiration = rs.getString("expiration");
                    int deposit = rs.getInt("deposit");
                    int hostelAccountId = rs.getInt("hostel_owner_id");
                    int renterAccountId = rs.getInt("renter_id");
                    int status = rs.getInt("status");
                    contract = Contract.builder()
                            .contract_id(contract_id)
                            .room_id(roomID)
                            .price(price)
                            .startDate(startDate)
                            .expiration(expiration)
                            .deposit(deposit)
                            .hostelOwnerId(hostelAccountId)
                            .renterId(renterAccountId)
                            .status(status)
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

    public ArrayList<Contract> GetListContractByHostelYear(String hostelName, String year) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Contract contract = null;
        String startYear1 = year +"-01-01";
        String endYear1 = year +"-12-31";
        ArrayList<Contract> contracts = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select A.contract_id, A.room_id, A.price, A.start_date, A.expiration, A.deposit, A.renter_id, A.hostel_owner_id, A.cancelDate\n" +
                        "from [dbo].[Contracts] A join [dbo].[Rooms] B on A.room_id = B.room_id join [dbo].[Hostels] C on B.hostel_id = c.hostel_id\n" +
                        "where C.name = ? and A.start_date between ? and ?";

                pst = cn.prepareStatement(sql);
                pst.setString(1, hostelName);
                pst.setString(2, startYear1);
                pst.setString(3, endYear1);

                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int contract_id = rs.getInt("contract_id");
                    int roomId = rs.getInt("room_id");
                    int price = rs.getInt("price");
                    String startDate = rs.getString("start_date");
                    String expiration = rs.getString("expiration");
                    int deposit = rs.getInt("deposit");
                    int renterId = rs.getInt("renter_id");
                    int ownerId = rs.getInt("hostel_owner_id");
                    String cancelDate = rs.getString("cancelDate");
                    contract = Contract.builder()
                            .contract_id(contract_id)
                            .room_id(roomId)
                            .price(price)
                            .startDate(startDate)
                            .expiration(expiration)
                            .deposit(deposit)
                            .renterId(renterId)
                            .hostelOwnerId(ownerId)
                            .cancelDate(cancelDate)
                            .build();
                    contracts.add(contract);
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
        return contracts;
    }

    public ArrayList<Contract> GetListContractByHostelYearQuater(String hostelName, String year, String quatertmp) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Contract contract = null;
        ArrayList<Contract> listContract = new ArrayList<>();
        String startDate;
        String endDate;
        int quater = 0;
        if (quatertmp.equals("quater_1"))
            quater = 1;
        if (quatertmp.equals("quater_2"))
            quater = 2;
        if (quatertmp.equals("quater_3"))
            quater = 3;
        if (quatertmp.equals("quater_4"))
            quater = 4;

        if (quater == 1){
            startDate = year + "/01/01";
            endDate = year + "/03/31";
        } else if (quater == 2) {
            startDate = year + "/04/01";
            endDate = year + "/06/30";
        } else if (quater == 3) {
            startDate = year + "/07/01";
            endDate = year + "/9/30";
        }else if (quater == 4){
            startDate = year + "/10/01";
            endDate = year + "/12/31";
        }else{
            startDate = year + "/01/01";
            endDate = year + "/12/31";
        }
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select A.contract_id, A.room_id, A.price, A.start_date, A.expiration, A.deposit, A.renter_id, A.hostel_owner_id , A.status, A.cancelDate \n" +
                        "from [dbo].[Contracts] A join [dbo].[Rooms] B on A.room_id = B.room_id join [dbo].[Hostels] C on B.hostel_id = C.hostel_id\n" +
                        "where C.name = ? and A.start_date between ? and ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, hostelName);
                pst.setString(2, startDate);
                pst.setString(3, endDate);

                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int contract_id = rs.getInt("contract_id");
                    int roomId = rs.getInt("room_id");
                    int price = rs.getInt("price");
                    String startDate1 = rs.getString("start_date");
                    String expiration = rs.getString("expiration");
                    int deposit = rs.getInt("deposit");
                    int renterId = rs.getInt("renter_id");
                    int ownerId = rs.getInt("hostel_owner_id");
                    String cancelDate = rs.getString("cancelDate");
                    contract = Contract.builder()
                            .contract_id(contract_id)
                            .room_id(roomId)
                            .price(price)
                            .startDate(startDate1)
                            .expiration(expiration)
                            .deposit(deposit)
                            .renterId(renterId)
                            .hostelOwnerId(ownerId)
                            .cancelDate(cancelDate)
                            .build();
                    listContract.add(contract);
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
        return listContract;
    }
}
