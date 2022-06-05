package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.AccountInfo;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {

    private final static Account getAccount(ResultSet rs) {
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
            if (role == 2) {//Renter
                roommateInfoList = getRoommateInformationById(accId);
                accInf = getAccountInformationById(accId);
                acc = new Account(accId, username, createdate, status, role, accInf, roommateInfoList);
            } else {
                accInf = getAccountInformationById(accId);
                acc = new Account(accId, username, createdate, status, role, accInf, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    private final static AccountInfo getAccountInformationById(int accId) {
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

    private final static ArrayList<RoommateInfo> getRoommateInformationById(int accId) {
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
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("identity_card_number");
                    String parentName = rs.getString("parent_name");
                    String parentPhone = rs.getString("parent_phone");

                    renterInfo = new RoommateInfo(new Information(fullname, email, birthday, sex, phone, address, cccd), parentName, parentPhone);
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

    public static Account getAccountByUsernameAndPassword(String username, String password) {
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
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
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

    public static ArrayList<Account> GetAll() {
        Account acc = null;
        ArrayList<Account> list = new ArrayList<Account>();
        Connection cn = null;
        Statement st = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM [dbo].[Accounts]";
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs != null && rs.next()) {
                    acc = getAccount(rs);
                    list.add(acc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
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

    public static ArrayList<Account> GetAllBy(String searchBy, String keyword) {
        Account acc = null;
        ArrayList<Account> list = new ArrayList<Account>();
        Connection cn = null;
        PreparedStatement pst = null;
        StringBuilder SearchBy = new StringBuilder("Where "+ searchBy+ " = ?");
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                StringBuilder sql = new StringBuilder("SELECT * FROM [dbo].[Accounts]\n");
                if(!searchBy.isEmpty() || !searchBy.isEmpty()){
                    sql = sql.append(SearchBy);
                }

                pst = cn.prepareStatement(sql.toString());
                pst.setString(1, keyword);
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


    /*-------------------------------------UPDATE-------------------------------------*/

    public static int updateTokenByUserName(String token, String username) {
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
            if (cn != null) {
                try {
                    cn.close();
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
        }
        return result;
    }


    public static int updateAccountStatus(String username, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        Account acc = null;
        int result = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Update [dbo].[Accounts]\n" +
                        "Set status = ?\n" +
                        "Where username = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, username);
                result = pst.executeUpdate();
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

    // Handle register
    private static final String IS_EXIST_USERNAME = "SELECT username FROM [dbo].[Accounts] Where username = ?";
    private static final String ADD_AN_ACCOUNT = "INSERT INTO Accounts (username, password, create_date, status, role) \n" +
                                                 "VALUES (?, ?, GETDATE(), ?, ?)";
    private static final String ADD_ACCOUNT_INFORMATION = "INSERT INTO AccountInformations (account_id, fullname, email, identity_card_number) \n" +
                                                          "VALUES (?, ?, ?, ?)";

    public static boolean isExistUsername(String username) {
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

    public static boolean addAnAccount(Account account) {
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
}
