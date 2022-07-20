package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.BillDetail;
import com.hqt.happyhostel.dto.Payment;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private static final String UPDATE_BILL_STATUS = "UPDATE [dbo].[Bill] SET [status] = ?, [payment_date] = ?, [payment_id]= ? WHERE [bill_id] = ?";

    private static final String Get_List_Bill_By_Quarter_And_Hostel = "select A.bill_id, A.bill_title, A.created_date, A.expired_payment_date , A.payment_date,A.payment_id, A.room_id, A.status, A.total_money\n" +
            "from [dbo].[Bill] A join [dbo].[Rooms] B on A.room_id = B.room_id join  [dbo].[Hostels] C on B.hostel_id = C.hostel_id\n" +
            "where C.name = ? and A.created_date between ? and ?";


    private static final String Get_List_Bill_By_Year = "select A.bill_id, A.bill_title, A.created_date, A.expired_payment_date , A.payment_date,A.payment_id, A.room_id, A.status, A.total_money \n" +
            "from [dbo].[Bill] A join [dbo].[Rooms] B on A.room_id = B.room_id join  [dbo].[Hostels] C on B.hostel_id = C.hostel_id\n" +
            "where C.name = ? and A.created_date between ? and ?";
    private static final String Get_List_Bill_By_Hostel = "select A.bill_id, A.bill_title, A.created_date, A.expired_payment_date , A.payment_date,A.payment_id, A.room_id, A.status, A.total_money\n" +
            "from [dbo].[Bill] A join [dbo].[Rooms] B on A.room_id = B.room_id join [dbo].[Hostels] C on B.hostel_id = C.hostel_id\n" +
            "where C.name = ?";
    private static final String GET_BILL_BY_RENTER_ID = "SELECT Bill.bill_id, Bill.total_money, Bill.created_date, Bill.bill_title, \n" +
            "Bill.expired_payment_date, Bill.payment_date, Bill.status, Bill.payment_id, Bill.room_id\n" +
            "FROM Accounts INNER JOIN Contracts ON Accounts.account_id=Contracts.renter_id\n" +
            "INNER JOIN Rooms ON Contracts.room_id=Rooms.room_id\n" +
            "INNER JOIN Bill ON Contracts.room_id=Bill.room_id\n" +
            "WHERE Accounts.account_id = ?";
    private static final String GET_RENTER_BILL_BY_ID =
            "SELECT bill_id, total_money, created_date, bill_title, \n" +
                    "expired_payment_date, payment_date, status, payment_id, room_id\n" +
                    "FROM Bill \n" +
                    "WHERE bill_id = ?";
    private static final String Get_List_Bill_By_Quarter = "SELECT bill_id, total_money, created_date, bill_title,expired_payment_date, payment_date, status, payment_id, room_id FROM [dbo].[Bill] WHERE created_date BETWEEN  ? AND ?";

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

    public Bill getBillByID(int billID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Bill bill = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT room_id, total_money, created_date, bill_title, expired_payment_date, payment_date, status, Bill.payment_id as 'payment_id'\n" +
                        "FROM Bill\n" +
                        "WHERE bill_id = ?\n";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, billID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int roomID = rs.getInt("room_id");
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

    public Bill getBillById(int billId) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Bill bill = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [bill_id], [total_money], [created_date], [bill_title], [expired_payment_date], [payment_date], [status], [payment_id], [room_id]\n" +
                        "FROM [dbo].[Bill]\n" +
                        "WHERE [bill_id] = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, billId);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    billId = rs.getInt("bill_id");
                    int roomId = rs.getInt("");
                    int totalMoney = rs.getInt("total_money");
                    String createdDate = rs.getString("created_date");
                    String billTitle = rs.getString("bill_title");
                    String expiredPaymentDate = rs.getString("expired_payment_date");
                    String paymentDate = rs.getString("payment_date");
                    int status = rs.getInt("status");
                    int paymentId = rs.getInt("payment_id");
                    String paymentName = getPaymentName(paymentId);
                    Payment payment =
                            Payment.builder()
                                    .paymentID(paymentId)
                                    .paymentName(paymentName)
                                    .build();
                    bill = Bill.builder()
                            .billID(billId)
                            .roomID(roomId)
                            .totalMoney(totalMoney)
                            .createdDate(createdDate)
                            .billTitle(billTitle)
                            .expiredPaymentDate(expiredPaymentDate)
                            .paymentDate(paymentDate)
                            .status(status)
                            .payment(payment)
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
        return bill;
    }

    public boolean updateBillStatus(int billId, int status, String payDate, int payId) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = UPDATE_BILL_STATUS;

                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, payDate);
                pst.setInt(3, payId);
                pst.setInt(4, billId);


                if (pst.executeUpdate() > 0) {
                    result = true;
                    cn.commit();
                } else {
                    cn.rollback();
                }
                cn.setAutoCommit(true);
            }
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
        return result;
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
                    while (rs.next()) {
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


    public List<Bill> getBllListByRenterID(int renterID) throws SQLException {
        List<Bill> billList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(GET_BILL_BY_RENTER_ID);
            ptm.setInt(1, renterID);
            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                billList.add(Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return billList;
    }

    public Bill getRenterBillByID(int billID) throws SQLException {
        Bill bill = null;
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(GET_RENTER_BILL_BY_ID);
            ptm.setInt(1, billID);
            rs = ptm.executeQuery();
            if (rs != null && rs.next()) {
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return bill;
    }

    public ArrayList<Bill> GetListBillAccordingQuarter(String startDate, String endDate) throws Exception {
        Bill bill = null;
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //select * from [dbo].[Bill] where created_date BETWEEN  '2022-06-22' AND '2022-10-22'
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(Get_List_Bill_By_Quarter);
            ptm.setString(1, startDate);
            ptm.setString(2, endDate);
            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }

    public ArrayList<Bill> GetListBillAccordingYear(String startYear) throws Exception {
        Bill bill = null;
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endYear = startYear + "12-31";
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(Get_List_Bill_By_Year);
            ptm.setString(1, startYear);
            ptm.setString(2, endYear);
            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }


    public ArrayList<Bill> GetListBillByHostelYearQuater(String hostelName,  String year, String quatertmp) throws Exception {
year = year == null ? "2022" : year;
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
        Bill bill = null;
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(Get_List_Bill_By_Quarter_And_Hostel);
            ptm.setString(1, hostelName);
            ptm.setString(2, startDate);
            ptm.setString(3, endDate);
            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }

    public ArrayList<Bill> GetListBillByHostel(String hostelName) throws Exception {
        Bill bill = null;
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //select * from [dbo].[Bill] where created_date BETWEEN  '2022-06-22' AND '2022-10-22'
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(Get_List_Bill_By_Hostel);
            ptm.setString(1, hostelName);
            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                //A.bill_id, A.bill_title, A.created_date, A.expired_payment_date , A.payment_date, A.status, A.total_money
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }

    public ArrayList<Bill> GetListBillByHostelAndYear(String hostel, String Year) throws Exception {
        Year = Year == null ? "2022" : Year;
        Bill bill = null;
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startYear = Year + "-01-01";
        String endYear = Year + "-12-31";
        try {
            cn = DBUtils.makeConnection();
            ptm = cn.prepareStatement(Get_List_Bill_By_Year);
            ptm.setString(1, hostel);
            ptm.setString(2, startYear);
            ptm.setString(3, endYear);
            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }

    public ArrayList<Bill> GetListBillByHostelIdMonthYear(int hostelId, int year , int month) throws Exception {
        Bill bill = null;
        String strYear = String.valueOf(year);
        year = Integer.parseInt(strYear.substring(2,4));
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            cn = DBUtils.makeConnection();
            String sql = "select A.bill_id, A.bill_title, A.created_date, A.expired_payment_date , A.payment_date,A.payment_id, A.room_id, A.status, A.total_money\n" +
                    "from [dbo].[Bill] A join [dbo].[Rooms] R on A.room_id = R.room_id join [dbo].[Hostels] H on R.hostel_id = H.hostel_id\n" +
                    "where H.hostel_id = ? and SUBSTRING( CONVERT(varchar(30), A.created_date, 1), 2, 1) = ? and SUBSTRING( CONVERT(varchar(30), A.created_date, 1), 7, 2) = ?\n" +
                    "Order By SUBSTRING( CONVERT(varchar(30), A.created_date, 1), 2, 1) DESC";
            ptm = cn.prepareStatement(sql);
            ptm.setInt(1, hostelId);
            ptm.setInt(2, month);
            ptm.setInt(3, year);

            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }

    public ArrayList<Bill> GetBillByHostelId(int hostelID) throws Exception {
        Bill bill = null;
        ArrayList<Bill> listBill = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            cn = DBUtils.makeConnection();
            String sql = "select A.bill_id, A.bill_title, A.created_date, A.expired_payment_date , A.payment_date,A.payment_id, A.room_id, A.status, A.total_money\n" +
                    "from [dbo].[Bill] A join [dbo].[Rooms] R on A.room_id = R.room_id join [dbo].[Hostels] H on R.hostel_id = H.hostel_id\n" +
                    "where H.hostel_id = ?";
            ptm = cn.prepareStatement(sql);
            ptm.setInt(1, hostelID);

            rs = ptm.executeQuery();
            while (rs != null && rs.next()) {
                int billID = rs.getInt("bill_id");
                int totalMoney = rs.getInt("total_money");
                String createdDate = dateFormat.format(rs.getDate("created_date"));
                String billTitle = rs.getString("bill_title");
                String expiredPaymentDate = dateFormat.format(rs.getDate("expired_payment_date"));
                Date paymentDateTemp = rs.getDate("payment_date");
                String paymentDate;
                if (paymentDateTemp == null || paymentDateTemp.equals("")) {
                    paymentDate = "";
                } else {
                    paymentDate = dateFormat.format(paymentDateTemp);
                }
                int status = rs.getInt("status");
                int paymentID = rs.getInt("payment_id");
                int roomID = rs.getInt("room_id");
                bill = Bill.builder()
                        .billID(billID)
                        .createdDate(createdDate)
                        .totalMoney(totalMoney)
                        .billTitle(billTitle)
                        .expiredPaymentDate(expiredPaymentDate)
                        .paymentDate(paymentDate)
                        .status(status)
                        .payment(Payment.builder().paymentID(paymentID).build())
                        .roomID(roomID)
                        .build();
                listBill.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return listBill;
    }
}
