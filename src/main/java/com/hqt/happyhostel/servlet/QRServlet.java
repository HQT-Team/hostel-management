package com.hqt.happyhostel.servlet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@WebServlet(name = "QRServlet", value = "/QRServlet")
public class QRServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String inviteCode = (String) request.getAttribute("INVITE_CODE");
            String inviteUrl = "RenterRegisterPage?invitecode=1234";

            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = barcodeWriter.encode(inviteUrl, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage qrImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
            request.setAttribute("qrImg", qrImg);
        }catch (Exception e){
            log("Error at QRServlet: " + e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
