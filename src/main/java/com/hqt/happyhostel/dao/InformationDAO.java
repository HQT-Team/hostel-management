package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InformationDAO {

    public static final String IS_EXIST_EMAIL = "SELECT email FROM AccountInformations WHERE email = ? ";

    public boolean isExistEmail(String email) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(IS_EXIST_EMAIL);
                psm.setString(1, email);
                rs = psm.executeQuery();
                if (rs != null && rs.next()) {
                    check = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (psm != null) {
                    psm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }
}
