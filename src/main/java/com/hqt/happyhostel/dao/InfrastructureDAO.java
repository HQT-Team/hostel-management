package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Consume;
import com.hqt.happyhostel.dto.InfrastructureItem;
import com.hqt.happyhostel.dto.Infrastructures;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDAO {

    public static boolean updateInfrastructureStatus(int idInfrastructureRoom, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        Boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE InfrastructuresRoom\n" +
                        "SET status = ?\n" +
                        "WHERE id_infrastructure = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, idInfrastructureRoom);

                int rows = pst.executeUpdate();
                if (rows == 0) {
                    isSuccess = false;
                } else {
                    isSuccess = true;
                }
            }
            cn.close();
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

    public static ArrayList<Infrastructures> getInfrastructures(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ArrayList<Infrastructures> infrastructures = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT id_infrastructure, quantity, status, infrastructure_name\n" +
                        "FROM InfrastructuresRoom I, InfrastructureItem IT\n" +
                        "WHERE I.room_id = ?\n" +
                        "AND I.id_infrastructure_item = IT.id_infrastructure_item";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("id_infrastructure");
                        String name = rs.getString("infrastructure_name");
                        int quantity = rs.getInt("quantity");
                        int status = rs.getInt("status");
                        infrastructures.add(new Infrastructures(id, name, quantity, status));
                    }
                }
            }
            cn.close();
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
        return infrastructures;
    }

    public static ArrayList<InfrastructureItem> getAllInfrastructure() {
        Connection cn = null;
        Statement pst = null;
        ArrayList<InfrastructureItem> infrastructureItems = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT id_infrastructure_item, infrastructure_name\n" +
                        "FROM InfrastructureItem\n";

                pst = cn.createStatement();

                ResultSet rs = pst.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int idInfrastructureItem = rs.getInt("id_infrastructure_item");
                        String infrastructureName = rs.getString("infrastructure_name");
                        infrastructureItems.add(new InfrastructureItem(idInfrastructureItem, infrastructureName));
                    }
                }
            }
            cn.close();
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

        return infrastructureItems;
    }

    public static Boolean addNewInfrastructure(int roomID, int quantity, int status, int idInfrastructureItem) {
        Connection cn = null;
        PreparedStatement pst = null;
        Boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (?, ?, ?, ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);
                pst.setInt(2, quantity);
                pst.setInt(3, status);
                pst.setInt(4, idInfrastructureItem);

                int rows = pst.executeUpdate();
                if (rows == 0) {
                    isSuccess = false;
                } else {
                    isSuccess = true;
                }
            }
            cn.close();
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
