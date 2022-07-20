package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(name = "HostelOwnerPageServlet", value = "/HostelOwnerPageServlet")
public class HostelOwnerPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = "HostelOwner";
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("USER");
        try {
            //Get now date time
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date result = cal.getTime();
            int startDay = cal.getActualMinimum(Calendar.DATE);
            int endDay = cal.getActualMaximum(Calendar.DATE);
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");

            String str = result.toString();
            int[] listMoneyEachMonth = {0, 0, 0, 0, 0, 0};
            int[] listSixMonthAgo = GetListMonthOfSixMonthAgoByHostelId();
            int[] listMoneyOfOneAndTwoMonthAgo = {0, 0};
            int totalMoneyOfHotel = 0;
            int totalMonthOfHostel = 0;
            int totalReportDamageOfHostel = 0;
            int[] listReportByReportStatus = {0, 0, 0};
            int averageDamageReport = 0, averageMoneyOfHotel = 0, comparePercentOfTwoMonthAgo = 0;
            HostelDAO hostelDAO = new HostelDAO();
            List<Hostel> listHostel = hostelDAO.getHostelByOwnerId(account.getAccId());
            if (listHostel.size() > 0){
                Random rand = new Random();
                int randomHostelId = rand.nextInt(listHostel.size());
                //fix cung
                int hostelId = listHostel.get(0).getHostelID();
                //int hostelId = 1;
                listMoneyEachMonth = GetListMoneyEachMonthOfSixMonthAgoByHostelId(hostelId);
                listMoneyOfOneAndTwoMonthAgo = GetMoneyOfOnMonthAgoAndTwoMonthAgoByHostelId(hostelId, listSixMonthAgo[0], listSixMonthAgo[1]);
                totalMoneyOfHotel = GetTotalMoneyByHostelId(hostelId);
                totalMonthOfHostel = GetTotalMonthByHostelId(hostelId);
                totalReportDamageOfHostel = GetTotalReportDamageByHostelId(hostelId);
                listReportByReportStatus = GetNumberNewReportWaittingReportDoneReportByHostelId(hostelId);
                if (totalMonthOfHostel != 0) {
                    averageDamageReport = totalReportDamageOfHostel / totalMonthOfHostel;
                    averageMoneyOfHotel = totalMoneyOfHotel / totalMonthOfHostel;
                }
                if (listMoneyOfOneAndTwoMonthAgo[1] != 0) {
                    comparePercentOfTwoMonthAgo = (listMoneyOfOneAndTwoMonthAgo[0] / listMoneyOfOneAndTwoMonthAgo[1]) * 100;
                }
            }else{
                session.setAttribute("MES" , "nothing");
            }

            request.setAttribute("listMoneyEachMonth", listMoneyEachMonth);
            request.setAttribute("listSixMonthAgo", listSixMonthAgo);
            request.setAttribute("newReport", listReportByReportStatus[0]);
            request.setAttribute("numberHostel", listHostel.size());
            request.setAttribute("waittingReport", listReportByReportStatus[1]);
            request.setAttribute("doneReport", listReportByReportStatus[2]);
            request.setAttribute("timeNow", formatter1.format(formatter.parse(str)).substring(3, 10));
            request.setAttribute("averageReport", averageDamageReport);
            request.setAttribute("startDay", startDay);
            request.setAttribute("endDay", endDay);
            request.setAttribute("listMoneyOfOneAndTwoMonthAgo", listMoneyOfOneAndTwoMonthAgo);
            request.setAttribute("averageMoneyOfHotel", averageMoneyOfHotel);
            request.setAttribute("comparePercentOfTwoMonthAgo", comparePercentOfTwoMonthAgo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
        }
    }

    //Input hostel ID return list money of each month of 6 month recent
    protected int[] GetListMoneyEachMonthOfSixMonthAgoByHostelId(int hostelID) throws Exception {
        Date date = new Date();
        BillDAO billDAO = new BillDAO();
        int month6 = 0, month5 = 0, month4 = 0, month3 = 0, month2 = 0, month1 = 0;
        int moneyInMonth6 = 0, moneyInMonth5 = 0, moneyInMonth4 = 0, moneyInMonth3 = 0, moneyInMonth2 = 0, moneyInMonth1 = 0;
        Calendar cal = Calendar.getInstance();
        Date result;
        result = cal.getTime();
        int year6 = result.getYear() + 1900, year5 = result.getYear() + 1900, year4 = result.getYear() + 1900, year3 = result.getYear() + 1900, year2 = result.getYear() + 1900, year1 = result.getYear() + 1900;
        month6 = result.getMonth() != 0 ? result.getMonth() : 12;
        if (result.getMonth() == 0)
            year6 = year6 - 1;
        List<Bill> listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year6, month6);
        for (Bill item : listBillInMonth) {
            if (item.getStatus() == 1) {
                moneyInMonth6 += item.getTotalMoney();
            }
        }

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month5 = result.getMonth() != 0 ? result.getMonth() : 12;
        if (result.getMonth() == 0) year5 = year5 - 1;
        listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year5, month5);
        for (Bill item : listBillInMonth) {
            if (item.getStatus() == 1) {
                moneyInMonth5 += item.getTotalMoney();
            }
        }

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month4 = result.getMonth() != 0 ? result.getMonth() : 12;
        if (result.getMonth() == 0) year4 = year4 - 1;
        listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year4, month4);
        for (Bill item : listBillInMonth) {
            if (item.getStatus() == 1) {
                moneyInMonth4 += item.getTotalMoney();
            }
        }

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month3 = result.getMonth() != 0 ? result.getMonth() : 12;
        if (result.getMonth() == 0) year3 = year3 - 1;
        listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year3, month3);
        for (Bill item : listBillInMonth) {
            if (item.getStatus() == 1) {
                moneyInMonth3 += item.getTotalMoney();
            }
        }

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month2 = result.getMonth() != 0 ? result.getMonth() : 12;
        if (result.getMonth() == 0) year2 = year2 - 1;
        listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year2, month2);
        for (Bill item : listBillInMonth) {
            if (item.getStatus() == 1) {
                moneyInMonth2 += item.getTotalMoney();
            }
        }

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month1 = result.getMonth() != 0 ? result.getMonth() : 12;
        if (result.getMonth() == 0) year1 = year1 - 1;
        listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year1, month1);
        for (Bill item : listBillInMonth) {
            if (item.getStatus() == 1) {
                moneyInMonth1 += item.getTotalMoney();
            }
        }
        int[] listMoneyInSixMonth = {moneyInMonth6, moneyInMonth5, moneyInMonth4, moneyInMonth3, moneyInMonth2, moneyInMonth1};
        return listMoneyInSixMonth;
    }

    //Input hostel ID return list six month ago
    protected int[] GetListMonthOfSixMonthAgoByHostelId() throws Exception {
        int month6 = 0, month5 = 0, month4 = 0, month3 = 0, month2 = 0, month1 = 0;
        Calendar cal = Calendar.getInstance();
        Date result;
        result = cal.getTime();
        int defauYear = result.getYear() + 1900;
        int year6 = defauYear, year5 = defauYear, year4 = defauYear, year3 = defauYear, year2 = defauYear, year1 = defauYear;
        month6 = result.getMonth() != 0 ? result.getMonth() : 12;

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month5 = result.getMonth() != 0 ? result.getMonth() : 12;

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month4 = result.getMonth() != 0 ? result.getMonth() : 12;

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month3 = result.getMonth() != 0 ? result.getMonth() : 12;

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month2 = result.getMonth() != 0 ? result.getMonth() : 12;

        cal.add(Calendar.MONTH, -1);
        result = cal.getTime();
        month1 = result.getMonth() != 0 ? result.getMonth() : 12;
        int[] listMonth = {month6, month5, month4, month3, month2, month1};
        return listMonth;
    }

    //Input Hostel ID return list include two total money of 1 month ago and 2 month ago
    protected int[] GetMoneyOfOnMonthAgoAndTwoMonthAgoByHostelId(int hostelID, int oneMonthAgo, int twoMonthAgo) throws Exception {
        BillDAO billDAO = new BillDAO();
        Calendar cal = Calendar.getInstance();
        Date result;
        result = cal.getTime();
        int moneyOneMonthAgo = 0, moneyTwoMonthAgo = 0;
        int year1 = result.getYear() + 1900, year2 = result.getYear() + 1900;
        if (oneMonthAgo == 1) {
            twoMonthAgo = 12;
            year2 = year2 - 1;
        } else if (oneMonthAgo == 0) {
            oneMonthAgo = 12;
            year1 = year1 - 1;
            year2 = year2 - 1;
        }
        List<Bill> listBillOneMonthAgo = billDAO.GetListBillByHostelIdMonthYear(hostelID, year1, oneMonthAgo);
        for (Bill item : listBillOneMonthAgo) {
            if (item.getStatus() == 1){
                moneyOneMonthAgo += item.getTotalMoney();
            }
        }
        List<Bill> listBillTwoMonthAgo = billDAO.GetListBillByHostelIdMonthYear(hostelID, year2, twoMonthAgo);
        for (Bill item : listBillTwoMonthAgo) {
            if (item.getStatus() == 1) {
                moneyTwoMonthAgo += item.getTotalMoney();
            }
        }
        int[] listMoney = {moneyOneMonthAgo, moneyTwoMonthAgo};
        return listMoney;
    }

    //Input hostel id return total money of hostel
    protected int GetTotalMoneyByHostelId(int hostelID) throws Exception {
        BillDAO billDAO = new BillDAO();
        int totalMoney = 0;
        List<Bill> listBillByHostelID = billDAO.GetBillByHostelId(hostelID);
        for (Bill item : listBillByHostelID) {
            if (item.getStatus() == 1) {
                totalMoney += item.getTotalMoney();
            }
        }
        return totalMoney;
    }

    //Input hostel id return all month when hostel is activated form here
    protected int GetTotalMonthByHostelId(int hostelID) throws Exception {
        BillDAO billDAO = new BillDAO();
        List<Bill> listBillByHostelID = billDAO.GetBillByHostelId(hostelID);
        List<String> listMonth = new ArrayList<>();

        boolean check;
        for (int i = 0; i < listBillByHostelID.size(); i++) {
            if (listBillByHostelID.get(i).getStatus() == 1){
                if (listMonth.size() == 0){
                    listMonth.add(listBillByHostelID.get(i).getCreatedDate().substring(5, 7));
                }
                check = true;
                for (int j = 0; j < listMonth.size(); j++) {
                    if (listMonth.get(j).equals(listBillByHostelID.get(i).getCreatedDate().substring(5, 7)))
                        check = false;
                }
                if (check)
                    listMonth.add(listBillByHostelID.get(i).getCreatedDate().substring(5, 7));
            }
        }
        return listMonth.size();
    }

    //Input hostel id return number report of hostel
    protected int GetTotalReportDamageByHostelId(int hostelID) throws SQLException {
        ReportDAO reportDAO = new ReportDAO();
        List<Report> listReport = reportDAO.getListReportByHostelId(hostelID);
        int totalReportDamage = 0;
        for (Report item:listReport) {
            if (item.getCateID() == 1)
                totalReportDamage++;
        }
        return totalReportDamage;
    }

    //Input hostel id return list report of hostel
    protected int[] GetNumberNewReportWaittingReportDoneReportByHostelId(int hostelID) throws SQLException {
        ReportDAO reportDAO = new ReportDAO();
        List<Report> listReport = reportDAO.getListReportByHostelId(hostelID);
        int newReport = 0, waittingReport = 0, doneReport = 0;
        for (Report item : listReport) {
            if (item.getStatus() == 0)
                newReport++;
            else if (item.getStatus() == 1) {
                waittingReport++;
            } else {
                doneReport++;
            }
        }
        int[] listReportByReportStatus = {newReport, waittingReport, doneReport};
        return listReportByReportStatus;
    }

}
