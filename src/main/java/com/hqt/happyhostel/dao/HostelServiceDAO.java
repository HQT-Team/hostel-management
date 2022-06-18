package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.HostelService;
import com.hqt.happyhostel.dto.Services;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HostelServiceDAO {

    private static final String GET_CURRENT_LIST_SERVICES_OF_A_HOSTEL =
            "DECLARE @service_id INT\n" +
                    "DECLARE @hostel_id INT\n" +
                    "DECLARE @NewestServicesTable TABLE (service_id int ,hostel_id int , service_price decimal(18, 3) , valid_date datetime)\n" +
                    "DECLARE cursorServices CURSOR FOR\n" +
                    "SELECT service_id, hostel_id  FROM (SELECT DISTINCT service_id, hostel_id FROM HostelService WHERE hostel_id = ?) AS TB\n" +
                    "Open cursorServices\n" +
                    "FETCH NEXT FROM cursorServices \n" +
                    "      INTO @service_id, @hostel_id\n" +
                    "WHILE @@FETCH_STATUS = 0\n" +
                    "BEGIN\n" +
                    "\tDECLARE @validDate datetime\n" +
                    "\tSet @validDate = (SELECT MAX(valid_date) FROM HostelService WHERE hostel_id = @hostel_id AND service_id = @service_id)\n" +
                    "\tDECLARE @price decimal(18, 0)\n" +
                    "\tSET @price = (SELECT service_price FROM HostelService WHERE valid_date = @validDate AND hostel_id = @hostel_id AND service_id = @service_id)\n" +
                    "\tINSERT @NewestServicesTable SELECT @service_id, @hostel_id, @price, @validDate\n" +
                    "    FETCH NEXT FROM cursorServices\n" +
                    "          INTO @service_id, @hostel_id\n" +
                    "END\n" +
                    "CLOSE cursorServices\n" +
                    "DEALLOCATE cursorServices\n" +
                    "SELECT service_id, hostel_id, service_price, valid_date FROM @NewestServicesTable";

    private static final String INSERT_LIST_SERVICES_INTO_HOSTEL =
            "INSERT INTO HostelService (hostel_id, service_id, service_price, valid_date)\n" +
                    "VALUES (?, ?, ?, GETDATE())";

    public boolean insertListServicesIntoHostel(List<HostelService> hostelServiceList, int hostelId) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;

        boolean check = false;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                conn.setAutoCommit(false);

                boolean checkInsert;
                for (HostelService item : hostelServiceList) {
                    psm = conn.prepareStatement(INSERT_LIST_SERVICES_INTO_HOSTEL);
                    psm.setInt(1, hostelId);
                    psm.setInt(2, item.getServiceID());
                    psm.setDouble(3, item.getServicePrice());
                    checkInsert = psm.executeUpdate() > 0;

                    if (checkInsert) {
                        check =  true;
                    } else {
                        conn.rollback();
                        conn.setAutoCommit(true);
                        return false;
                    }
                }
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (psm != null) psm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public List<HostelService> getCurrentListServicesOfAHostel (int hostelId) throws SQLException {
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        List<HostelService> list = new ArrayList<>();
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                psm = conn.prepareStatement(GET_CURRENT_LIST_SERVICES_OF_A_HOSTEL);
                psm.setInt(1, hostelId);
                rs = psm.executeQuery();
                while (rs != null && rs.next()) {
                    list.add(HostelService.builder()
                            .hostelID(rs.getInt("hostel_id"))
                            .serviceID(rs.getInt("service_id"))
                            .servicePrice(rs.getDouble("service_price"))
                            .validDate(rs.getString("valid_date")).build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}
