package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.Report;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    private static final String INSERT_REPORT =
            "INSERT INTO [dbo].[Reports](send_date, [content], status, reply_account_id, send_account_id, cate_id) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_REPORT = "SELECT * FROM Reports";

    private static final String UPDATE_REPORT_TO_PROCESS =
            "UPDATE Reports SET status = 1, reply_date = GETDATE(), reply = ? WHERE id_report = ?";

    private static final String UPDATE_REPORT_TO_FINISHED =
            "UPDATE Reports SET status = 2, complete_date = GETDATE() WHERE id_report = ?";

    public boolean updateReportToFinished(int reportId) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                ptm = cn.prepareStatement(UPDATE_REPORT_TO_FINISHED);
                ptm.setInt(1, reportId);
                check = ptm.executeUpdate() > 0;

                if (!check) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public boolean updateReportToProcess(int reportId, String replyMsg) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                ptm = cn.prepareStatement(UPDATE_REPORT_TO_PROCESS);
                ptm.setString(1, replyMsg);
                ptm.setInt(2, reportId);
                check = ptm.executeUpdate() > 0;

                if (!check) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public boolean addReport(Report report) throws SQLException {
        boolean check = false;
        Connection cn = null;
        PreparedStatement ptm = null;
        try {
            cn = DBUtils.makeConnection();
            //Insert table Hostel
            if (cn != null) {
                cn.setAutoCommit(false);

                ptm = cn.prepareStatement(INSERT_REPORT);
                ptm.setString(1, report.getSendDate());
                ptm.setString(2, report.getContent());
                ptm.setInt(3, report.getStatus());
                ptm.setInt(4, report.getReplyAccountID());
                ptm.setInt(5, report.getSendAccountID());
                ptm.setInt(6, report.getCateID());
                check = ptm.executeUpdate() > 0;

                if (!check) {
                    cn.rollback();
                } else {
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }

    public List<Report> getReport() throws SQLException {
        List<Report> reports = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn!=null){
                st = cn.createStatement();
                rs = st.executeQuery(GET_REPORT);
                while (rs!=null && rs.next()){
                    int reportID = rs.getInt("id_report");
                    String sendDate = rs.getString("send_date");
                    String content = rs.getString("content");
                    int status = rs.getInt("status");
                    String reply = rs.getString("reply");
                    String completeDate = rs.getString("complete_date");
                    int replyAccountID = rs.getInt("reply_account_id");
                    int sendAccountID = rs.getInt("send_account_id");
                    int cateID = rs.getInt("cate_id");
                    reports.add(Report.builder()
                                    .reportID(reportID)
                                    .sendDate(sendDate)
                                    .content(content)
                                    .status(status)
                                    .reply(reply)
                                    .completeDate(completeDate)
                                    .replyAccountID(replyAccountID)
                                    .sendAccountID(sendAccountID)
                                    .cateID(cateID)
                                    .build());
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
        return reports;
    }

}
