package com.hqt.happyhostel.servlet.RenterServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dao.RoommateInfoDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Information;
import com.hqt.happyhostel.dto.RoommateInfo;
import com.hqt.happyhostel.dto.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRenterRoommateServlet", value = "/AddRenterRoommateServlet")
public class AddRenterRoommateServlet extends HttpServlet {
    private static final String SUCCESS = "Renter-add-roommate";
    private static final String ERROR = "Renter-add-roommate";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        AccountDAO accountDAO = new AccountDAO();
        RoommateInfoDAO roommateInfoDAO = new RoommateInfoDAO();

        HttpSession session = request.getSession();
        Account acc = (Account)session.getAttribute("USER");
        try {
            int accId = acc.getAccId();
            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getHostelRoomInforByRenterId(accId);
            int roomCapacity = room.getCapacity();
            String currentRenterAccountUsername = acc.getUsername();
            //Update Roommate Information
            String fullName = request.getParameter("full-name");
            String dateOfBirth = request.getParameter("dob");
            int gender = Integer.parseInt(request.getParameter("gender"));
            String phoneNumber = request.getParameter("phone-number");
            String email = request.getParameter("email");
            String cccd = request.getParameter("cccd");
            String address = request.getParameter("address");
            String parentName = request.getParameter("parent-name");
            String parentPhone = request.getParameter("parent-phone");

            // Get AccountID of Current Renter by currentRenterAccountUsername
            int accountId = accountDAO.getAccountIdByUserName(currentRenterAccountUsername);

            // Check get accountId is true or false
            if (accountId >= 0)  {
                List<RoommateInfo> listCurrentRoommate = roommateInfoDAO.getListRoommatesOfAnAccount(accountId);

                if (listCurrentRoommate.size() < roomCapacity) {
                    Information information = Information.builder()
                            .fullname(fullName)
                            .birthday(dateOfBirth)
                            .sex(gender)
                            .email(email)
                            .phone(phoneNumber)
                            .address(address)
                            .cccd(cccd).build();
                    RoommateInfo roommateInfo = RoommateInfo.builder()
                            .information(information)
                            .parentName(parentName)
                            .parentPhone(parentPhone).build();

                    // Insert into RoommateInformation table
                    boolean check = roommateInfoDAO.AddRoommateInformationOfAnAccount(roommateInfo, accountId);

                    if (check) {
                        request.setAttribute("SUCCESS", "Thêm thành viên mới vào phòng thành công");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Đã có lỗi xảy ra! Vui lòng thử lại sau");
                    }
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Phòng đã đạt số lượng thành viên tối đa! Thêm thành viên thất bại!");
                }
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Đã có lỗi xảy ra! Vui lòng thử lại sau");
            }
        } catch (Exception e) {
            log("Error at AddRoommateServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
