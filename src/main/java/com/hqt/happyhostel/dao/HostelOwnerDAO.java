package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.HostelOwnerAccount;
import com.hqt.happyhostel.dto.HostelOwnerInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;

public class HostelOwnerDAO {
    public static HostelOwnerAccount getAccountByUsernameAndPassword(String username, String password){
        Connection cn = null;
        PreparedStatement pst = null;
        HostelOwnerAccount acc = null;
        HostelOwnerInfo inf = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql =    "SELECT *\n" +
                                "FROM [dbo].[HostelOwnerAccounts] AS A JOIN [dbo].[HostelOwnerInformations] AS I ON A.hostel_owner_account_id = I.hostel_owner_account_id\n" +
                                "WHERE A.username = ? AND A.password = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if(rs!= null && rs.next()){
                    String Username = rs.getString("username");
                    String createdate = rs.getString("create_date");
                    int status = rs.getInt("status");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("CCCD");
                    inf = new HostelOwnerInfo(new Information(fullname, email, birthday, sex, phone, address, cccd));
                    acc = new HostelOwnerAccount(username, createdate, status, inf);
                }
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
        return acc;
    }
}
