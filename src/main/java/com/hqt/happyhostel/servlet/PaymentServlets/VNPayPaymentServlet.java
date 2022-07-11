package com.hqt.happyhostel.servlet.PaymentServlets;

import com.hqt.happyhostel.dao.BillDAO;
import com.hqt.happyhostel.dto.Bill;
import com.hqt.happyhostel.utils.ConfigUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "VNPayPaymentServlet", value = "/VNPayPaymentServlet")
public class VNPayPaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String vnp_TxnRef = req.getParameter("vnp_OrderId");
            Bill bill = new BillDAO().getRenterBillByID(Integer.parseInt(vnp_TxnRef));

            ConfigUtils Config = new ConfigUtils();
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            //Hóa đơn dịch vụ
            String orderType = "250006";
            //ten khu tro + so phong + thang
            String vnp_OrderInfo = bill.getBillTitle();
            String vnp_IpAddr = Config.getIpAddress(req);
            String vnp_TmnCode = Config.vnp_TmnCode;
            long amount = (long)bill.getTotalMoney() * 100;
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            String bank_code = req.getParameter("bankcode");
            if (bank_code != null && !bank_code.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bank_code);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = req.getParameter("language");
            if (locate != null && !locate.isEmpty()) {
                vnp_Params.put("vnp_Locale", locate);
            } else {
                vnp_Params.put("vnp_Locale", "vn");
            }
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Date dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(dt);
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            Calendar cldvnp_ExpireDate = Calendar.getInstance();
            cldvnp_ExpireDate.add(Calendar.SECOND, 30);
            Date vnp_ExpireDateD = cldvnp_ExpireDate.getTime();
            String vnp_ExpireDate = formatter.format(vnp_ExpireDateD);

            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    //hashData.append(fieldValue); //sử dụng và 2.0.0 và 2.0.1 checksum sha256
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())); //sử dụng v2.1.0  check sum sha512
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            //String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret + hashData.toString());
            String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            resp.sendRedirect(paymentUrl);
//        System.out.println(paymentUrl);
//        JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));

        } catch (Exception e) {
            log("Error at VNPayPaymentServlet: " + e.toString());
        }
    }
}
