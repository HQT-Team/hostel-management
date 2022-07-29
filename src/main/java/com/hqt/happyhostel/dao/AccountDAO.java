package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;
import com.hqt.happyhostel.utils.DBUtils;
import com.hqt.happyhostel.utils.RandomStringGenerator;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccountDAO {

    private Account getAccount(ResultSet rs) {
        Account acc = null;
        AccountInfo accInf = null;
        List<RoommateInfo> roommateInfoList = new ArrayList<>();
        try {
            int accId = rs.getInt("account_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String createdate = rs.getString("create_date");
            int status = rs.getInt("status");
            int role = rs.getInt("role");
            int roomId = rs.getInt("room_id");
            if (role == 2) { //Renter
                roommateInfoList = new RoommateInfoDAO().getListRoommatesOfAnAccount(accId);
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

    public List<Account> GetAccountsByRole(int role) {
        Account acc;
        ArrayList<Account> list = new ArrayList<>();
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

    // Update token
    public boolean updateTokenByUserName(String token, String username) {
        boolean result = false;
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
                result = pst.executeUpdate() > 0;
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
        }
        return result;
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
                        "WHERE A.[account_id] = ? AND A.[otp] = ? AND GETDATE() < A.[expired_time_otp]";
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
    private static final String UPDATE_ACCOUNT_OTP = "Update [dbo].[Accounts] Set [otp]  = ?, [expired_time_otp] = ? Where [account_id] = ? ";
    private static final String GET_ACCOUNT_OTP_EXPIRED = "SELECT [expired_time_otp] FROM [dbo].[Accounts] WHERE [account_id] = ? ";

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

    public String getAccountOTPExpired(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        boolean isSuccess = false;
        ResultSet rs = null;
        String expiredTime = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = GET_ACCOUNT_OTP_EXPIRED;
                pst = cn.prepareStatement(sql);
                pst.setInt(1, accId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    expiredTime = rs.getString("expired_time_otp");
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
        return expiredTime;
    }

    private static final String GET_ACCOUNT_BY_EMAIL =
            "SELECT B.account_id, B.username, B.password, B.token, B.create_date, B.expired_date, B.status, B.role,\n" +
            "B.room_id, B.otp, B.expired_time_otp, B.request_recover_password_code, B.expired_time_recover_password\n" +
            "FROM AccountInformations A JOIN Accounts B ON A.account_id = B.account_id \n" +
            "WHERE A.email = ? AND (B.status = -1 OR B.status = 1 OR B.status = 0)";

    public Account getAccountByEmail(String email) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_ACCOUNT_BY_EMAIL);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    acc = Account.builder()
                            .accId(rs.getInt("account_id"))
                            .username(rs.getString("username"))
                            .password("**********")
                            .token(rs.getString("token"))
                            .createDate(rs.getString("create_date"))
                            .expiredDate(rs.getString("expired_date"))
                            .status(rs.getInt("status"))
                            .role(rs.getInt("role"))
                            .roomId(rs.getInt("room_id"))
                            .otp(rs.getString("otp"))
                            .expiredTimeOTP(rs.getString("expired_time_otp"))
                            .requestRecoverPasswordCode(rs.getString("request_recover_password_code"))
                            .expiredTimeRecoverPassword(rs.getString("expired_time_recover_password"))
                            .build();
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
        return acc;
    }

    private static final String ADD_KEY_AND_EXPIRATION_TIME_FOR_PASSWORD_RECOVERY_REQUEST =
            "UPDATE Accounts SET request_recover_password_code = ?, expired_time_recover_password = ?\n" +
            "WHERE account_id = ?";

    public boolean addKeyAndExpirationTimeForPasswordRecoveryRequest(String requestRecoverPasswordCode, String expiredTime, int accountId) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                pst = cn.prepareStatement(ADD_KEY_AND_EXPIRATION_TIME_FOR_PASSWORD_RECOVERY_REQUEST);
                pst.setString(1, requestRecoverPasswordCode);
                pst.setString(2, expiredTime);
                pst.setInt(3, accountId);
                result = pst.executeUpdate() > 0;

                if (!result) {
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
        return result;
    }

    private static final String CHECK_ACCOUNT_REQUEST_RECOVER_PASSWORD =
            "SELECT account_id, username, password, token, create_date, expired_date, status, role,\n" +
            "room_id, otp, expired_time_otp, request_recover_password_code, expired_time_recover_password FROM Accounts \n" +
            "WHERE account_id = ? AND request_recover_password_code = ? AND GETDATE() <= expired_time_recover_password";

    public boolean checkAccountRequestRecoverPassword(int accountId, String recoverCode) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                pst = cn.prepareStatement(CHECK_ACCOUNT_REQUEST_RECOVER_PASSWORD);
                pst.setInt(1, accountId);
                pst.setString(2, recoverCode);
                rs = pst.executeQuery();

                if (rs != null && rs.next()) {
                    result = true;
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
        return result;
    }

}