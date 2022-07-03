package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformationDAO {

    private static final String IS_EXIST_EMAIL = "SELECT email FROM AccountInformations WHERE email = ? ";
    private static final String GET_HOSTEL_OWNER_INFO_BY_RENTER_ID =
            "SELECT DISTINCT AccountInformations.fullname, AccountInformations.email, AccountInformations.birthday," +
            " AccountInformations.sex, AccountInformations.phone, AccountInformations.address, AccountInformations.identity_card_number\n" +
            "FROM AccountInformations INNER JOIN Accounts ON AccountInformations.account_id=Accounts.account_id\n" +
            "INNER JOIN Hostels ON Accounts.account_id=Hostels.owner_account_id\n" +
            "INNER JOIN Contracts ON Accounts.account_id=Contracts.hostel_owner_id\n" +
            "WHERE Contracts.renter_id= ?";
    private static final String GET_RENTER_INFO_BY_ID =
            "SELECT *\n" +
            "FROM [dbo].[AccountInformations]\n" +
            "WHERE [account_id] = ?";
    private static final String UPDATE_PROFILE =
            "UPDATE AccountInformations SET fullname = ?, email = ?, birthday = ?, phone = ?, address = ?, identity_card_number = ?, sex = ? WHERE account_id = ?";
    private static final String UPDATE_ROOMMATE =
            "UPDATE [dbo].[RoomateInformations] SET fullname = ?, email = ?, birthday = ?, sex = ?, phone = ?, address = ?, identity_card_number = ? WHERE roomate_info_id = ?";
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

    public Information getHostelOwnerInfoByRenterId(int renterId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Information accountInfor = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_OWNER_INFO_BY_RENTER_ID);
                pst.setInt(1, renterId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("identity_card_number");

                    accountInfor = Information.builder()
                            .fullname(fullname)
                            .phone(phone)
                            .email(email)
                            .birthday(birthday)
                            .sex(sex)
                            .address(address)
                            .cccd(cccd)
                            .build();
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
        return accountInfor;
    }

    public Information getAccountInformationById(int renterId) {
        Connection cn = null;
        PreparedStatement pst = null;
        Information inf = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_RENTER_INFO_BY_ID);
                pst.setInt(1, renterId);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String birthday = rs.getString("birthday");
                    int sex = rs.getInt("sex");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String cccd = rs.getString("identity_card_number");
                    inf = new Information(fullname, email, birthday, sex, phone, address, cccd);
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

    public boolean updateProfileByAccId(Information accountInfos,int accId) throws SQLException {
        boolean checkUpdate = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                ptm = cn.prepareStatement(UPDATE_PROFILE);
                ptm.setString(1, accountInfos.getFullname());
                ptm.setString(2, accountInfos.getEmail());
                ptm.setString(3, accountInfos.getBirthday());
                ptm.setString(4, accountInfos.getPhone());
                ptm.setString(5, accountInfos.getAddress());
                ptm.setString(6, accountInfos.getCccd());
                ptm.setInt(7, accountInfos.getSex());
                ptm.setInt(8, accId);

                checkUpdate = ptm.executeUpdate() > 0;

                if (!checkUpdate) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return checkUpdate;
    }
    public boolean updateRoommateInfoByID(RoommateInfo roommateInfo, int roommateID) throws SQLException {
        boolean checkUpdate = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                ptm = cn.prepareStatement(UPDATE_ROOMMATE);
                ptm.setString(1, roommateInfo.getInformation().getFullname());
                ptm.setString(2, roommateInfo.getInformation().getEmail());
                ptm.setString(3, roommateInfo.getInformation().getBirthday());
                ptm.setInt(4, roommateInfo.getInformation().getSex());
                ptm.setString(5, roommateInfo.getInformation().getPhone());
                ptm.setString(6, roommateInfo.getInformation().getAddress());
                ptm.setString(7, roommateInfo.getInformation().getCccd());
                ptm.setInt(8, roommateID);

                checkUpdate = ptm.executeUpdate() > 0;

                if (!checkUpdate) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return checkUpdate;
    }
}
