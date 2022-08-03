package com.hqt.happyhostel.servlets.ExcelHandlerServlets;

import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.Hostel;
import com.hqt.happyhostel.dto.HostelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ImportHostelFromExcelServlet", value = "/ImportHostelFromExcelServlet")
public class ImportHostelFromExcelServlet extends HttpServlet {
    public static final int COLUMN_INDEX_NAME = 0;
    public static final int COLUMN_INDEX_ADDRESS = 1;
    public static final int COLUMN_INDEX_WARD = 2;
    public static final int COLUMN_INDEX_DISTRICT = 3;
    public static final int COLUMN_INDEX_CITY = 4;
    public static final int COLUMN_INDEX_ELECTRIC = 5;
    public static final int COLUMN_INDEX_WATER = 6;
    public static final int COLUMN_INDEX_INTERNET = 7;
    public static final int COLUMN_INDEX_MANAGEMENT = 8;
    public static final int COLUMN_INDEX_VEHICLE = 9;
    public static final int COLUMN_INDEX_SANITARY = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("USER");
            int accID = account.getAccId();
            HostelDAO hostelDAO = new HostelDAO();

            String fileName = req.getParameter("fileName");
            String excelFilePath = req.getServletContext().getRealPath("/excels" + "/" + fileName);

            // Get file
            InputStream inputStream = new FileInputStream(new File(excelFilePath));
            // Get workbook
            Workbook workbook = getWorkbook(inputStream, excelFilePath);
            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Get data
                LocalDate dateObj = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String validDate = dateObj.format(formatter);
                List<HostelService> hostelServiceList = new ArrayList<>();
                Hostel hostel = new Hostel();

                while (cellIterator.hasNext()) {
                    hostel.setHostelOwnerAccountID(accID);
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for hostel object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_NAME:
                            hostel.setHostelName((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_ADDRESS:
                            hostel.setAddress((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_WARD:
                            hostel.setWard((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DISTRICT:
                            hostel.setDistrict((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_CITY:
                            hostel.setCity((String) getCellValue(cell));
                            break;
                        case COLUMN_INDEX_ELECTRIC:
                            int electricPrice = BigDecimal.valueOf((double) cellValue).intValue();
                            hostelServiceList.add(HostelService.builder().serviceID(1).validDate(validDate).servicePrice(electricPrice).build());
                            break;
                        case COLUMN_INDEX_WATER:
                            int waterPrice = BigDecimal.valueOf((double) cellValue).intValue();
                            hostelServiceList.add(HostelService.builder().serviceID(2).validDate(validDate).servicePrice(waterPrice).build());
                            break;
                        case COLUMN_INDEX_INTERNET:
                            int internetPrice = new BigDecimal((double) cellValue).intValue();
                            hostelServiceList.add(HostelService.builder().serviceID(3).validDate(validDate).servicePrice(internetPrice).build());
                            break;
                        case COLUMN_INDEX_MANAGEMENT:
                            int managementPrice = new BigDecimal((double) cellValue).intValue();
                            hostelServiceList.add(HostelService.builder().serviceID(4).validDate(validDate).servicePrice(managementPrice).build());
                            break;
                        case COLUMN_INDEX_VEHICLE:
                            int vehicleryPrice = new BigDecimal((double) cellValue).intValue();
                            hostelServiceList.add(HostelService.builder().serviceID(7).validDate(validDate).servicePrice(vehicleryPrice).build());
                            break;
                        case COLUMN_INDEX_SANITARY:
                            int sanitaryPrice = new BigDecimal((double) cellValue).intValue();
                            hostelServiceList.add(HostelService.builder().serviceID(6).validDate(validDate).servicePrice(sanitaryPrice).build());
                            break;
                    }
                }

                int hostelId = hostelDAO.addHostel(hostel, hostelServiceList);
                if(hostelId > 0){
                    req.setAttribute("SUCCESS_IMPORT", "Thêm khu trọ thành công");
                }
                workbook.close();
                inputStream.close();
            }

        } catch (Exception e) {
            log("Error at ImportHostelFromExcelServlet: " + e.toString());
            req.setAttribute("ERROR_IMPORT", "Đã có lỗi xảy ra thêm khu trọ thất bại");

        } finally {
            req.getRequestDispatcher("list-hostels").forward(req, resp);
        }
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
