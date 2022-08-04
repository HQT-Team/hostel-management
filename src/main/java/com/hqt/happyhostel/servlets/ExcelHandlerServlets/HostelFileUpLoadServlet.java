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

@WebServlet(name = "HostelFileUpLoadServlet", value = "/HostelFileUpLoadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class HostelFileUpLoadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String realPath = req.getServletContext().getRealPath("/excels");
            Path excelPath = Paths.get(realPath);

            if (!Files.exists(excelPath)) {
                Files.createDirectories(excelPath);
            }

            Part filePart = req.getPart("file");
            String fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
            filePart.write(Paths.get(excelPath.toString(), fileName).toString());

            req.setAttribute("file", fileName);
            req.setAttribute("SUCCESS", "Đã tải tệp " + "\"" + fileName + "\"" + " lên thành công");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("ERROR", "Đã có lỗi xảy ra! Tải tệp lên thất bại!");
        } finally {
            req.getRequestDispatcher("list-hostels").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("denied");
    }
}
