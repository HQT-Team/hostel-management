package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.ReportCategory;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportCategoryDAO {

    private static final String GET_REPORT_CATEGORY = "SELECT * FROM ReportCategory";
    private static final String GET_REPORT_CATEGORY_BY_ID = "SELECT  cate_id, title FROM ReportCategory WHERE cate_id = ?";

    public ReportCategory getReportCategoryById(int cateId) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ReportCategory reportCategory = new ReportCategory();
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                pst = conn.prepareStatement(GET_REPORT_CATEGORY_BY_ID);
                pst.setInt(1, cateId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int cateID = rs.getInt("cate_id");
                    String cateTitle = rs.getString("title");
                    reportCategory = new ReportCategory(cateID, cateTitle);
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
            if (conn != null) {
                conn.close();
            }
        }
        return reportCategory;
    }

    public List<ReportCategory> getReportCategory() throws SQLException {
        List<ReportCategory> reportCategories = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                st = cn.createStatement();
                rs = st.executeQuery(GET_REPORT_CATEGORY);
                while (rs != null && rs.next()) {
                    int cateID = rs.getInt("cate_id");
                    String cateTitle = rs.getString("title");
                    reportCategories.add(new ReportCategory(cateID, cateTitle));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return reportCategories;
    }
}
