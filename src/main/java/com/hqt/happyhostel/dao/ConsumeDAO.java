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
                String sql = "SELECT TOP 2 consume_id, number_electric, number_water, start_consume_date, end_consume_date\n" +
                             "FROM Consumes\n" +
                             "WHERE room_id = ?\n" +
                             "ORDER BY end_consume_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int consumeID = rs.getInt("consume_id");
                        int numberElectric = rs.getInt("number_electric");
                        int numberWater = rs.getInt("number_water");
                        String startConsumeDate = rs.getString("start_consume_date");
                        String endConsumeDate = rs.getString("end_consume_date");
                        consumesList.add(new Consume(consumeID, roomID, numberElectric, numberWater, startConsumeDate, endConsumeDate));
                    }
                }
                if (consumesList.size() == 2) {
                    int consumeID = consumesList.get(0).getConsumeID();
                    String startDate = consumesList.get(0).getStartConsumeDate();
                    String endDate = consumesList.get(0).getEndConsumeDate();
                    int consumeElectricNumber = consumesList.get(0).getNumberElectric() - consumesList.get(1).getNumberElectric();
                    int consumeWaterNumber = consumesList.get(0).getNumberWater() - consumesList.get(1).getNumberWater();
                    consume = new Consume(consumeID, roomID, consumeElectricNumber, consumeWaterNumber, startDate, endDate);
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
                String sql = "SELECT TOP 1 consume_id, number_electric, number_water, start_consume_date , end_consume_date\n" +
                             "FROM Consumes\n" +
                             "WHERE room_id = ?\n" +
                             "ORDER BY end_consume_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int consumeID = rs.getInt("consume_id");
                    int numberElectric = rs.getInt("number_electric");
                    int numberWater = rs.getInt("number_water");
                    String startConsumeDate = rs.getString("start_consume_date");
                    String endConsumeDate = rs.getString("end_consume_date");
                    consume = new Consume(consumeID, roomID, numberElectric, numberWater, startConsumeDate, endConsumeDate);
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

    public static Boolean updateConsumeNumber(int roomID, int numberElectric, int numberWater) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Consumes\n" +
                             "SET number_electric = ?, number_water = ?, end_consume_date = GETDATE()\n" +
                             "WHERE consume_id = (SELECT TOP 1 consume_id\n" +
                             "\t\t\t\t\tFROM Consumes\n" +
                             "\t\t\t\t\tWHERE room_id = ?\n" +
                             "\t\t\t\t\tORDER BY end_consume_date DESC)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, numberElectric);
                pst.setInt(2, numberWater);
                pst.setInt(3, roomID);

                if (pst.executeUpdate() == 0) {
                    isSuccess = false;
                    cn.rollback();
                } else {
                    isSuccess = true;
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
        return isSuccess;
    }

}