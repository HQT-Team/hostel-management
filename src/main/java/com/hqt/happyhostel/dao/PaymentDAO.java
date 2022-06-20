package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Payment;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class PaymentDAO {

    public ArrayList<Payment> getPaymentList() {
        Connection cn = null;
        Statement pst = null;
        ResultSet rs = null;
        ArrayList<Payment> paymentList = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "SELECT payment_id, payment_name\n" +
                        "FROM Payment";

                pst = cn.createStatement();

                rs = pst.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int paymentId = rs.getInt("payment_id");
                        String paymentName = rs.getString("payment_name");
                        paymentList.add(new Payment(paymentId, paymentName));
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
        return paymentList;
    }

    public boolean updateBillStatus(int billID, int paymentID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Boolean updated = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "UPDATE Bill\n" +
                        "SET payment_date = GETDATE(), status = 1, payment_id = ?\n" +
                        "WHERE bill_id = ?\n";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, paymentID);
                pst.setInt(2, billID);

                int rows = pst.executeUpdate();
                if (rows == 0) {
                    updated = false;
                } else {
                    updated = true;
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
        return updated;
    }


}
