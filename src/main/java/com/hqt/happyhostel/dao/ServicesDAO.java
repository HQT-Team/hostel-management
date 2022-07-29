package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.ServiceInfo;
import com.hqt.happyhostel.dto.Services;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicesDAO {

    private static final String GET_LIST_SERVICES_NOT_IN_HOSTEL =
            "SELECT service_id, service_name, unit FROM Services WHERE service_id NOT IN \n" +
                    "(SELECT DISTINCT(service_id) FROM HostelService WHERE hostel_id = ?)";

    private static final String GET_ALL_SERVICES = "SELECT service_id, service_name, unit FROM Services";

    public Map<String, Services> getAll() {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Map<String, Services> list = new HashMap<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_ALL_SERVICES);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    list.put(rs.getString("service_name"), Services.builder()
                            .serviceID(rs.getInt("service_id"))
                            .serviceName(rs.getString("service_name"))
                            .unit(rs.getString("unit")).build());
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
        return list;
    }

    public List<Services> getListServicesNotInHostel(int hostelId) {
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
        return list;
    }

}
