package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.ReportCategory;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportCategoryDAO {
    private static final String GET_REPORT_CATEGORY = "SELECT * FROM ReportCategory";
    public List<ReportCategory> getReportCategory() throws SQLException {
        List<ReportCategory> reportCategories = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn!=null){
                st = cn.createStatement();
                rs = st.executeQuery(GET_REPORT_CATEGORY);
                while (rs!=null && rs.next()){
                    int cateID = rs.getInt("cate_id");
                    String cateTitle = rs.getString("title");
                    reportCategories.add(new ReportCategory(cateID,cateTitle));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (rs!=null){
                rs.close();
            }
            if (st!=null){
                st.close();
            }
            if (cn!=null){
                cn.close();
            }
        }
        return reportCategories;
    }
}
