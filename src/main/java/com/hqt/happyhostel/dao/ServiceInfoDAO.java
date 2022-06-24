package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.ServiceInfo;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceInfoDAO {

    private static final String GET_SERVICES_OF_HOSTEL =
                    "DECLARE @service_id INT\n" +
                    "DECLARE @hostel_id INT = ?\n" +
                    "DECLARE @NewestServicesTable TABLE (hostel_service_id int, service_id int ,hostel_id int , service_price decimal(18, 0) , valid_date datetime)\n" +
                    "DECLARE cursorServices CURSOR FOR\n" +
                    "SELECT service_id  FROM (SELECT DISTINCT service_id, hostel_id FROM HostelService WHERE hostel_id = @hostel_id) AS TB\n" +
                    "Open cursorServices \n" +
                    "FETCH NEXT FROM cursorServices\n" +
                    "INTO @service_id\n" +
                    "WHILE @@FETCH_STATUS = 0 \n" +
                    "BEGIN\n" +
                    "DECLARE @validDate datetime \n" +
                    "Set @validDate = (SELECT MAX(valid_date) FROM HostelService WHERE hostel_id = @hostel_id AND service_id = @service_id) \n" +
                    "DECLARE @price decimal(18, 0)\n" +
                    "SET @price = (SELECT service_price FROM HostelService WHERE valid_date = @validDate AND hostel_id = @hostel_id AND service_id = @service_id)\n" +
                    "DECLARE @hostel_service_id int\n" +
                    "SET @hostel_service_id = (SELECT hostel_service_id FROM HostelService WHERE valid_date = @validDate AND hostel_id = @hostel_id AND service_id = @service_id)\n" +
                    "INSERT @NewestServicesTable SELECT @hostel_service_id, @service_id, @hostel_id, @price, @validDate\n" +
                    "FETCH NEXT FROM cursorServices\n" +
                    "INTO @service_id\n" +
                    "END\n" +
                    "CLOSE cursorServices \n" +
                    "DEALLOCATE cursorServices\n" +
                    "SELECT N.hostel_service_id, S.service_id, service_name, valid_date, service_price, unit\n" +
                    "FROM Services S RIGHT JOIN @NewestServicesTable N ON S.service_id = N.service_id\n";

    public List<ServiceInfo> getServicesOfHostel(int hostelID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ServiceInfo> servicesList = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_SERVICES_OF_HOSTEL);
                pst.setInt(1, hostelID);

                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int hostelServiceId = rs.getInt("hostel_service_id");
                        int serviceID = rs.getInt("service_id");
                        String serviceName = rs.getString("service_name");
                        int servicePrice = rs.getInt("service_price");
                        String unit = rs.getString("unit");
                        String validDate = rs.getString("valid_date");
                        servicesList.add(new ServiceInfo(hostelServiceId, hostelID, serviceID, serviceName, validDate, servicePrice, unit));
                    }
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
        return servicesList;
    }

    public List<ServiceInfo> getServiceOfBill(int billDetailID, int hostelID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ServiceInfo> servicesList = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "SELECT hostel_service_id, HostelService.service_id as 'service_id', Services.service_name as 'service_name', HostelService.valid_date as 'valid_date', HostelService.service_price as 'service_price', Services.unit as 'unit'\n" +
                        "FROM HostelService, Services\n" +
                        "WHERE hostel_id = ?\n" +
                        "AND HostelService.service_id = Services.service_id\n" +
                        "AND HostelService.hostel_service_id IN (SELECT hostel_service_id\n" +
                        "FROM BillService\n" +
                        "WHERE bill_detail_id = ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setInt(2, billDetailID);

                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int hostelServiceId = rs.getInt("hostel_service_id");
                        int serviceID = rs.getInt("service_id");
                        String serviceName = rs.getString("service_name");
                        int servicePrice = rs.getInt("service_price");
                        String unit = rs.getString("unit");
                        String validDate = rs.getString("valid_date");
                        servicesList.add(new ServiceInfo(hostelServiceId, hostelID, serviceID, serviceName, validDate, servicePrice, unit));
                    }
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
        return servicesList;
    }

}
