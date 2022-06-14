package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Consume;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsumeDAO {
    public static Consume getNearestConsumeNumber(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Consume consume = null;
        ArrayList<Consume> consumesList = new ArrayList();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 2 consume_id, number_electric, number_water, update_date, status\n" +
                             "FROM Consumes\n" +
                             "WHERE room_id = ?\n" +
                             "ORDER BY update_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int consumeID = rs.getInt("consume_id");
                        int numberElectric = rs.getInt("number_electric");
                        int numberWater = rs.getInt("number_water");
                        String updateConsumeDate = rs.getString("update_date");
                        int status = rs.getInt("status");
                        consumesList.add(Consume.builder()
                                .consumeID(consumeID)
                                .roomID(roomID)
                                .numberElectric(numberElectric)
                                .numberWater(numberWater)
                                .updateDate(updateConsumeDate)
                                .status(status).build());
                    }
                }
                if (consumesList.size() == 2) {
                    int consumeID = consumesList.get(0).getConsumeID();
                    String updateConsumeDate = consumesList.get(0).getUpdateDate();
                    int status = consumesList.get(0).getStatus();
                    int consumeElectricNumber = consumesList.get(0).getNumberElectric() - consumesList.get(1).getNumberElectric();
                    int consumeWaterNumber = consumesList.get(0).getNumberWater() - consumesList.get(1).getNumberWater();
                    consume = Consume.builder()
                            .consumeID(consumeID)
                            .roomID(roomID)
                            .numberElectric(consumeElectricNumber)
                            .numberWater(consumeWaterNumber)
                            .updateDate(updateConsumeDate)
                            .status(status).build();
                } else if (consumesList.size() == 1) {
                    consume = consumesList.get(0);
                } else {
                    consume = null;
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
        return consume;
    }

    public static Consume getNearestConsume(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Consume consume = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 consume_id, number_electric, number_water, update_date, status \n" +
                             "FROM Consumes\n" +
                             "WHERE room_id = ?\n" +
                             "ORDER BY update_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int consumeID = rs.getInt("consume_id");
                    int numberElectric = rs.getInt("number_electric");
                    int numberWater = rs.getInt("number_water");
                    String updateConsumeDate = rs.getString("update_date");
                    int status = rs.getInt("status");
                    consume = Consume.builder()
                            .consumeID(consumeID)
                            .roomID(roomID)
                            .numberElectric(numberElectric)
                            .numberWater(numberWater)
                            .updateDate(updateConsumeDate)
                            .status(status).build();
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
        return consume;
    }

    public static Boolean updateConsumeNumber(Consume consume) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "INSERT INTO Consumes (number_electric, number_water, update_date, status, room_id)\n" +
                             "VALUES (?, ?, GETDATE(), ?, ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, consume.getNumberElectric());
                pst.setInt(2, consume.getNumberWater());
                pst.setInt(3, consume.getStatus());
                pst.setInt(4, consume.getRoomID());

                if (pst.executeUpdate() > 0) {
                    isSuccess = true;
                } else {
                    cn.rollback();
                }
                cn.setAutoCommit(true);
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
        return isSuccess;
    }

}