package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {

    private Account getAccount(ResultSet rs) {
        Account acc = null;
        AccountInfo accInf = null;
        RoommateInfo renterInfo = null;
        ArrayList<RoommateInfo> roommateInfoList = new ArrayList<>();
        try {
            int accId = rs.getInt("account_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String createdate = rs.getString("create_date");
            int status = rs.getInt("status");
            int role = rs.getInt("role");
            int roomId = rs.getInt("room_id");
            if (role == 2) {//Renter
                roommateInfoList = getRoommateInformationById(accId);
                accInf = getAccountInformationById(accId);
                acc = Account.builder()
                        .accId(accId)
                        .username(username)
                        .password(password)
                        .createDate(createdate)
                        .status(status)
                        .role(role)
                        .roomId(roomId)
                        .accountInfo(accInf)
                        .roommateInfo(roommateInfoList)
                        .build();
            } else {
                accInf = getAccountInformationById(accId);
                acc = Account.builder()
                        .accId(accId)
                        .username(username)
                        .password(password)
                        .createDate(createdate)
                        .status(status)
                        .role(role)
                        .roomId(-1)
                        .accountInfo(accInf)
                        .roommateInfo(null)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public AccountInfo getAccountInformationById(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        AccountInfo inf = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                             "FROM [dbo].[AccountInformations]\n" +
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
                    String cccd = rs.getString("identity_card_number");
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

    public ArrayList<RoommateInfo> getRoommateInformationById(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        RoommateInfo renterInfo = null;
        ArrayList<RoommateInfo> roommateInfoList = new ArrayList<RoommateInfo>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                             "FROM [dbo].[RoomateInformations]\n" +
                             "WHERE [account_renter_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, accId);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int roommateID = rs.getInt("roomate_info_id");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("identity_card_number");
                    String parentName = rs.getString("parent_name");
                    String parentPhone = rs.getString("parent_phone");

                    renterInfo = new RoommateInfo(roommateID, new Information(fullname, email, birthday, sex, phone, address, cccd), parentName, parentPhone);
                    roommateInfoList.add(renterInfo);
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
        return roommateInfoList;
    }

    public Account getAccountByUsernameAndPassword(String username, String password) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                             "FROM [dbo].[Accounts]\n" +
                             "WHERE [username] = ? AND [password] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    acc = getAccount(rs);
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
        return acc;
    }

    public Account getAccountById(int id) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT *\n" +
                             "FROM [dbo].[Accounts]\n" +
                             "WHERE [account_id] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    acc = getAccount(rs);
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
        return acc;
    }


    public Account getAccountByToken(String token) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        AccountInfo inf = null;
        RoommateInfo roommateInfo = null;
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
                    acc = getAccount(rs);
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

    public ArrayList<Account> GetAllByRole(int role) {
        Account acc = null;
        ArrayList<Account> list = new ArrayList<Account>();
        Connection cn = null;
        PreparedStatement pst = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * \n" +
                             "FROM [dbo].[Accounts] \n" +
                             "WHERE Role = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, role);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    acc = getAccount(rs);
                    list.add(acc);
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
        return list;
    }

    public String getUsernameRoomCurrently(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        String username = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT username\n" +
                             "FROM Accounts\n" +
                             "WHERE account_id = (SELECT TOP 1 renter_id\n" +
                             "FROM Rooms R, Contracts C\n" +
                             "WHERE R.room_id = ?\n" +
                             "AND R.room_id = C.room_id\n" +
                             "ORDER BY C.start_date ASC)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    username = rs.getString("username");
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
        return username;
    }

    // Update token
    public int updateTokenByUserName(String token, String username) {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlUpdateStatus = "Update [dbo].[Accounts]\n" +
                        "Set token = ?\n" +
                        "Where username = ?";
                pst = cn.prepareStatement(sqlUpdateStatus);
                pst.setString(1, token);
                pst.setString(2, username);
                result = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null && pst != null) {
                try {
                    pst.close();
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    public boolean updateAccountStatus(int id, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Update [dbo].[Accounts]\n" +
                             "Set status = ?\n" +
                             "Where account_id = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, id);
                int i = pst.executeUpdate();
                if(i > 0) result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null && pst != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // Handle register
    private static final String IS_EXIST_USERNAME = "SELECT username FROM [dbo].[Accounts] Where username = ?";
    private static final String ADD_AN_ACCOUNT = "INSERT INTO Accounts (username, password, create_date, status, role) \n" +
                                                 "VALUES (?, ?, GETDATE(), ?, ?)";
    private static final String ADD_A_RENTER_ACCOUNT = "INSERT INTO Accounts (username, password, create_date, status, role, room_id) \n" +
            "VALUES (?, ?, GETDATE(), ?, ?, ?)";
    private static final String ADD_ACCOUNT_INFORMATION = "INSERT INTO AccountInformations (account_id, fullname, email, identity_card_number) \n" +
                                                          "VALUES (?, ?, ?, ?)";

    public boolean isExistUsername(String username) {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(IS_EXIST_USERNAME);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }

    public boolean addAnAccount(Account account) {
        boolean check = false;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Stop auto commit for rollback if transaction insert data have any problem
                cn.setAutoCommit(false);

                // Add into Accounts table
                pst = cn.prepareStatement(ADD_AN_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
                                                            // Return key Identity of data just inserted
                pst.setString(1, account.getUsername());
                pst.setString(2, account.getPassword());
                pst.setInt(3, account.getStatus());
                pst.setInt(4, account.getRole());

                if (pst.executeUpdate() > 0) {

                    int accountId = -1;
                    rs = pst.getGeneratedKeys();

                    if (rs.next()) {
                        accountId = rs.getInt(1);
                    }

                    // Add into AccountInformations table
                    pst = cn.prepareStatement(ADD_ACCOUNT_INFORMATION);
                    pst.setInt(1, accountId);
                    pst.setString(2, account.getAccountInfo().getInformation().getFullname());
                    pst.setString(3, account.getAccountInfo().getInformation().getEmail());
                    pst.setString(4, account.getAccountInfo().getInformation().getCccd());

                    if (pst.executeUpdate() > 0) {
                        check = true;
                        cn.commit();
                    } else {
                        cn.rollback();
                    }
                    cn.setAutoCommit(true);
                } else {
                    cn.rollback();
                    cn.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return check;
    }

    public int createRenterAccount(Account account) {
        int accountId = -1;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Stop auto commit for rollback if transaction insert data have any problem
                cn.setAutoCommit(false);

                // Add into Accounts table
                pst = cn.prepareStatement(ADD_A_RENTER_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, account.getUsername());
                pst.setString(2, account.getPassword());
                pst.setInt(3, account.getStatus());
                pst.setInt(4, account.getRole());
                pst.setInt(5, account.getRoomId());

                if (pst.executeUpdate() > 0) {
                    rs = pst.getGeneratedKeys();

                    if (rs.next()) {
                        accountId = rs.getInt(1);
                    }

                    // Add into AccountInformations table
                    pst = cn.prepareStatement(ADD_ACCOUNT_INFORMATION);
                    pst.setInt(1, accountId);
                    pst.setString(2, account.getAccountInfo().getInformation().getFullname());
                    pst.setString(3, account.getAccountInfo().getInformation().getEmail());
                    pst.setString(4, account.getAccountInfo().getInformation().getCccd());

                    if (pst.executeUpdate() > 0) {
                        cn.commit();
                    } else {
                        cn.rollback();
                    }
                    cn.setAutoCommit(true);
                } else {
                    cn.rollback();
                    cn.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return accountId;
    }

    // Handle add roommate
    private static final String GET_ACCOUNT_ID_BY_USERNAME = "SELECT account_id FROM Accounts WHERE username = ?";

    public int getAccountIdByUserName(String userName) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        int accountId = -1;

        try {

            conn = DBUtils.makeConnection();

            if (conn != null) {
                psm = conn.prepareStatement(GET_ACCOUNT_ID_BY_USERNAME);
                psm.setString(1, userName);
                rs = psm.executeQuery();

                if (rs != null && rs.next()) {
                    accountId = rs.getInt("account_id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) { rs.close(); }
            if (psm != null) { psm.close(); }
            if (conn != null) { conn.close(); }
        }
        return accountId;
    }


    public int checkAccountByOTP(int accId, String otp) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        int accountId = -1;

        try {

            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "SELECT A.[account_id]\n" +
                        "FROM [dbo].[Accounts] AS A JOIN [dbo].[AccountInformations] AS AI ON A.[account_id] = AI.[account_id]\n" +
                        "WHERE A.[account_id] = ? AND A.[otp] = ? AND GETDATE() < A.[expiredTimeOTP]";
                psm = conn.prepareStatement(sql);
                psm.setInt(1, accId);
                psm.setString(2, otp);
                rs = psm.executeQuery();

                if (rs != null && rs.next()) {
                    accountId = rs.getInt("account_id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) { rs.close(); }
            if (psm != null) { psm.close(); }
            if (conn != null) { conn.close(); }
        }
        return accountId;
    }


    private static final String UPDATE_ACCOUNT_PASSWORD = "Update [dbo].[Accounts] Set [password] = ? Where [account_id] = ?";
    private static final String UPDATE_ACCOUNT_FULLNAME = "Update [dbo].[AccountInformations] Set [fullname] = ? Where [account_id] = ?";
    private static final String UPDATE_ACCOUNT_OTP = "Update [dbo].[Accounts] Set [otp]  = ?, [expiredTimeOTP] = ? Where [account_id] = ? ";
    public boolean updateAccountPass(int accId, String pass) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = UPDATE_ACCOUNT_PASSWORD;
                pst = cn.prepareStatement(sql);
                pst.setString(1, pass);
                pst.setInt(2, accId);
                if (pst.executeUpdate() < 1) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }


    public boolean updateAccountFullName(int accId, String fullName) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = UPDATE_ACCOUNT_FULLNAME;
                pst = cn.prepareStatement(sql);
                pst.setString(1, fullName);
                pst.setInt(2, accId);
                if (pst.executeUpdate() < 1) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    public boolean updateAccountOTP(int accId, String otp, String endTime) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = UPDATE_ACCOUNT_OTP;
                pst = cn.prepareStatement(sql);
                pst.setString(1, otp);
                pst.setString(2, endTime);
                pst.setInt(3, accId);
                if (pst.executeUpdate() < 1) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }



}