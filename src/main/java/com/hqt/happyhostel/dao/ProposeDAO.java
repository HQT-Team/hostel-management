package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Propose;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProposeDAO {

    private static final String GET_ALL_PROPOSES_BY_SENDER_ID =
            "SELECT id, content, send_date, reply, reply_date, status, send_account_id,\n" +
                    "reply_account_id FROM Propose WHERE send_account_id = ? ORDER BY send_date DESC";
    private static final String INSERT_NEW_PROPOSE =
            "INSERT INTO Propose(content, send_date, status, send_account_id) VALUES(?, GETDATE(), 0, ?)";

    public boolean insertNewPropose(String content, int senderId) {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                pst = conn.prepareStatement(INSERT_NEW_PROPOSE);
                pst.setString(1, content);
                pst.setInt(2, senderId);
                check = pst.executeUpdate() > 0;
                if (!check) {
                    conn.rollback();
                }
                conn.setAutoCommit(true);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return check;
    }

    public List<Propose> getAllProposeBySenderId(int senderId) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Propose> proposeList = new ArrayList<>();
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                pst = conn.prepareStatement(GET_ALL_PROPOSES_BY_SENDER_ID);
                pst.setInt(1, senderId);
                rs = pst.executeQuery();

                AccountDAO accountDAO = new AccountDAO();
                while (rs.next()) {
                    Account sendAccount = accountDAO.getAccountById(rs.getInt("send_account_id"));
                    Account replyAccount = accountDAO.getAccountById(rs.getInt("reply_account_id"));
                    proposeList.add(Propose.builder()
                            .id(rs.getInt("id"))
                            .content(rs.getString("content"))
                            .sendDate(rs.getString("send_date"))
                            .reply(rs.getString("reply"))
                            .replyDate(rs.getString("reply_date"))
                            .status(rs.getInt("status"))
                            .sendAccount(sendAccount)
                            .replyAccount(replyAccount).build());
                }
            }
        } catch(Exception e) {
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return proposeList;
    }
}
