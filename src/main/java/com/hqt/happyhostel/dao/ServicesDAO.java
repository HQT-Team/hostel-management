package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.ServiceInfo;
import com.hqt.happyhostel.dto.Services;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicesDAO {

    private static final String GET_HOSTEL_SERVICE_BY_RENTER_ID =
            "SELECT Services.service_name,HostelService.service_price\n" +
                    "FROM Accounts INNER JOIN Contracts ON Accounts.account_id=Contracts.renter_id\n" +
                    "INNER JOIN Rooms ON Contracts.room_id=Rooms.room_id \n" +
                    "INNER JOIN Hostels ON Rooms.hostel_id=Hostels.hostel_id\n" +
                    "INNER JOIN HostelService ON Hostels.hostel_id=HostelService.hostel_id\n" +
                    "INNER JOIN Services ON HostelService.service_id =Services.service_id\n" +
                    "WHERE Accounts.account_id = ?";

    private static final String GET_LIST_SERVICES_NOT_IN_HOSTEL =
            "SELECT service_id, service_name, unit FROM Services WHERE service_id NOT IN \n" +
                    "(SELECT DISTINCT(service_id) FROM HostelService WHERE hostel_id = ?)";

    private static final String GET_LIST_SERVICES_IN_HOSTEL =
            "SELECT service_id, service_name, unit FROM Services WHERE service_id IN \n" +
                    "(SELECT DISTINCT(service_id) FROM HostelService WHERE hostel_id = ?)";

    private static final String GET_LIST_ALL_SERVICES = "SELECT service_id, service_name, unit FROM Services";

    public List<Services> GetListAllServices() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Services> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_LIST_SERVICES_IN_HOSTEL);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    list.add(Services.builder()
                            .serviceID(rs.getInt("service_id"))
                            .serviceName(rs.getString("service_name"))
                            .unit(rs.getString("unit")).build());
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
        return list;
    }

    public List<Services> getListServicesInHostel(int hostelId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Services> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_LIST_SERVICES_IN_HOSTEL);
                pst.setInt(1, hostelId);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    list.add(Services.builder()
                            .serviceID(rs.getInt("service_id"))
                            .serviceName(rs.getString("service_name"))
                            .unit(rs.getString("unit")).build());
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
        return list;
    }

    public List<Services> getListServicesNotInHostel(int hostelId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Services> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_LIST_SERVICES_NOT_IN_HOSTEL);
                pst.setInt(1, hostelId);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    list.add(Services.builder()
                            .serviceID(rs.getInt("service_id"))
                            .serviceName(rs.getString("service_name"))
                            .unit(rs.getString("unit")).build());
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
        return list;
    }

    public List<ServiceInfo> getHostelServicesByRenterId(int renterId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ServiceInfo> services = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_SERVICE_BY_RENTER_ID);
                pst.setInt(1, renterId);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    String serviceName = rs.getString("service_name");
                    double servicePrice = rs.getDouble("service_price");
                    services.add(ServiceInfo
                            .builder()
                            .serviceName(serviceName)
                            .servicePrice(servicePrice)
                            .build());
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
        return services;
    }
}
