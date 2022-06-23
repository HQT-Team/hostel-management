package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.Payment;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO {
    private static final String GET_BILL_BY_ACC_ID =
            "SELECT Bill.bill_id,Bill.total_money,Bill.created_date,Bill.expired_payment_date,Bill.status,Bill.payment_id,Bill.room_id\n" +
                    "FROM Accounts INNER JOIN Rooms ON Accounts.room_id=Rooms.room_id\n" +
                    "INNER JOIN Bill ON Rooms.room_id=Bill.room_id\n" +
                    "WHERE Accounts.account_id = ?";
    public Bill getBillByAccountID(int accID) throws SQLException {
        Bill bill = null;
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
                cn = DBUtils.makeConnection();
                ptm = cn.prepareStatement(GET_BILL_BY_ACC_ID);
                ptm.setInt(1, accID);
                rs = ptm.executeQuery();
                if (rs!=null){
                    int billID = rs.getInt("bill_id");
                    double totalMoney = rs.getDouble("total_money");
                    String createdDate = rs.getString("created_date");
                    String expiredPaymentDate = rs.getString("expired_payment_date");
                    int status = rs.getInt("status");
                    int paymentID = rs.getInt("payment_id");
                    int roomID = rs.getInt("room_id");
                    bill = Bill.builder()
                            .billID(billID)
                            .payment(Payment.builder().paymentID(paymentID).build())
                            .createdDate(createdDate)
                            .expiredPaymentDate(expiredPaymentDate)
                            .totalMoney(totalMoney)
                            .roomID(roomID)
                            .status(status)
                            .build();
                }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (rs!=null){
                rs.close();
            }
            if (ptm!=null){
                ptm.close();
            }
            if (cn!=null){
                cn.close();
            }
        }


        return bill;

    }
}
