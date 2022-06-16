package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Notification;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    private static final String GET_NOTIFICATION_BY_ID =
            "SELECT Notifications.title, Notifications.content, Notifications.create_date\n" +
                    "FROM Accounts INNER JOIN Contracts ON Accounts.account_id=Contracts.renter_id\n" +
                    "INNER JOIN Rooms ON Contracts.room_id=Rooms.room_id\n" +
                    "INNER JOIN Hostels ON Rooms.hostel_id=Hostels.hostel_id\n" +
                    "INNER JOIN Notifications ON Hostels.hostel_id=Notifications.hostel_id\n" +
                    "WHERE Accounts.account_id = ?";

    public List<Notification> getNotificationById(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        List<Notification> noti = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_NOTIFICATION_BY_ID);
                pst.setInt(1, accId);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String createDate = rs.getString("create_date");
                    noti.add(Notification
                            .builder()
                            .title(title)
                            .content(content)
                            .createDate(createDate)
                            .build());
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
        return noti;
    }
}
