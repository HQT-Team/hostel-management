package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RenterInfo;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {

    private static AccountInfo getOwnerAccountInformationById(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        AccountInfo inf = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                        "FROM [dbo].[HostelOwnerInformations]\n" +
                        "WHERE [account_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, accId);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("CCCD");
                    inf = new AccountInfo(new Information(fullname, email, birthday, sex, phone, address, cccd));
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
        return inf;
    }

    private static ArrayList<RenterInfo> getRenterAccountInformationById(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        RenterInfo renterInfo = null;
        ArrayList<RenterInfo> renterInfoList = new ArrayList<RenterInfo>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                        "FROM [dbo].[RoomateInformations]\n" +
                        "WHERE [account_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, accId);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("CCCD");
                    String parentName = rs.getString("parent_name");
                    String parentPhone = rs.getString("parent_phone");

                    renterInfo = new RenterInfo(new Information(fullname, email, birthday, sex, phone, address, cccd), parentName, parentPhone);
                    renterInfoList.add(renterInfo);
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
        return renterInfoList;
    }

    public static Account getAccountByUsernameAndPassword(String username, String password) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        AccountInfo inf = null;
        RenterInfo renterInfo = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                        "FROM [dbo].[Accounts]\n" +
                        "WHERE [username] = ? AND [password] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int accId = rs.getInt("account_id");
                    String Username = rs.getString("username");
                    String createdate = rs.getString("create_date");
                    String expireddate = rs.getString("expired_date");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");

                    if (role == 1) {
                        inf = getOwnerAccountInformationById(accId);
                        acc = new Account(Username, createdate, expireddate, status, role, inf, null);
                    } else {
                        ArrayList<RenterInfo> renterInfoList = getRenterAccountInformationById(accId);
                        acc = new Account(Username, createdate, expireddate, status, role, null, renterInfoList);
                    }
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


    public static Account getAccountByToken(String token) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        AccountInfo inf = null;
        RenterInfo renterInfo = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                        "FROM [dbo].[Accounts]\n" +
                        "WHERE [token] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int accId = rs.getInt("account_id");
                    String Username = rs.getString("username");
                    String createdate = rs.getString("create_date");
                    String expireddate = rs.getString("expired_date");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");

                    if (role == 1) {
                        inf = getOwnerAccountInformationById(accId);
                        acc = new Account(Username, createdate, expireddate, status, role, inf, null);
                    } else {
                        ArrayList<RenterInfo> renterInfoList = getRenterAccountInformationById(accId);
                        acc = new Account(Username, createdate, expireddate, status, role, null, renterInfoList);
                    }
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


    public static boolean addTokenByUserName(String token, String username) {
        boolean result = true;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Select *\n" +
                        "from [dbo].[Accounts]\n" +
                        "Where username = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    String sqlUpdateStatus = "Update [dbo].[Accounts]\n" +
                            "Set token = ?\n" +
                            "Where username = ?";
                    pst = cn.prepareStatement(sqlUpdateStatus);
                    pst.setString(1, token);
                    pst.setString(2, username);
                    pst.executeUpdate();
                } else result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
