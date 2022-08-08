package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoommateInfoDAO {

    private static final String ADD_ROOMMATE_INFORMATION_OF_AN_ACCOUNT =
            "INSERT INTO [dbo].[RoomateInformations] (fullname, email, birthday, \n" +
                    "sex, phone, address, identity_card_number, parent_name, parent_phone, account_renter_id) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LIST_ROOMMATES_OF_AN_ACCOUNT =
            "SELECT roomate_info_id, fullname, email, birthday, sex, phone, address, \n" +
                    "identity_card_number, parent_name, parent_phone FROM [dbo].[RoomateInformations] \n" +
                    "WHERE account_renter_id = ?";
    private static final String UPDATE_ROOMMATE_INFO =
            "UPDATE [dbo].[RoomateInformations]\n" +
                    "SET fullname = ?, email = ?, birthday = ?, sex = ?, phone = ?,\n" +
                    "address = ?, identity_card_number = ?, parent_name = ?, parent_phone = ?\n" +
                    "WHERE roomate_info_id = ?";

    private static final String DELETE_ROOMMATE_BY_ID = "DELETE FROM RoomateInformations WHERE roomate_info_id = ?";

    private static final String DELETE_ROOMMATE_INFO = "DELETE FROM RoomateInformations WHERE account_renter_id = ? AND roomate_info_id = ?";

    private static final String GET_ROOMMATE_BY_ID = "SELECT roomate_info_id, fullname, email, birthday,\n" +
            "sex, phone, [address], identity_card_number,\n" +
            "parent_name, parent_phone, account_renter_id\n" +
            "FROM RoomateInformations\n" +
            "WHERE roomate_info_id = ? ";
    public boolean DeleteRoommateInfo(int accountId, int roommateId) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        boolean check = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(DELETE_ROOMMATE_INFO);
                psm.setInt(1, accountId);
                psm.setInt(2, roommateId);

                check = psm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean UpdateRoommateInfo(RoommateInfo roommateInfo) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        boolean check = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(UPDATE_ROOMMATE_INFO);
                psm.setString(1, roommateInfo.getInformation().getFullname());
                psm.setString(2, roommateInfo.getInformation().getEmail());
                psm.setString(3, roommateInfo.getInformation().getBirthday());
                psm.setInt(4, roommateInfo.getInformation().getSex());
                psm.setString(5, roommateInfo.getInformation().getPhone());
                psm.setString(6, roommateInfo.getInformation().getAddress());
                psm.setString(7, roommateInfo.getInformation().getCccd());
                psm.setString(8, roommateInfo.getParentName());
                psm.setString(9, roommateInfo.getParentPhone());
                psm.setInt(10, roommateInfo.getRoommateID());

                check = psm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public List<RoommateInfo> getListRoommatesOfAnAccount(int accountId) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        List<RoommateInfo> list = new ArrayList<RoommateInfo>();
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_LIST_ROOMMATES_OF_AN_ACCOUNT);
                psm.setInt(1, accountId);
                rs = psm.executeQuery();

                while (rs.next()) {
                    Information information = Information.builder()
                            .fullname(rs.getString("fullname"))
                            .email(rs.getString("email"))
                            .birthday(rs.getString("birthday"))
                            .sex(rs.getInt("sex"))
                            .phone(rs.getString("phone"))
                            .address(rs.getString("address"))
                            .cccd(rs.getString("identity_card_number"))
                            .build();
                    RoommateInfo roommateInfo = RoommateInfo.builder()
                            .roommateID(rs.getInt("roomate_info_id"))
                            .information(information)
                            .parentName(rs.getString("parent_name"))
                            .parentPhone(rs.getString("parent_phone")).build();
                    list.add(roommateInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return list;
    }

    public boolean AddRoommateInformationOfAnAccount(RoommateInfo roommateInfo, int accountId) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        boolean check = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(ADD_ROOMMATE_INFORMATION_OF_AN_ACCOUNT);
                psm.setString(1, roommateInfo.getInformation().getFullname());
                psm.setString(2, roommateInfo.getInformation().getEmail());
                psm.setString(3, roommateInfo.getInformation().getBirthday());
                psm.setInt(4, roommateInfo.getInformation().getSex());
                psm.setString(5, roommateInfo.getInformation().getPhone());
                psm.setString(6, roommateInfo.getInformation().getAddress());
                psm.setString(7, roommateInfo.getInformation().getCccd());
                psm.setString(8, roommateInfo.getParentName());
                psm.setString(9, roommateInfo.getParentPhone());
                psm.setInt(10, accountId);

                check = psm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean DeleteRoommateInfo(int roomID) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        boolean check = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(DELETE_ROOMMATE_BY_ID);
                psm.setInt(1, roomID);

                check = psm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public RoommateInfo getRoommateByID(int roommateID) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;

        RoommateInfo roommateInfo = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_ROOMMATE_BY_ID);
                psm.setInt(1, roommateID);
                rs = psm.executeQuery();

                if (rs.next()) {
                    Information information = Information.builder()
                            .fullname(rs.getString("fullname"))
                            .email(rs.getString("email"))
                            .birthday(rs.getString("birthday"))
                            .sex(rs.getInt("sex"))
                            .phone(rs.getString("phone"))
                            .address(rs.getString("address"))
                            .cccd(rs.getString("identity_card_number"))
                            .build();
                    roommateInfo = RoommateInfo.builder()
                            .roommateID(rs.getInt("roomate_info_id"))
                            .information(information)
                            .parentName(rs.getString("parent_name"))
                            .parentPhone(rs.getString("parent_phone")).build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return roommateInfo;
    }
}
