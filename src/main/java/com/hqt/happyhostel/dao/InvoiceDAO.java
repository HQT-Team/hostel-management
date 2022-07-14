package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    private static final String GET_INVOICE_LIST_BY_OWNER_ACCOUNT_ID =
            "SELECT bill_id, bill_title, Rooms.room_id AS 'room_id', total_money, created_date\n" +
                    "FROM Bill, Rooms, Hostels\n" +
                    "WHERE Bill.room_id = Rooms.room_id\n" +
                    "AND Hostels.owner_account_id = ?\n" +
                    "AND Bill.status = ?\n" +
                    "ORDER BY created_date DESC";

    public List<Bill> getInvoiceListByOwnerAccountID(int ownerAccountID, int status) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Bill> invoiceList = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_INVOICE_LIST_BY_OWNER_ACCOUNT_ID);
                pst.setInt(1, ownerAccountID);
                pst.setInt(2, status);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int billID = rs.getInt("bill_id");
                        String billTitle = rs.getString("bill_title");
                        int roomID = rs.getInt("room_id");
                        int totalMoney = rs.getInt("total_money");
                        String createdDate = rs.getString("created_date");
                        Bill bill = Bill.builder().billID(billID)
                                .billTitle(billTitle)
                                .roomID(roomID)
                                .totalMoney(totalMoney)
                                .createdDate(createdDate).build();
                        invoiceList.add(bill);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return invoiceList;
    }


}
