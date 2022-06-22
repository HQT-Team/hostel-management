package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.Services;
import com.hqt.happyhostel.utils.DBUtils;
import com.hqt.happyhostel.utils.GetAddressUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostelDAO {

    private static final String GET_HOSTEL =
            "SELECT hostel_id, owner_account_id, name, address, ward, district, city FROM [dbo].[Hostels]";
    private static final String INSERT_HOSTEl =
            "INSERT INTO [dbo].[Hostels](owner_account_id, name, address, ward, district, city) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_SERVICE =
            "SELECT service_id, service_name, unit FROM [dbo].[Services]";
    private static final String INSERT_HOSTEL_SERVICE =
            "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, (SELECT service_id FROM Services WHERE service_name = N'Điện'), ?, ?)\n" +
                    "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, (SELECT service_id FROM Services WHERE service_name = N'Nước'), ?, ?)\n" +
                    "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, (SELECT service_id FROM Services WHERE service_name = N'Wifi'), ?, ?)\n" +
                    "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, (SELECT service_id FROM Services WHERE service_name = N'Phí quản lý'), ?, ?)\n" +
                    "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, (SELECT service_id FROM Services WHERE service_name = N'Phí giữ xe'), ?, ?)\n" +
                    "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, (SELECT service_id FROM Services WHERE service_name = N'Phí vệ sinh'), ?, ?)";;
    private static final String UPDATE_HOSTEL =
            "UPDATE Hostels SET name = ?, address = ?, ward = ?, district = ?, city = ? WHERE hostel_id = ?";
    private static final String GET_HOSTEL_BY_ID =
            "SELECT hostel_id, owner_account_id, name, address, ward, district, city FROM [dbo].[Hostels] WHERE hostel_id = ?";
    private static final String GET_HOSTEL_BY_ID_WITH_CONSTRAINT =
            "SELECT hostel_id, owner_account_id, name, address, ward, district, city FROM Hostels WHERE hostel_id = ? AND owner_account_id = ?";
    private static final String GET_HOSTEL_BY_OWNER_ID =
            "SELECT hostel_id, owner_account_id, name, address, ward, district, city FROM [dbo].[Hostels] WHERE owner_account_id = ?";

    public Hostel getHostelById(int hostelId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hostel hostel = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_BY_ID);
                pst.setInt(1, hostelId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int hostelOwnerAccountID = rs.getInt("owner_account_id");
                    String name =  rs.getString("name");
                    String address =  GetAddressUtils.getDefaultAddress(rs.getString("address"));
                    String ward = GetAddressUtils.getDefaultAddress(rs.getString("ward"));
                    String district = GetAddressUtils.getDefaultAddress(rs.getString("district"));
                    String city = GetAddressUtils.getDefaultAddress(rs.getString("city"));
                    hostel = Hostel.builder()
                            .hostelID(hostelId)
                            .hostelOwnerAccountID(hostelOwnerAccountID)
                            .hostelName(name)
                            .address(address)
                            .ward(ward)
                            .district(district)
                            .city(city)
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
        return hostel;
    }

    public Hostel getHostelByIdWithConstraint(int hostelId, int ownerAccountID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hostel hostel = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_BY_ID_WITH_CONSTRAINT);
                pst.setInt(1, hostelId);
                pst.setInt(2, ownerAccountID);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int hostelOwnerAccountID = rs.getInt("owner_account_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String ward = rs.getString("ward");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    hostel = Hostel.builder()
                            .hostelID(hostelId)
                            .hostelOwnerAccountID(hostelOwnerAccountID)
                            .hostelName(name)
                            .address(address)
                            .ward(ward)
                            .district(district)
                            .city(city).build();
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
        return hostel;
    }

    public List<Hostel> getHostelByOwnerId(int hostelOwnerAccountID) throws SQLException {
        List<Hostel> listHostels = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hostel hostel = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_BY_OWNER_ID);
                pst.setInt(1, hostelOwnerAccountID);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int hostelID = rs.getInt("hostel_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String ward = rs.getString("ward");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    hostel = Hostel.builder()
                            .hostelID(hostelID)
                            .hostelOwnerAccountID(hostelOwnerAccountID)
                            .hostelName(name)
                            .address(address)
                            .ward(ward)
                            .district(district)
                            .city(city).build();
                    listHostels.add(hostel);
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
        return listHostels;
    }

    public boolean addHostel(Hostel hostel, List<HostelService> hostelServices) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            //Insert table Hostel
            if (cn != null) {
                cn.setAutoCommit(false);

                ptm = cn.prepareStatement(INSERT_HOSTEl, Statement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, hostel.getHostelOwnerAccountID());
                ptm.setString(2, hostel.getHostelName());
                ptm.setString(3, hostel.getAddress());
                ptm.setString(4, hostel.getWard());
                ptm.setString(5, hostel.getDistrict());
                ptm.setString(6, hostel.getCity());
                check = ptm.executeUpdate() > 0;

                rs = ptm.getGeneratedKeys();

                if (rs.next()) {
                    hostel.setHostelID(rs.getInt(1));
                }

                int c = 0;
                ptm = cn.prepareStatement(INSERT_HOSTEL_SERVICE);
                for (HostelService ser: hostelServices
                ) {
                    c++;
                    ptm.setInt(c, hostel.getHostelID());
                    c++;
                    ptm.setDouble(c, ser.getServicePrice());
                    c++;
                    ptm.setString(c, ser.getValidDate());
                }
                check = ptm.executeUpdate() > 0;

                if (!check) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public boolean updateHostel(Hostel hostel, int hostelID) throws SQLException {
        boolean checkUpdate = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                cn.setAutoCommit(false);
                ptm = cn.prepareStatement(UPDATE_HOSTEL);
                ptm.setString(1, hostel.getHostelName());
                ptm.setString(2, hostel.getAddress());
                ptm.setString(3, hostel.getWard());
                ptm.setString(4, hostel.getDistrict());
                ptm.setString(5, hostel.getCity());
                ptm.setInt(6, hostelID);
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

    // Renter handler
    private static final String GET_HOSTEL_BY_RENTER_ID =
            "SELECT Hostels.hostel_id, Hostels.owner_account_id, Hostels.name, Hostels.address, Hostels.ward, Hostels.district, Hostels.city\n" +
                    "FROM Hostels INNER JOIN Rooms ON Hostels.hostel_id=Rooms.hostel_id \n" +
                    "INNER JOIN Contracts ON Rooms.room_id = Contracts.room_id\n" +
                    "INNER JOIN Accounts ON Contracts.renter_id = Accounts.account_id\n" +
                    "Where account_id = ?";
    public Hostel getHostelByRenterId(int renterId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Hostel hostel = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_BY_RENTER_ID);
                pst.setInt(1, renterId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int hostelId = rs.getInt("hostel_id");
                    int hostelOwnerAccountID = rs.getInt("owner_account_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String ward = rs.getString("ward");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    hostel = new Hostel(hostelId, hostelOwnerAccountID, name, address, ward, district, city);
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
        return hostel;
    }


}
