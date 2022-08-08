package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.InfrastructureItem;
import com.hqt.happyhostel.dto.Services;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfrastructureItemDAO {

    private static final String GET_INFRASTRUCTURE_BY_NAME =
            "SELECT id_infrastructure_item, infrastructure_name FROM InfrastructureItem WHERE infrastructure_name = ?";
    private static final String UPDATE_INFRASTRUCTURE =
            "Update InfrastructureItem SET infrastructure_name = ? WHERE id_infrastructure_item = ?";
    private static final String CREATE_NEW_UPDATE_INFRASTRUCTURE =
            "INSERT INTO InfrastructureItem(infrastructure_name) VALUES(?)";

    public boolean createNewInfrastructureItem(String infrastructureName) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                pst = cn.prepareStatement(CREATE_NEW_UPDATE_INFRASTRUCTURE);
                pst.setString(1, infrastructureName);
                check = pst.executeUpdate() > 0;
                if (!check) {
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
        return check;
    }

    public boolean updateInfrastructureItem(InfrastructureItem infrastructureItem) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                pst = cn.prepareStatement(UPDATE_INFRASTRUCTURE);
                pst.setString(1, infrastructureItem.getInfrastructureName());
                pst.setInt(2, infrastructureItem.getIdInfrastructureItem());
                check = pst.executeUpdate() > 0;
                if (!check) {
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
        return check;
    }

    public InfrastructureItem getInfrastructureByName(String infrastructureName) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        InfrastructureItem infrastructureItem = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(GET_INFRASTRUCTURE_BY_NAME);
                pst.setString(1, infrastructureName);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    infrastructureItem = InfrastructureItem.builder().build();
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
        return infrastructureItem;
    }
}
