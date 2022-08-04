package com.hqt.happyhostel.servlets.PaymentServlets;

import com.hqt.happyhostel.dao.AccountDAO;
import com.hqt.happyhostel.dao.BillDAO;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.utils.ConfigUtils;
import com.hqt.happyhostel.utils.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "VNPayIPNServlet", value = "/VNPayIPNServlet")
public class VNPayIPNServlet extends HttpServlet {
    private final String SUCCESS = "renter-bill-payment";
    private final String FAIL = "renter-bill-payment";
    private final String ERROR = "error-page";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int billId = -1;
        long amount = -1;
        String url = ERROR;
//        String orderInfo = null;
//        String responseCode = null;
//        String transactionNo = null;
//        String bankCode = null;
        String vnp_payDate = null;
        Date payDate = null;
        SimpleDateFormat formatter = null;
        String transactionStatus = null;
        BillDAO billDAO = new BillDAO();
        HandlerStatus handlerStatus = null;
        try {
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            if (fields != null) {
                url = FAIL;
                String vnp_SecureHash = request.getParameter("vnp_SecureHash");
                if (fields.containsKey("vnp_SecureHashType")) {
                    fields.remove("vnp_SecureHashType");
                }
                if (fields.containsKey("vnp_SecureHash")) {
                    fields.remove("vnp_SecureHash");
                }
                String signValue = ConfigUtils.hashAllFields(fields);
                billId = Integer.parseInt(request.getParameter("vnp_TxnRef"));
                amount = Long.parseLong(request.getParameter("vnp_Amount"));
//                orderInfo = request.getParameter("vnp_OrderInfo");
//                responseCode = request.getParameter("vnp_ResponseCode");
//                transactionNo = request.getParameter("vnp_TransactionNo");
//                bankCode = request.getParameter("vnp_BankCode");

                vnp_payDate = request.getParameter("vnp_PayDate");
                formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                payDate = formatter.parse(vnp_payDate);
                formatter.applyPattern("yyyy/MM/dd HH:mm:ss");

//                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
//                    transactionStatus = "Success";
//                } else {
//                    transactionStatus = "Failed";
//                }

                if (signValue.equals(vnp_SecureHash)) {
                    //Kiem tra chu ky OK
                    /* Kiem tra trang thai don hang trong DB: checkOrderStatus,
                        - Neu trang thai don hang OK, tien hanh cap nhat vao DB, tra lai cho VNPAY RspCode=00
                        - Neu trang thai don hang (da cap nhat roi) => khong cap nhat vao DB, tra lai cho VNPAY RspCode=02
                     */

                    Bill bill = billDAO.getRenterBillByID(billId);
                    boolean checkOrderId = bill != null; // vnp_TxnRef đơn hàng có tồn tại trong database merchant
                    boolean checkAmount = ((long)bill.getTotalMoney() * 100) == amount; // vnp_Amount is valid  (so sánh số tiền VNPAY request và sô tiền của giao dịch trong database merchant)
                    boolean checkOrderStatus = bill.getStatus() == 0; // PaymnentStatus = 0 (pending)
                    request.setAttribute("billID", billId);
                    if (checkOrderId) {
                        if (checkAmount) {
                            if (checkOrderStatus) {
                                if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                                    //Xu ly thanh toan thanh cong
                                    billDAO.updateBillStatus(bill.getBillID(), 1, formatter.format(payDate), 1);
                                    int ownerId = billDAO.getBillDetail(billId).getAccountHostelOwnerID();
                                    String ownerEmail = new AccountDAO().getAccountInformationById(ownerId).getInformation().getEmail();
                                    if (ownerEmail != null) {
                                        Hostel hostel = new HostelDAO().getHostelByRoomId(bill.getRoomID());
                                        String domain = "http://localhost:8080/HappyHostel/getRoomInvoiceDetail?billID="+billId+"&hostelID="+hostel.getHostelID()+"&roomID="+ bill.getRoomID();
                                        if (new MailUtils().SendMailConfirmPayment(ownerEmail, bill.getRoomID(), hostel.getHostelName() ,bill.getBillTitle(), domain)) {
                                            handlerStatus = HandlerStatus.builder().status(true).content("GD Thanh cong").build();
                                        }
                                    }
                                    url = SUCCESS;

                                } else {
                                    //Xu ly thanh toan khong thanh cong
                                    handlerStatus = HandlerStatus.builder().status(false).content("GD Khong thanh cong").build();
                                }
                            } else {
                                //Don hang nay da duoc cap nhat roi, Merchant khong cap nhat nua (Duplicate callback)
                                handlerStatus = HandlerStatus.builder().status(false).content("Order already confirmed").build();
                            }
                        } else {
                            handlerStatus = HandlerStatus.builder().status(false).content("Invalid Amount").build();
                        }
                    } else {
                        handlerStatus = HandlerStatus.builder().status(true).content("Order not Found").build();
                    }
                }else {
                    handlerStatus = HandlerStatus.builder().status(true).content("Invalid checksum").build();
                }

                request.setAttribute("RESPONSE_MSG", handlerStatus);
            }
        } catch (Exception e) {
//            log("Error at CreateRenterAccountServlet: " + e);
            e.printStackTrace();
        } finally {
            if (ERROR.equalsIgnoreCase(url)) response.sendRedirect(url);
            else request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
