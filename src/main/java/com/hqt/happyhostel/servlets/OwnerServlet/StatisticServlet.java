package com.hqt.happyhostel.servlets.OwnerServlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "StatisticServlet", value = "/StatisticServlet")
public class StatisticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = "statistic-page";
        HttpSession session = request.getSession();
        BillDAO billDAO = new BillDAO();
        HostelDAO hostelDAO = new HostelDAO();
        ContractDAO contractDAO = new ContractDAO();
        ReportDAO reportDAO = new ReportDAO();
        RoomDAO roomDAO = new RoomDAO();
       Account account = (Account) session.getAttribute("USER");
        int totalMoney = 0;
        int expenseMoney = 0;
        int revenueMoney = 0;
        int billLength = 0;
        ArrayList<String> listYear = new ArrayList<String>();

        try {

            List<Hostel> listHostel = hostelDAO.getHostelByOwnerId(account.getAccId());
            if (listHostel.size() == 0){
                request.setAttribute("error", "Ban chua co phong tro!");
                request.getRequestDispatcher(URL).forward(request, response);

            }
            ArrayList<Bill> listBillByHostel = billDAO.GetListBillByHostel(listHostel.get(0).getHostelName());
            boolean check;
            for (int i = 0; i < listBillByHostel.size(); i++) {
                String tmpYear = listBillByHostel.get(i).getCreatedDate().substring(0, 4);
                if (listYear.size() == 0) {
                    listYear.add(tmpYear);
                }
                check = true;
                for (int j = 0; j < listYear.size(); j++) {
                    if (listYear.get(j).equalsIgnoreCase(tmpYear)) {
                        check = false;
                    }
                    if (check) {
                        listYear.add(tmpYear);
                        check = false;
                    }
                }
            }
            ArrayList<Bill> listBillByFixValue = billDAO.GetListBillByHostelYearQuater(listHostel.get(0).getHostelName(), listYear.get(0), "quater_1");

            //lấy tất cả các năm mà khu trọ đã hoạt động

            for (int i = 0; i < listBillByFixValue.size(); i++) {
                totalMoney += listBillByFixValue.get(i).getTotalMoney();
            }

            if (totalMoney > 100000000)
                expenseMoney = (totalMoney / 100) * 5;
            revenueMoney = totalMoney - expenseMoney;
            int totalMoneyMonth1 = 0;
            int totalMoneyMonth2 = 0;
            int totalMoneyMonth3 = 0;

            for (int i = 0; i < listBillByFixValue.size(); i++) {
                if (listBillByFixValue.get(i).getCreatedDate().substring(5, 7).equalsIgnoreCase("04")) {
                    totalMoneyMonth1 += listBillByFixValue.get(i).getTotalMoney();
                } else if (listBillByFixValue.get(i).getCreatedDate().substring(5, 7).equalsIgnoreCase("05")) {
                    totalMoneyMonth2 += listBillByFixValue.get(i).getTotalMoney();
                } else if (listBillByFixValue.get(i).getCreatedDate().substring(5, 7).equalsIgnoreCase("06")) {
                    totalMoneyMonth3 += listBillByFixValue.get(i).getTotalMoney();
                }
            }


            ArrayList<Contract> listContractByFixValue;
            listContractByFixValue = contractDAO.GetListContractByHostelYearQuater(listHostel.get(0).getHostelName(), listYear.get(0), "quater_1");
            int contractCancelRate1 = 0;
            int contractCancelRate2 = 0;
            int contractCancelRate3 = 0;
            int contractCreateRate1 = 0;
            int contractCreateRate2 = 0;
            int contractCreateRate3 = 0;
            Date expriDate = new Date();
            Date cancelDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //String abc = listContractByFixValue.get(i).getExpiration().substring(0, 10);
            for (int i = 0; i < listContractByFixValue.size(); i++) {
                if (listContractByFixValue.get(i).getStartDate().substring(5, 7).equals("01")) {
                    contractCreateRate1++;
                    expriDate = dateFormat.parse(listContractByFixValue.get(i).getExpiration().substring(0, 10));
                    String tmpDate = listContractByFixValue.get(i).getCancelDate() == null ? null : listContractByFixValue.get(i).getCancelDate().substring(0, 10);
                    if (tmpDate != null) {
                        cancelDate = dateFormat.parse(tmpDate);
                        if (cancelDate.before(expriDate)) {
                            contractCancelRate1++;
                        }
                    }

                } else if (listContractByFixValue.get(i).getStartDate().substring(5, 7).equals("02")) {
                    contractCreateRate2++;
                    expriDate = dateFormat.parse(listContractByFixValue.get(i).getExpiration().substring(0, 10));
                    String tmpDate = listContractByFixValue.get(i).getCancelDate() == null ? null : listContractByFixValue.get(i).getCancelDate().substring(0, 10);
                    if (tmpDate != null) {
                        cancelDate = dateFormat.parse(tmpDate);
                        if (cancelDate.before(expriDate)) {
                            contractCancelRate2++;
                        }
                    }

                } else if (listContractByFixValue.get(i).getStartDate().substring(5, 7).equals("03")) {
                    contractCreateRate3++;
                    expriDate = dateFormat.parse(listContractByFixValue.get(i).getExpiration().substring(0, 10));
                    String tmpDate = listContractByFixValue.get(i).getCancelDate() == null ? null : listContractByFixValue.get(i).getCancelDate().substring(0, 10);
                    if (tmpDate != null) {
                        cancelDate = dateFormat.parse(tmpDate);
                        if (cancelDate.before(expriDate)) {
                            contractCancelRate3++;
                        }
                    }
                }
            }

            List<Report> listReport;
            listReport = reportDAO.getReports();
            var rate = 0;
            int rep = 0;
            int notyet = 0;
            for (int i = 0; i < listReport.size(); i++) {
                if (listReport.get(i).getReply() != null) {
                    rep++;
                } else {
                    notyet++;
                }
            }
            if (notyet != 0)
                rate = rep / notyet;

            List<Room> listRoom;
            int numberEmpty = 0;
            int numberRenting = 0;
            int numberContract = 0;
            listRoom = roomDAO.getListRoomsByHostelId(listHostel.get(0).getHostelID());
            for (int i = 0; i < listRoom.size(); i++) {
                if (listRoom.get(i).getRoomStatus() == 0) {
                    numberEmpty++;
                } else if (listRoom.get(i).getRoomStatus() == 1) {
                    numberRenting++;
                } else {
                    numberContract++;
                }
            }

            request.setAttribute("listHostel", listHostel);
            request.setAttribute("rate", rate);
            request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
            request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
            request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
            request.setAttribute("numberEmpty", numberEmpty);
            request.setAttribute("numberRenting", numberRenting);
            request.setAttribute("numberContract", numberContract);
            request.setAttribute("contractCancelRate1", contractCancelRate1);
            request.setAttribute("contractCancelRate2", contractCancelRate2);
            request.setAttribute("contractCancelRate3", contractCancelRate3);
            request.setAttribute("contractCreateRate1", contractCreateRate1);
            request.setAttribute("contractCreateRate2", contractCreateRate2);
            request.setAttribute("contractCreateRate3", contractCreateRate3);
            request.setAttribute("listBillByFixValue", listBillByFixValue);
            request.setAttribute("listYear", listYear);
            request.setAttribute("totalMoney", totalMoney);
            request.setAttribute("expenseMoney", expenseMoney);
            request.setAttribute("revenueMoney", revenueMoney);
            request.setAttribute("listReport", listReport);
            request.setAttribute("hostelName", null);
            request.setAttribute("year", null);
            request.setAttribute("quater", null);
            session.setAttribute("CURRENT_PAGE", "statistic");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = "statistic-page";
        String hostelName = request.getParameter("select-hostel");
        String year = request.getParameter("select-year") != null ? request.getParameter("select-year"): "2022";
        String quater = request.getParameter("select-quater") != null ? request.getParameter("select-quater"): "quater_1";
        BillDAO billDAO = new BillDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("USER");
        HostelDAO hostelDAO = new HostelDAO();
        ContractDAO contractDAO = new ContractDAO();
        ReportDAO reportDAO = new ReportDAO();
        RoomDAO roomDAO = new RoomDAO();
        int totalMoney = 0;
        int expenseMoney = 0;
        int revenueMoney = 0;
        int billLength = 0;

        String month1 = "0";
        String month2 = "0";
        String month3 = "0";

        if (quater.equals("quater_1")){
            month1 = "1";
            month2 = "2";
            month3 = "3";
        }else if (quater.equals("quater_2")){
            month1 = "4";
            month2 = "5";
            month3 = "6";
        }else if (quater.equals("quater_3")) {
            month1 = "7";
            month2 = "8";
            month3 = "9";
        }else if (quater.equals("quater_4")) {
            month1 = "10";
            month2 = "11";
            month3 = "12";
        }

        ArrayList<String> listYear = new ArrayList<String>();
        try {

            ArrayList<Hostel> listHostel = (ArrayList<Hostel>) hostelDAO.getHostelByOwnerId(account.getAccId());
            ArrayList<Bill> listBillByHostel = billDAO.GetListBillByHostel(hostelName);
            if (listHostel.size() == 0){
                request.setAttribute("error", "Ban chua co phong tro!");
                request.getRequestDispatcher(URL).forward(request, response);

            }
            if (listBillByHostel.size()==0)
                year=null;
            boolean check;
            for (int i = 0; i < listBillByHostel.size(); i++) {
                String tmpYear = listBillByHostel.get(i).getCreatedDate().substring(0, 4);
                if (listYear.size() == 0) {
                    listYear.add(tmpYear);
                }
                check = true;
                for (int j = 0; j < listYear.size(); j++) {
                    if (listYear.get(j).equalsIgnoreCase(tmpYear)) {
                        check = false;
                    }
                    if (check) {
                        listYear.add(tmpYear);
                        check = false;
                    }
                }
            }
            ArrayList<Bill> listBillByFixValue = billDAO.GetListBillByHostelYearQuater(hostelName, year, quater);

            //lấy tất cả các năm mà khu trọ đã hoạt động

            for (int i = 0; i < listBillByFixValue.size(); i++) {
                totalMoney += listBillByFixValue.get(i).getTotalMoney();
            }

            if (totalMoney > 100000000)
                expenseMoney = (totalMoney / 100) * 5;
            revenueMoney = totalMoney - expenseMoney;
            int totalMoneyMonth1 = 0;
            int totalMoneyMonth2 = 0;
            int totalMoneyMonth3 = 0;

            for (int i = 0; i < listBillByFixValue.size(); i++) {
                if (listBillByFixValue.get(i).getCreatedDate().substring(6, 7).equalsIgnoreCase(month1)) {
                    totalMoneyMonth1 += listBillByFixValue.get(i).getTotalMoney();
                } else if (listBillByFixValue.get(i).getCreatedDate().substring(6, 7).equalsIgnoreCase(month2)) {
                    totalMoneyMonth2 += listBillByFixValue.get(i).getTotalMoney();
                } else if (listBillByFixValue.get(i).getCreatedDate().substring(6, 7).equalsIgnoreCase(month3)) {
                    totalMoneyMonth3 += listBillByFixValue.get(i).getTotalMoney();
                }
            }


            ArrayList<Contract> listContractByFixValue;
            listContractByFixValue = contractDAO.GetListContractByHostelYearQuater(hostelName, year, quater);
            int contractCancelRate1 = 0;
            int contractCancelRate2 = 0;
            int contractCancelRate3 = 0;
            int contractCreateRate1 = 0;
            int contractCreateRate2 = 0;
            int contractCreateRate3 = 0;
            Date expriDate = new Date();
            Date cancelDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < listContractByFixValue.size(); i++) {
                if (listContractByFixValue.get(i).getStartDate().substring(6, 7).equals(month1)) {
                    contractCreateRate1++;
                    expriDate = dateFormat.parse(listContractByFixValue.get(i).getExpiration().substring(0, 10));
                    String tmpDate = listContractByFixValue.get(i).getCancelDate() == null ? null : listContractByFixValue.get(i).getCancelDate().substring(0, 10);
                    if (tmpDate != null) {
                        cancelDate = dateFormat.parse(tmpDate);
                        if (cancelDate.before(expriDate)) {
                            contractCancelRate1++;
                        }
                    }

                } else if (listContractByFixValue.get(i).getStartDate().substring(6, 7).equals(month2)) {
                    contractCreateRate2++;
                    expriDate = dateFormat.parse(listContractByFixValue.get(i).getExpiration().substring(0, 10));
                    String tmpDate = listContractByFixValue.get(i).getCancelDate() == null ? null : listContractByFixValue.get(i).getCancelDate().substring(0, 10);
                    if (tmpDate != null) {
                        cancelDate = dateFormat.parse(tmpDate);
                        if (cancelDate.before(expriDate)) {
                            contractCancelRate2++;
                        }
                    }

                } else if (listContractByFixValue.get(i).getStartDate().substring(6, 7).equals(month3)) {
                    contractCreateRate3++;
                    expriDate = dateFormat.parse(listContractByFixValue.get(i).getExpiration().substring(0, 10));
                    String tmpDate = listContractByFixValue.get(i).getCancelDate() == null ? null : listContractByFixValue.get(i).getCancelDate().substring(0, 10);
                    if (tmpDate != null) {
                        cancelDate = dateFormat.parse(tmpDate);
                        if (cancelDate.before(expriDate)) {
                            contractCancelRate3++;
                        }
                    }
                }
            }

            List<Report> listReport;
            listReport = reportDAO.getReportByhostel(hostelName);
            var rate = 0;
            int rep = 0;
            int notyet = 0;
            for (int i = 0; i < listReport.size(); i++) {
                if (listReport.get(i).getReply() != null) {
                    rep++;
                } else {
                    notyet++;
                }
            }
            if (notyet != 0)
                rate = rep / notyet;

            List<Room> listRoom;
            int numberEmpty = 0;
            int numberRenting = 0;
            int numberContract = 0;
            int hostelID = 0;
            for (int i = 0; i < listHostel.size(); i++) {
                if (listHostel.get(i).getHostelName().equals(hostelName))
                    hostelID = listHostel.get(i).getHostelID();
            }
            listRoom = roomDAO.getListRoomsByHostelId(hostelID);
            for (int i = 0; i < listRoom.size(); i++) {
                if (listRoom.get(i).getRoomStatus() == 0) {
                    numberEmpty++;
                } else if (listRoom.get(i).getRoomStatus() == 1) {
                    numberRenting++;
                } else {
                    numberContract++;
                }
            }

            ArrayList<Bill> listBillByYear = new ArrayList<>();
            listBillByYear = billDAO.GetListBillByHostelAndYear(hostelName, year);

            int month_1 = 0;
            int month_2 = 0;
            int month_3 = 0;
            int month_4 = 0;
            int month_5 = 0;
            int month_6 = 0;
            int month_7 = 0;
            int month_8 = 0;
            int month_9 = 0;
            int month_10 = 0;
            int month_11= 0;
            int month_12 = 0;
            for (int i = 0; i < listBillByYear.size(); i++){
                switch (listBillByYear.get(i).getCreatedDate().substring(5, 7)){
                    case "01": month_1+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "02": month_2+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "03": month_3+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "04": month_4+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "05": month_5+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "06": month_6+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "07": month_7+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "08": month_8+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "09": month_9+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "10": month_10+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "11": month_11+= listBillByYear.get(i).getTotalMoney();
                        break;
                    case "12": month_12+= listBillByYear.get(i).getTotalMoney();
                        break;
                }
            }
            int cancelMonth_1 = 0;
            int cancelMonth_2 = 0;
            int cancelMonth_3 = 0;
            int cancelMonth_4 = 0;
            int cancelMonth_5 = 0;
            int cancelMonth_6 = 0;
            int cancelMonth_7 = 0;
            int cancelMonth_8 = 0;
            int cancelMonth_9 = 0;
            int cancelMonth_10 = 0;
            int cancelMonth_11 = 0;
            int cancelMonth_12 = 0;
            int createMonth_1 = 0;
            int createMonth_2 = 0;
            int createMonth_3 = 0;
            int createMonth_4 = 0;
            int createMonth_5 = 0;
            int createMonth_6 = 0;
            int createMonth_7 = 0;
            int createMonth_8 = 0;
            int createMonth_9 = 0;
            int createMonth_10 = 0;
            int createMonth_11 = 0;
            int createMonth_12 = 0;
            ArrayList<Contract> listContract1 = contractDAO.GetListContractByHostelYear(hostelName, year);
            Date expriDate2;
            String tmpCancelDate;
            for (int i = 0; i < listContract1.size(); i++){
                switch (listContract1.get(i).getStartDate().substring(5, 7)){
                    case "01":
                        createMonth_1++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_1++;
                            }
                        }
                        break;
                    case "02":
                        createMonth_2++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_2++;
                            }
                        }
                        break;
                    case "03":
                        createMonth_3++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_3++;
                            }
                        }
                        break;
                    case "04":
                        createMonth_4++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_4++;
                            }
                        }
                        break;
                    case "05":
                        createMonth_5++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_5++;
                            }
                        }
                        break;
                    case "06":
                        createMonth_6++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_6++;
                            }
                        }
                        break;
                    case "07":
                        createMonth_7++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_7++;
                            }
                        }
                        break;
                    case "08":
                        createMonth_8++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_8++;
                            }
                        }
                        break;
                    case "09":
                        createMonth_9++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_9++;
                            }
                        }
                        break;
                    case "10":
                        createMonth_10++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_10++;
                            }
                        }
                        break;
                    case "11":
                        createMonth_11++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_11++;
                            }
                        }
                        break;
                    case "12":
                        createMonth_12++;
                        expriDate2 = dateFormat.parse(listContract1.get(i).getExpiration().substring(0, 10));
                        tmpCancelDate = listContract1.get(i).getCancelDate() == null ? null: listContract1.get(i).getCancelDate().substring(0, 10);
                        if (tmpCancelDate != null) {
                            Date CancelDate = dateFormat.parse(tmpCancelDate);
                            if (CancelDate.before(expriDate2)){
                                cancelMonth_12++;
                            }
                        }
                        break;
                }
            }

            request.setAttribute("listHostel", listHostel);
            request.setAttribute("sizeListHostel", listBillByHostel.size());
            request.setAttribute("rate", rate);
            request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
            request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
            request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
            request.setAttribute("numberEmpty", numberEmpty);
            request.setAttribute("numberRenting", numberRenting);
            request.setAttribute("numberContract", numberContract);
            request.setAttribute("contractCancelRate1", contractCancelRate1);
            request.setAttribute("contractCancelRate2", contractCancelRate2);
            request.setAttribute("contractCancelRate3", contractCancelRate3);
            request.setAttribute("contractCreateRate1", contractCreateRate1);
            request.setAttribute("contractCreateRate2", contractCreateRate2);
            request.setAttribute("contractCreateRate3", contractCreateRate3);
            request.setAttribute("listBillByFixValue", listBillByFixValue);
            request.setAttribute("month_1", month_1);
            request.setAttribute("month_2", month_2);
            request.setAttribute("month_3", month_3);
            request.setAttribute("month_4", month_4);
            request.setAttribute("month_5", month_5);
            request.setAttribute("month_6", month_6);
            request.setAttribute("month_7", month_7);
            request.setAttribute("month_8", month_8);
            request.setAttribute("month_9", month_9);
            request.setAttribute("month_10", month_10);
            request.setAttribute("month_11", month_11);
            request.setAttribute("month_12", month_12);
            request.setAttribute("listYear", listYear);


            request.setAttribute("createMonth_1", createMonth_1);
            request.setAttribute("createMonth_2", createMonth_2);
            request.setAttribute("createMonth_3", createMonth_3);
            request.setAttribute("createMonth_4", createMonth_4);
            request.setAttribute("createMonth_5", createMonth_5);
            request.setAttribute("createMonth_6", createMonth_6);
            request.setAttribute("createMonth_7", createMonth_7);
            request.setAttribute("createMonth_8", createMonth_8);
            request.setAttribute("createMonth_9", createMonth_9);
            request.setAttribute("createMonth_10", createMonth_10);
            request.setAttribute("createMonth_11", createMonth_11);
            request.setAttribute("createMonth_12", createMonth_12);
            request.setAttribute("cancelMonth_1", cancelMonth_1);
            request.setAttribute("cancelMonth_2", cancelMonth_2);
            request.setAttribute("cancelMonth_3", cancelMonth_3);
            request.setAttribute("cancelMonth_4", cancelMonth_4);
            request.setAttribute("cancelMonth_5", cancelMonth_5);
            request.setAttribute("cancelMonth_6", cancelMonth_6);
            request.setAttribute("cancelMonth_7", cancelMonth_7);
            request.setAttribute("cancelMonth_8", cancelMonth_8);
            request.setAttribute("cancelMonth_9", cancelMonth_9);
            request.setAttribute("cancelMonth_10", cancelMonth_10);
            request.setAttribute("cancelMonth_11", cancelMonth_11);
            request.setAttribute("cancelMonth_12", cancelMonth_12);



            request.setAttribute("totalMoney", totalMoney);
            request.setAttribute("expenseMoney", expenseMoney);
            request.setAttribute("revenueMoney", revenueMoney);
            request.setAttribute("listReport", listReport);
            request.setAttribute("hostelName", hostelName);
            request.setAttribute("year", year);
            request.setAttribute("quater", quater);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
        }
    }
}