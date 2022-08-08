package com.hqt.happyhostel.servlets.ExcelHandlerServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "RoomFileUpLoadServlet", value = "/RoomFileUpLoadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class RoomFileUpLoadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "detailHostel?hostelID=";
        try {
            int hostelID = Integer.parseInt(req.getParameter("hostelID"));
            req.setAttribute("hostelID", hostelID);
            Part filePart = req.getPart("file");
            String realPath = req.getServletContext().getRealPath("/excels");
            Path excelPath = Paths.get(realPath);
            String fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();

            if(!Files.exists(Path.of(realPath))){
                Files.createDirectories(Path.of(realPath));
            }
            filePart.write(Paths.get(excelPath.toString(), fileName).toString());
            req.setAttribute("file", fileName);
            req.setAttribute("SUCCESS","Đã tải tệp "+ "\"" + fileName + "\"" + " lên thành công");

            url += hostelID;
        } catch (Exception e){
            log("Error at FileUpLoadServlet: " + e.toString());
            req.setAttribute("ERROR", "Đã có lỗi xảy ra! Tải tệp lên thất bại!");

        } finally {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("error-page");
    }
}
