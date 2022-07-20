package com.hqt.happyhostel.servlet.OwnerServlet;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;
import com.hqt.happyhostel.utils.MailUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CalculateTotalCostServlet", value = "/CalculateTotalCostServlet")
public class CalculateTotalCostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "CalculateTotalCostRoomPage";
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();

            Room room = (Room) session.getAttribute("room");
            int roomId = room.getRoomId();

            AccountDAO accountDAO = new AccountDAO();

            Contract contract = new ContractDAO().getContract(roomId);
            request.setAttribute("contractRoom", contract);

            List<Consume> consumeThisMonth = new ConsumeDAO().getConsumeThisMonth(roomId);
            request.setAttribute("consumeListThisMonth", consumeThisMonth);

            // Check hostel owner update consume or not, remind them, update is not a constraint
            if (consumeThisMonth.get(0).getNumberWater() - consumeThisMonth.get(consumeThisMonth.size() - 1).getNumberWater() == 0 ||
                    consumeThisMonth.get(0).getNumberElectric() - consumeThisMonth.get(consumeThisMonth.size() - 1).getNumberElectric() == 0) {
            }

            String consumeDateStart = consumeThisMonth.get(consumeThisMonth.size() - 1).getUpdateDate().split(" ")[0];
            String consumeDateEnd = consumeThisMonth.get(0).getUpdateDate().split(" ")[0];

            String billTitle = null;
            if (new BillDAO().getBillTitle(roomId, contract.getStartDate()) != null) {
                String billTitleOld = new BillDAO().getBillTitle(roomId, contract.getStartDate());
                String month = billTitleOld.split("/")[0];
                String year = billTitleOld.split("/")[1];
                int yearInteger = Integer.parseInt(year);
                int monthInteger = Integer.parseInt(month) + 1;
                if (monthInteger == 13) {
                    monthInteger = 1;
                    yearInteger += 1;
                }
                billTitle = "" + monthInteger + "/" + yearInteger;
            } else {
                long monthsBetween = ChronoUnit.MONTHS.between(
                        YearMonth.from(LocalDate.parse(consumeDateStart)),
                        YearMonth.from(LocalDate.parse(consumeDateEnd)));

                if (monthsBetween == 0) {
                    String month = consumeDateEnd.split("-")[1];
                    String year = consumeDateEnd.split("-")[0];
                    billTitle = month + "/" + year;
                } else if (monthsBetween == 1) {
                    String month = consumeDateEnd.split("-")[1];
                    String year = consumeDateEnd.split("-")[0];
                    billTitle = month + "/" + year;
                } else if (monthsBetween == 2) {
                    String month = consumeDateEnd.split("-")[1];
                    int monthInteger = Integer.parseInt(month) - 1;
                    String year = consumeDateEnd.split("-")[0];
                    billTitle = monthInteger + "/" + year;
                }
            }

            request.setAttribute("billTitle", billTitle);

            List<ServiceInfo> serviceInfo = new ServiceInfoDAO().getServicesOfHostel(hostelID);
            request.setAttribute("serviceInfo", serviceInfo);

            Account renterAccount = accountDAO.getAccountById(contract.getRenterId());

            request.setAttribute("renterAccountId", renterAccount.getAccId());

            AccountInfo accountRenter = accountDAO.getAccountInformationById(renterAccount.getAccId());
            request.setAttribute("renterName", accountRenter.getInformation().getFullname());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "CalculateTotalCostRoomPage";
        HandlerStatus handlerStatus = null;
        try {
            HttpSession session = request.getSession();
            int hostelID = ((Hostel) session.getAttribute("hostel")).getHostelID();
            int accHostelOwnerID = ((Account) session.getAttribute("USER")).getAccId();

            Room room = (Room) session.getAttribute("room");
            int roomId = room.getRoomId();

            AccountDAO accountDAO = new AccountDAO();

            Contract contract = new ContractDAO().getContract(roomId);
            request.setAttribute("contractRoom", contract);

            List<Consume> consumeThisMonth = new ConsumeDAO().getConsumeThisMonth(roomId);
            request.setAttribute("consumeListThisMonth", consumeThisMonth);

            List<ServiceInfo> serviceInfo = new ServiceInfoDAO().getServicesOfHostel(hostelID);

            Account renterAccount = accountDAO.getAccountById(contract.getRenterId());

            int accountRenterId = renterAccount.getAccId();

            String expiredDateBill = request.getParameter("expiredDate");

            String billTitle = request.getParameter("billTitle");

            double totalCostBill = Double.parseDouble(request.getParameter("totalCost"));
            int totalCost = (int) totalCostBill;
            int consumeIDEnd = consumeThisMonth.get(0).getConsumeID();
            int consumeIDStart = consumeThisMonth.get(consumeThisMonth.size() - 1).getConsumeID();

            ArrayList<Integer> listHostelServiceID = new ArrayList<>();
            for (ServiceInfo service : serviceInfo) {
                listHostelServiceID.add(service.getHostelService());
            }
            int numberLastElectric = consumeThisMonth.get(0).getNumberElectric();
            int numberLastWater = consumeThisMonth.get(0).getNumberWater();

            boolean isInserted = new BillDAO().InsertANewBill(totalCost, billTitle, expiredDateBill, roomId,
                    consumeIDStart, consumeIDEnd, accHostelOwnerID, accountRenterId, numberLastElectric, numberLastWater, listHostelServiceID);

            if (isInserted) {
                url = "roomDetail";
                String renterMail = renterAccount.getAccountInfo().getInformation().getEmail();
                new MailUtils().sendMailNewBill(renterMail, billTitle);
                handlerStatus = HandlerStatus.builder().status(true).content("Tạo hóa đơn thành công").build();
                request.setAttribute("CREATE_BILL_MSG", handlerStatus);
                request.setAttribute("roomID", roomId);
                request.setAttribute("RENTER_ID", accountRenterId);

//                request.setAttribute("IS_SUCCESS", HandlerStatus.builder().status(true));
            } else {
                url = "list-hostels";
//                request.setAttribute("IS_SUCCESS", HandlerStatus.builder().status(false));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
