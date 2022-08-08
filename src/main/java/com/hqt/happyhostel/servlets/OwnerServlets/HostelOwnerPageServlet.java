package com.hqt.happyhostel.servlets.OwnerServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "HostelOwnerPageServlet", value = "/HostelOwnerPageServlet")
public class HostelOwnerPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URL = "HostelOwner";
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("USER");
        try {
            // Get six months before current month
            List<List> listSixMonthsAndYearsBeforeCurrentDate = getListSixMonthsAndYearBeforeCurrentDate();

            //Get previous date time before current date
            int previousMonth = (int) listSixMonthsAndYearsBeforeCurrentDate.get(0).get(0); // Month
            int currentYear = (int) listSixMonthsAndYearsBeforeCurrentDate.get(0).get(1); // Year
            YearMonth yearMonthObject = YearMonth.of(currentYear, previousMonth);
            String startDay = "1/" + previousMonth + "/" + currentYear;
            String endDay = yearMonthObject.lengthOfMonth() + "/" + previousMonth + "/" + currentYear;

            // Get data for chart
            List<Integer> listMoneyEachMonth = new ArrayList<>();
            for (int i = 0; i <= 5; i++) {
                listMoneyEachMonth.add(0);
            }

            int[] listReportByReportStatus = {0, 0, 0};
            int averageReport = 0,
                    averageMoneyOfHotel = 0;
            double comparePercentOfTwoMonthAgo = 0;
            double rateReplyReport = 0;
            HostelDAO hostelDAO = new HostelDAO();
            List<Hostel> listHostel = hostelDAO.getHostelByOwnerId(account.getAccId());

            if (listHostel.size() > 0) {
                Random rand = new Random();
                int randomHostelId = rand.nextInt(listHostel.size());
                int hostelId = listHostel.get(randomHostelId).getHostelID(); // Hard code

                listMoneyEachMonth = GetListMoneyEachMonthOfSixMonthAgoByHostelId(hostelId, listSixMonthsAndYearsBeforeCurrentDate);

                int totalMonthOfHostel = GetTotalMonthByHostelId(hostelId);
                int totalMoneyOfHostel = GetTotalMoneyByHostelId(hostelId);
                int totalReportOfHostel = GetTotalReportByHostelId(hostelId);

                listReportByReportStatus = getNumberOfReportsOfEachType(hostelId);

                if (totalMonthOfHostel != 0) {
                    averageReport = totalReportOfHostel / totalMonthOfHostel;
                    averageMoneyOfHotel = totalMoneyOfHostel / totalMonthOfHostel;
                }

                List<Report> reportList = new ReportDAO().getListReportByHostelId(hostelId);
                rateReplyReport = calculateReportReceptionRateFor1DayPeriod(reportList);

                // Calculate percentage increase revenue
                if (listMoneyEachMonth.get(0) != 0 && listMoneyEachMonth.get(1) != 0) {
                    double calculate = (listMoneyEachMonth.get(0) - listMoneyEachMonth.get(1))/((double)listMoneyEachMonth.get(1));
                    comparePercentOfTwoMonthAgo = Math.ceil(calculate * 1000) / 1000;
                } else if (listMoneyEachMonth.get(0) != 0 && listMoneyEachMonth.get(1) == 0) {
                    comparePercentOfTwoMonthAgo = 100;
                } else if (listMoneyEachMonth.get(0) == 0 && listMoneyEachMonth.get(1) != 0) {
                    comparePercentOfTwoMonthAgo = -100;
                }

                request.setAttribute("Hostel", new HostelDAO().getHostelById(hostelId));
            } else {
                session.setAttribute("NO_HAVE_HOSTEL", true);
            }

            request.setAttribute("StartDay", startDay);
            request.setAttribute("EndDay", endDay);

            request.setAttribute("ListMonthAndYear", listSixMonthsAndYearsBeforeCurrentDate);
            request.setAttribute("ListMoneyEachMonth", listMoneyEachMonth);
            request.setAttribute("ComparePercentOfTwoMonthAgo", comparePercentOfTwoMonthAgo);

            request.setAttribute("AverageReport", averageReport);
            request.setAttribute("AverageMoneyOfHotel", averageMoneyOfHotel);

            request.setAttribute("NumberNewReport", listReportByReportStatus[0]);
            request.setAttribute("NumberProcessReport", listReportByReportStatus[1]);
            request.setAttribute("NumberDoneReport", listReportByReportStatus[2]);
            request.setAttribute("RATE_REPLY_REPORT", rateReplyReport);

            request.setAttribute("NumberHostel", listHostel.size());

            List<Room> roomList = new RoomDAO().getListRoomsByHostelOwnerId(account.getAccId());
            List<Room> rentedRooms = new ArrayList<>();
            List<Room> readyRooms = new ArrayList<>();
            List<Room> processRooms = new ArrayList<>();

            for (Room room : roomList) {
                if (room.getRoomStatus() == 1) {
                    readyRooms.add(room);
                } else if (room.getRoomStatus() == -1) {
                    processRooms.add(room);
                } else if (room.getRoomStatus() == 0) {
                    rentedRooms.add(room);
                }
            }

            request.setAttribute("NumberRoom", roomList.size());
            request.setAttribute("NumberRentedRoom", rentedRooms.size());
            request.setAttribute("NumberReadyRoom", readyRooms.size());
            request.setAttribute("NumberProcessRoom", processRooms.size());
            request.setAttribute("NumberNotification", new NotificationDAO().getNotificationByOwnerId(account.getAccId()).size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);
        }
    }

    private double calculateReportReceptionRateFor1DayPeriod(List<Report> reportList) {
        double rate = 0;
        double totalRepliedReports = 0;
        double totalRepliedReportBefore1Day = 0;

        if (reportList.size() == 0) {
            return 100.0;
        }

        List<Report> repliedReportsList = new ArrayList<>();
        for (Report report : reportList) {
            if (report.getStatus() > 0) {
                repliedReportsList.add(report);
            }
        }

        if (repliedReportsList.size() <= 0) {
            return 0.0;
        }

        totalRepliedReports = repliedReportsList.size();

        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
            for (Report report : repliedReportsList) {
                Date replyDate = formatDate.parse(report.getReplyDate());
                Date sendDate = formatDate.parse(report.getSendDate());
                long diff = replyDate.getTime() - sendDate.getTime();
                TimeUnit time = TimeUnit.DAYS;
                long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
                if (diffrence <= 1) {
                    totalRepliedReportBefore1Day++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (totalRepliedReportBefore1Day == 0) {
            return 0.0;
        } else if (totalRepliedReportBefore1Day == totalRepliedReports) {
            return 100.0;
        } else {
            rate = ((totalRepliedReports - totalRepliedReportBefore1Day) / totalRepliedReports) * 100;
        }
        return rate;
    }

    //Input hostel ID return list money of each month of 6 month recent
    private List<Integer> GetListMoneyEachMonthOfSixMonthAgoByHostelId(int hostelID, List<List> listSixMonthsAndYearsBeforeCurrentDate) throws Exception {
        BillDAO billDAO = new BillDAO();
        List<Integer> listMoneyInSixMonth = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            listMoneyInSixMonth.add(0);
        }

        int i = 0;
        for (List entry : listSixMonthsAndYearsBeforeCurrentDate) {
            int month = (int) entry.get(0);
            int year = (int) entry.get(1);

            List<Bill> listBillInMonth = billDAO.GetListBillByHostelIdMonthYear(hostelID, year, month);
            for (Bill item : listBillInMonth) {
                if (item.getStatus() == 1) {
                    listMoneyInSixMonth.set(i, listMoneyInSixMonth.get(i) + item.getTotalMoney());
                }
            }

            i++;
        }

        return listMoneyInSixMonth;
    }

    //Input hostel ID return list six month ago
    private List<List> getListSixMonthsAndYearBeforeCurrentDate() {
        LocalDate currentDate = LocalDate.now();

        int currentMonth = currentDate.getMonth().getValue();
        int currentYear = currentDate.getYear();

        List<List> listMonthYears = new ArrayList<>();
        int i = 6;
        while (i > 0) {
            currentMonth -= 1;

            if (currentMonth == 0) {
                currentMonth = 12;
                currentYear -= 1;
            }
            List<Integer> monthYear = new ArrayList<>();
            monthYear.add(currentMonth);
            monthYear.add(currentYear);
            listMonthYears.add(monthYear);

            i--;
        }

        return listMonthYears;
    }

    //Input hostel id return total money of hostel
    private int GetTotalMoneyByHostelId(int hostelID) throws Exception {
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
    private int GetTotalMonthByHostelId(int hostelID) throws Exception {
        BillDAO billDAO = new BillDAO();
        List<Bill> listBillByHostelID = billDAO.GetBillByHostelId(hostelID);
        List<String> listMonth = new ArrayList<>();

        boolean check;
        for (int i = 0; i < listBillByHostelID.size(); i++) {
            if (listBillByHostelID.get(i).getStatus() == 1) {
                if (listMonth.size() == 0) {
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

    // Get total number report of hostel
    private int GetTotalReportByHostelId(int hostelID) throws SQLException {
        return new ReportDAO().getListReportByHostelId(hostelID).size();
    }

    // Get arr[] number of reports of each type
    private int[] getNumberOfReportsOfEachType(int hostelID) throws SQLException {
        ReportDAO reportDAO = new ReportDAO();
        List<Report> listReport = reportDAO.getListReportByHostelId(hostelID);
        int newReport = 0, processReport = 0, doneReport = 0;
        for (Report item : listReport) {
            if (item.getStatus() == 0)
                newReport++;
            else if (item.getStatus() == 1) {
                processReport++;
            } else {
                doneReport++;
            }
        }
        return new int[]{newReport, processReport, doneReport};
    }

}
