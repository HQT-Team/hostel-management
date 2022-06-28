package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.BillDetail;
import com.hqt.happyhostel.dto.Payment;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    private static final String INSERT_NEW_BILL = "INSERT INTO Bill (total_money, created_date, bill_title, expired_payment_date, status, payment_id, room_id)\n" +
            "VALUES (?, GETDATE(), ?, ?, 0, NULL, ?)";

    private static final String INSERT_NEW_BILL_DETAIL = "INSERT INTO BillDetail (consumeIDStart, consumeIDEnd, accountHostelOwnerID, accountRenterID, bill_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";

    // This function help you can view the service at the time hostel has, maybe now these functions are expanded - so we need this table to check and view for history
    private static final String INSERT_NEW_BILL_SERVICE = "INSERT INTO BillService (bill_detail_id, hostel_service_id) VALUES (?, ?)";

    // After insert new bill, you need to set all old consume to 1, and insert 1 new consume start with status = 0
    private static final String INSERT_NEW_BILL_TAIL = "UPDATE Consumes SET status = 1 WHERE room_id = 1\n" +
            "INSERT INTO Consumes (number_electric, number_water, update_date, status, room_id) VALUES (?, ?, GETDATE(), 0, ?)\n";

    public boolean InsertANewBill(int totalMoney, String billTitle, String expiredPaymentDate, int roomID,
                                  int consumeIDStart, int consumeIDEnd, int accountHostelOwner, int accountRenterID,
                                  int numberLastElectric, int numberLastWater,
                                  ArrayList<Integer> hostelServiceList) {
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                int billID = 0;
                int billDetailID = 0;

                // Insert new Bill
                ptm = cn.prepareStatement(INSERT_NEW_BILL, Statement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, totalMoney);
                ptm.setString(2, billTitle);
                ptm.setString(3, expiredPaymentDate);
                ptm.setInt(4, roomID);
                check = ptm.executeUpdate() > 0;

                rs = ptm.getGeneratedKeys();

                if (rs.next()) {
                    billID = rs.getInt(1);
                }

                // Insert Bill Detail
                ptm = cn.prepareStatement(INSERT_NEW_BILL_DETAIL, Statement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, consumeIDStart);
                ptm.setInt(2, consumeIDEnd);
                ptm.setInt(3, accountHostelOwner);
                ptm.setInt(4, accountRenterID);
                ptm.setInt(5, billID);
                check = ptm.executeUpdate() > 0;

                rs = ptm.getGeneratedKeys();
                if (rs.next()) {
                    billDetailID = rs.getInt(1);
                }

                // Insert Many Bill_Service
                ptm = cn.prepareStatement(INSERT_NEW_BILL_SERVICE);
                for (Integer hostelServiceID : hostelServiceList) {
                    ptm.setInt(1, billDetailID);
                    ptm.setInt(2, hostelServiceID);
                    if (ptm.executeUpdate() == 0) {
                        check = false;
                        break;
                    }
                }

                // Do the rest of insert app
                ptm = cn.prepareStatement(INSERT_NEW_BILL_TAIL);
                ptm.setInt(1, numberLastElectric);
                ptm.setInt(2, numberLastWater);
                ptm.setInt(3, roomID);
                check = ptm.executeUpdate() > 0;

                if (!check) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
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
            if (ptm != null) {
                try {
                    ptm.close();
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
        return check;
    }

    public Bill getLastBill(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Bill bill = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 bill_id, total_money, created_date, bill_title, expired_payment_date, payment_date, status, Bill.payment_id as 'payment_id'\n" +
                        "FROM Bill, Payment\n" +
                        "WHERE room_id = ?\n" +
                        "ORDER BY created_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int billID = rs.getInt("bill_id");
                    int totalMoney = rs.getInt("total_money");
                    String createdDate = rs.getString("created_date");
                    String billTitle = rs.getString("bill_title");
                    String expiredPaymentDate = rs.getString("expired_payment_date");
                    String paymentDate = rs.getString("payment_date");
                    int status = rs.getInt("status");
                    if (rs.getString("payment_id") == null) {
                        bill = new Bill(billID, roomID, totalMoney, createdDate, billTitle, expiredPaymentDate, paymentDate, status, new Payment(0, null));
                    } else {
                        int paymentID = rs.getInt("payment_id");
                        String paymentName = getPaymentName(paymentID);
                        bill = new Bill(billID, roomID, totalMoney, createdDate, billTitle, expiredPaymentDate, paymentDate, status, new Payment(paymentID, paymentName));
                    }
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
        return bill;
    }

    public String getPaymentName(int paymentID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String paymentName = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT payment_name FROM Payment WHERE payment_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, paymentID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    paymentName = rs.getString("payment_name");
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
        return paymentName;
    }

    public BillDetail getBillDetail(int billID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BillDetail billDetail = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT bill_detail_id, consumeIDStart, consumeIDEnd, accountHostelOwnerID, accountRenterID \n" +
                        "FROM BillDetail WHERE bill_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, billID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int billDetailID = rs.getInt("bill_detail_id");
                    int consumeIDStart = rs.getInt("consumeIDStart");
                    int consumeIDEnd = rs.getInt("consumeIDEnd");
                    int accountHostelOwnerID = rs.getInt("accountHostelOwnerID");
                    int accountRenterID = rs.getInt("accountRenterID");
                    billDetail = new BillDetail(billDetailID, consumeIDStart, consumeIDEnd, accountHostelOwnerID, accountRenterID);
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
        return billDetail;
    }

    public String getBillTitle(int roomID, String contractStartDate) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String billTitle = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 bill_title\n" +
                        "FROM Bill\n" +
                        "WHERE room_id = ?\n" +
                        "AND created_date > ?\n" +
                        "ORDER BY created_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);
                pst.setString(2, contractStartDate);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    billTitle = rs.getString("bill_title");
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
        return billTitle;
    }

    public List<Bill> getListBillByRoomID(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Bill> bills = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "\n" +
                        "SELECT bill_id, total_money, created_date, bill_title, expired_payment_date, payment_date, status, payment_id\n" +
                        "FROM Bill\n" +
                        "WHERE room_id = ?\n" +
                        "ORDER BY created_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null) {
                    while(rs.next()) {
                        int billID = rs.getInt("bill_id");
                        int totalMoney = rs.getInt("total_money");
                        String createdDate = rs.getString("created_date");
                        String billTitle = rs.getString("bill_title");
                        String expiredPaymentDate = rs.getString("expired_payment_date");
                        String paymentDate = rs.getString("payment_date");
                        int status = rs.getInt("status");
                        if (rs.getString("payment_id") == null) {
                            bills.add(new Bill(billID, roomID, totalMoney, createdDate, billTitle, expiredPaymentDate, paymentDate, status, new Payment(0, null)));
                        } else {
                            int paymentID = rs.getInt("payment_id");
                            String paymentName = getPaymentName(paymentID);
                            bills.add(new Bill(billID, roomID, totalMoney, createdDate, billTitle, expiredPaymentDate, paymentDate, status, new Payment(paymentID, paymentName)));
                        }
                    }
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
        return bills;
    }



}
