package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Consume;
import com.hqt.happyhostel.dto.InfrastructureItem;
import com.hqt.happyhostel.dto.Infrastructures;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDAO {

    public boolean updateInfrastructureStatus(int idInfrastructureRoom, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE InfrastructuresRoom\n" +
                             "SET status = ?\n" +
                             "WHERE id_infrastructure = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, idInfrastructureRoom);

                if (pst.executeUpdate() > 0) {
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

    public ArrayList<Infrastructures> getInfrastructures(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
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
                rs = pst.executeQuery();
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
        return infrastructures;
    }

    public ArrayList<InfrastructureItem> getAllInfrastructure() {
        Connection cn = null;
        Statement pst = null;
        ResultSet rs = null;
        ArrayList<InfrastructureItem> infrastructureItems = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT id_infrastructure_item, infrastructure_name\n" +
                        "FROM InfrastructureItem\n";

                pst = cn.createStatement();

                rs = pst.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int idInfrastructureItem = rs.getInt("id_infrastructure_item");
                        String infrastructureName = rs.getString("infrastructure_name");
                        infrastructureItems.add(new InfrastructureItem(idInfrastructureItem, infrastructureName));
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

        return infrastructureItems;
    }

    public Boolean addNewInfrastructure(int roomID, int quantity, int status, int idInfrastructureItem) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isSuccess = false;
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

                if (pst.executeUpdate() > 0) {
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

    // Renter handler
    private static final String GET_HOSTEL_INFRASTRUCTURE_BY_RENTER_ID =
            "SELECT InfrastructureItem.infrastructure_name,InfrastructuresRoom.quantity\n" +
                    "FROM Accounts INNER JOIN Contracts ON Accounts.account_id=Contracts.renter_id\n" +
                    "INNER JOIN Rooms ON Contracts.room_id=Rooms.room_id \n" +
                    "INNER JOIN InfrastructuresRoom ON Rooms.room_id=InfrastructuresRoom.room_id\n" +
                    "INNER JOIN InfrastructureItem ON InfrastructuresRoom.id_infrastructure_item=InfrastructureItem.id_infrastructure_item\n" +
                    "WHERE Accounts.account_id = ?";
    public List<Infrastructures> getHostelInfrastructuresByRenterId(int renterId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Infrastructures> infrastructures = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_HOSTEL_INFRASTRUCTURE_BY_RENTER_ID);
                pst.setInt(1, renterId);
                rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    String infrastructureName = rs.getString("infrastructure_name");
                    int quantity = rs.getInt("quantity");
                    infrastructures.add(Infrastructures
                            .builder()
                            .name(infrastructureName)
                            .quantity(quantity)
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
        return infrastructures;
    }

}
