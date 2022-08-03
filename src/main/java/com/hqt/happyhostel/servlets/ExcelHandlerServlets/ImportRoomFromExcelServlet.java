package com.hqt.happyhostel.servlets.ExcelHandlerServlets;

import com.hqt.happyhostel.dao.RoomDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.dto.HandlerStatus;
import com.hqt.happyhostel.dto.Room;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ImportRoomFromExcelServlet", value = "/ImportRoomFromExcelServlet")
public class ImportRoomFromExcelServlet extends HttpServlet {

    public static final int COLUMN_INDEX_ROOM_NUMBER                  = 0;
    public static final int COLUMN_INDEX_CAPACITY                     = 1;
    public static final int COLUMN_INDEX_AREA                         = 2;
    public static final int COLUMN_INDEX_ATTIC                        = 3;
    public static final int COLUMN_INDEX_ROOM_STATUS                  = 4;
    public static final int COLUMN_INDEX_TOILET                       = 5;
    public static final int COLUMN_INDEX_TOILET_STATUS                = 6;
    public static final int COLUMN_INDEX_WINDOWS                      = 7;
    public static final int COLUMN_INDEX_WINDOWS_STATUS               = 8;
    public static final int COLUMN_INDEX_DOOR                         = 9;
    public static final int COLUMN_INDEX_DOOR_STATUS                  = 10;
    public static final int COLUMN_INDEX_AIR_CONDITIONER              = 11;
    public static final int COLUMN_INDEX_AIR_CONDITIONER_STATUS       = 12;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "detailHostel?hostelID=";
        try {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("USER");
            int accID = account.getAccId();
            int hostelID = Integer.parseInt(req.getParameter("hostelID"));
            req.setAttribute("hostelID", hostelID);
            RoomDAO roomDAO = new RoomDAO();
            List<HandlerStatus> errors = new ArrayList<>();
            List<HandlerStatus> successes = new ArrayList<>();
            boolean checkAdd = false;

            String fileName = req.getParameter("fileName");
            if(fileName == null || fileName.equals("")){
                req.setAttribute("ERROR", "Vui lòng tải tệp lên để tiến hành thêm phòng!");
                req.getRequestDispatcher("AddRoomPage");
            }

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
                int quantity1 = 0, quantity2 = 0, quantity3 = 0, quantity4 = 0,
                        status1 = 0, status2 = 0, status3 = 0, status4 = 0;
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Get data
                Room room = new Room();

                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    // Set value for hostel object
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case COLUMN_INDEX_ROOM_NUMBER:
                            room.setRoomNumber(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_CAPACITY:
                            room.setCapacity(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_AREA:
                            room.setRoomArea(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_ATTIC:
                            room.setHasAttic(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_ROOM_STATUS:
                            room.setRoomStatus(new BigDecimal((double) cellValue).intValue());
                            break;
                        case COLUMN_INDEX_TOILET:
                            quantity1 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_TOILET_STATUS:
                            status1 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_WINDOWS:
                            quantity2 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_WINDOWS_STATUS:
                            status2 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_DOOR:
                            quantity3 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_DOOR_STATUS:
                            status3 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_AIR_CONDITIONER:
                            quantity4 = new BigDecimal((double) cellValue).intValue();
                            break;
                        case COLUMN_INDEX_AIR_CONDITIONER_STATUS:
                            status4 = new BigDecimal((double) cellValue).intValue();
                            break;
                        default:
                            break;
                    }
                }
                checkAdd = roomDAO.addNewRoom(hostelID, room.getRoomNumber(), room.getCapacity(), room.getRoomArea(), room.getHasAttic(), room.getRoomStatus(),
                        quantity1,status1,quantity2,status2,quantity3,status3,quantity4,status4);
                if(checkAdd){
                    successes.add(HandlerStatus.builder()
                            .status(true)
                            .content("Đã thêm thành công phòng số: "+room.getRoomNumber()).build());

                } else {
                    errors.add(HandlerStatus.builder()
                            .status(false)
                            .content("Đã thêm phòng có số phòng trùng với số phòng đã có! Không thể thêm phòng số: "+room.getRoomNumber()).build());
                }
            }


            workbook.close();
            inputStream.close();
            url += hostelID;
            req.setAttribute("SUCCESS_IMPORT", successes);
            req.setAttribute("ERROR_IMPORT", errors);

        } catch (Exception e){
            log("Error at ImportRoomFromExcelServlet: " + e.toString());
            req.setAttribute("ERROR_IMPORT", "Đã có lỗi xảy ra! Thêm phòng trọ thất bại!");

        }finally {
            req.getRequestDispatcher(url).forward(req,resp);
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
