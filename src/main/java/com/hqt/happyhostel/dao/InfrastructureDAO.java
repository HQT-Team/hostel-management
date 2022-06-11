package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Consume;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDAO {

    public static boolean updateInfrastructureStatus(int idInfrastructureRoom, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        Boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE InfrastructuresRoom\n" +
                        "SET status = ?\n" +
                        "WHERE id_infrastructure = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, idInfrastructureRoom);

                int rows = pst.executeUpdate();
                if (rows == 0) {
                    isSuccess = false;
                } else {
                    isSuccess = true;
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
        return isSuccess;
    }

}
