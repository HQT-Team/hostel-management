package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Notification;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    private static final String INSERT_NOTIFICATION = "INSERT INTO [dbo].[Notifications] VALUES(?, ?, ?, ?, GETDATE())";
    private static final String GET_NOTIFICATION_BY_RENTER_ID =
            "SELECT Notifications.title, Notifications.content, Notifications.create_date\n" +
            "FROM Accounts INNER JOIN Contracts ON Accounts.account_id=Contracts.renter_id\n" +
            "INNER JOIN Rooms ON Contracts.room_id=Rooms.room_id\n" +
            "INNER JOIN Hostels ON Rooms.hostel_id=Hostels.hostel_id\n" +
            "INNER JOIN Notifications ON Hostels.hostel_id=Notifications.hostel_id\n" +
            "WHERE Accounts.account_id = ?";
    private static final String GET_NOTIFICATION_BY_OWNER_ID =
            "SELECT [notification_id], [title], [content], [create_date]\n" +
            "FROM [dbo].[Notifications]\n" +
            "WHERE [hostel_owner_account_id] = ?";

    private static final String GET_NOTIFICATION_BY_ID =
            "SELECT [notification_id], [title], [content], [create_date]\n" +
            "FROM [dbo].[Notifications]\n" +
            "WHERE [notification_id] = ?";
    public List<Notification> getNotificationByRenterId(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        List<Notification> noti = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_NOTIFICATION_BY_RENTER_ID);
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

    public List<Notification> getNotificationByOwnerId(int accId) {
        Connection cn = null;
        PreparedStatement pst = null;
        List<Notification> noti = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_NOTIFICATION_BY_OWNER_ID);
                pst.setInt(1, accId);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int notiId = rs.getInt("notification_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String createDate = rs.getString("create_date");
                    noti.add(Notification
                            .builder()
                            .notification_id(notiId)
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

    public Notification getNotificationById(int notiId) {
        Connection cn = null;
        PreparedStatement pst = null;
        Notification noti = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_NOTIFICATION_BY_ID);
                pst.setInt(1, notiId);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    notiId = rs.getInt("notification_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String createDate = rs.getString("create_date");
                    noti = Notification
                            .builder()
                            .notification_id(notiId)
                            .title(title)
                            .content(content)
                            .createDate(createDate)
                            .build();
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

    public int creatNotification(int ownerId, int hostelId, String title, String content){
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int notiId = -1;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Stop auto commit for rollback if transaction insert data have any problem
                cn.setAutoCommit(false);

                // Add into Accounts table
                pst = cn.prepareStatement(INSERT_NOTIFICATION, Statement.RETURN_GENERATED_KEYS);
                // Return key Identity of data just inserted
                pst.setInt(1, ownerId);
                pst.setInt(2, hostelId);
                pst.setString(3, title);
                pst.setString(4,content);

                if (pst.executeUpdate() > 0) {

                    rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        notiId = rs.getInt(1);
                    }

                } else {
                    cn.rollback();
                }
                cn.setAutoCommit(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
        return notiId;
    }

}
