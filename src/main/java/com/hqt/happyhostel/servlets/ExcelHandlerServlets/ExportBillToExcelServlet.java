package com.hqt.happyhostel.servlets.ExcelHandlerServlets;

import com.hqt.happyhostel.dao.*;
import com.hqt.happyhostel.dto.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ExportBillToExcelServlet", value = "/ExportBillToExcelServlet")
public class ExportBillToExcelServlet extends HttpServlet {
    private static final String ERROR = "Renter-payment";
    private static final String SUCCESS = "Renter-payment";

    private static final int COLUMN_INDEX_STT = 2;
    private static final int COLUMN_INDEX_NAME = 3;
    private static final int COLUMN_INDEX_UNIT = 4;
    private static final int COLUMN_INDEX_QUANTITY = 5;
    private static final int COLUMN_INDEX_PRICE = 6;
    private static final int COLUMN_INDEX_TOTAL_MONEY = 7;

    private static CellStyle cellStyleFormatNumber = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = ERROR;
        FileOutputStream fileOut = null;
        String sheetName = "Bill";
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment;filename=hoadon.xlsx");

        try {
            //Get Bill Data
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("USER");
            int accID = account.getAccId();
            String name = account.getAccountInfo().getInformation().getFullname();
            String phone = account.getAccountInfo().getInformation().getPhone();
            String address = account.getAccountInfo().getInformation().getAddress();


            BillDAO billDAO = new BillDAO();
            int billID = (req.getAttribute("billID") != null) ? (int) req.getAttribute("billID") : Integer.parseInt(req.getParameter("billID"));
            BillDetail billDetail = new BillDAO().getBillDetail(billID);

            Bill bill = billDAO.getRenterBillByID(billID);
            Consume consumeStart = null;
            Consume consumeEnd = null;
            if (bill != null) {
                req.setAttribute("BILL", bill);
                //get number electric and water
                int consumeIDStart = billDetail.getConsumeIDStart();
                int consumeIDEnd = billDetail.getConsumeIDEnd();
                consumeStart = new ConsumeDAO().getConsumeByID(consumeIDStart);
                consumeEnd = new ConsumeDAO().getConsumeByID(consumeIDEnd);
                req.setAttribute("CONSUME_START", consumeStart);
                req.setAttribute("CONSUME_END", consumeEnd);
                HandlerStatus handlerStatus = (HandlerStatus) req.getAttribute("RESPONSE_MSG");
                req.setAttribute("RESPONSE_MSG", handlerStatus);
                url = SUCCESS;
            }
            //Get service
            HostelDAO hostelDAO = new HostelDAO();
            Hostel hostel = hostelDAO.getHostelByRenterId(accID);
            int hostelID = hostel.getHostelID();
            String hostelName = hostel.getHostelName();
            String hostelAddress = hostel.getAddress();
            String ward = hostel.getWard().split("-")[1];
            String district = hostel.getDistrict().split("-")[1];
            String city = hostel.getCity().split("-")[1];

            //Get room
            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getRoomInfoByRenterId(accID);
            int roomNumber = room.getRoomNumber();

            int billDetailID = billDetail.getBillDetailID();
            ServiceInfoDAO serviceInfoDao = new ServiceInfoDAO();
            List<ServiceInfo> serviceInfoList = serviceInfoDao.getServiceOfBill(billDetailID, hostelID);
            if (serviceInfoList.size() > 0) {
                req.setAttribute("LIST_SERVICES", serviceInfoList);
                url = SUCCESS;
            }
            //Create Workbook
            Workbook workbook = new XSSFWorkbook();
            //Create name of new sheet
            Sheet sheet = workbook.createSheet(sheetName);
            //Create font
            Font commonFont = sheet.getWorkbook().createFont();
            commonFont.setFontHeightInPoints((short) 12);
            commonFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle commonCellStyle = sheet.getWorkbook().createCellStyle();
            commonCellStyle.setFont(commonFont);
            commonCellStyle.setAlignment(HorizontalAlignment.LEFT);
            //Create row
            int rowIndex = 0;
            Row row = sheet.createRow(rowIndex);
            //Create format date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date getCreatedDate = (Date) dateFormat.parse(bill.getCreatedDate());
            Date getExpiredDate = (Date) dateFormat.parse(bill.getExpiredPaymentDate());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String createdDate = formatter.format(getCreatedDate);
            String expiredDate = formatter.format(getExpiredDate);

            SimpleDateFormat formatterStringDate = new SimpleDateFormat("yyyyMMdd");
            String strCreatedDate = formatterStringDate.format(getCreatedDate);
            //Company info
            //Title
            Font companyTitleFont = sheet.getWorkbook().createFont();
            companyTitleFont.setBold(true);
            companyTitleFont.setFontHeightInPoints((short) 22);
            companyTitleFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle companyCellStyle = sheet.getWorkbook().createCellStyle();
            companyCellStyle.setFont(companyTitleFont);
            companyCellStyle.setAlignment(HorizontalAlignment.CENTER);

            Cell companyCell = row.createCell(2);
            companyCell.setCellStyle(companyCellStyle);
            companyCell.setCellValue("Tập Đoàn Đầu Tư Địa Ốc " + hostelName);
            //company address
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Font companyAddressTitleFont = sheet.getWorkbook().createFont();
            companyAddressTitleFont.setBold(true);
            companyAddressTitleFont.setFontHeightInPoints((short) 11);
            companyAddressTitleFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle companyAddressCellStyle = sheet.getWorkbook().createCellStyle();
            companyAddressCellStyle.setFont(companyAddressTitleFont);
            companyAddressCellStyle.setAlignment(HorizontalAlignment.CENTER);

            companyCell = row.createCell(2);
            companyCell.setCellStyle(companyAddressCellStyle);
            companyCell.setCellValue("Địa chỉ: " + address + ", " + ward + ", " + district + ", " + city);


            //Create Title
            rowIndex++;
            rowIndex++;
            rowIndex++;

            // create CellStyle
            CellStyle cellStyle = createStyleForTitle(sheet);
            // Create row
            row = sheet.createRow(rowIndex);

            // Create cells
            Cell cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue("Hoá Đơn Tháng " + createdDate.split("/")[1]);
            //mã hoá đơn
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Font billIDFont = sheet.getWorkbook().createFont();
            billIDFont.setBold(true);
            billIDFont.setFontHeightInPoints((short) 16);
            billIDFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle billIDCellStyle = sheet.getWorkbook().createCellStyle();
            billIDCellStyle.setFont(billIDFont);
            billIDCellStyle.setAlignment(HorizontalAlignment.CENTER);

            Cell billIDCell = row.createCell(4);
            billIDCell.setCellStyle(billIDCellStyle);
            billIDCell.setCellValue("#" + strCreatedDate + bill.getBillID());

            //Payment status
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Font statusPaymentFont = sheet.getWorkbook().createFont();
            statusPaymentFont.setBold(true);
            statusPaymentFont.setFontHeightInPoints((short) 14);
            if (bill.getStatus() == 1) {
                statusPaymentFont.setColor(IndexedColors.GREEN.getIndex());
            } else {
                statusPaymentFont.setColor(IndexedColors.RED.getIndex());
            }

            CellStyle statusPaymentCellStyle = sheet.getWorkbook().createCellStyle();
            statusPaymentCellStyle.setFont(statusPaymentFont);
            statusPaymentCellStyle.setAlignment(HorizontalAlignment.CENTER);
            Cell statusPaymentCell = row.createCell(4);
            statusPaymentCell.setCellStyle(statusPaymentCellStyle);
            if (bill.getStatus() == 1) {
                statusPaymentCell.setCellValue("Đã thanh toán");

            } else {
                statusPaymentCell.setCellValue("Chưa thanh toán");
            }

            //Renter info
            //Name
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Font infoFont = sheet.getWorkbook().createFont();
            infoFont.setBold(true);
            infoFont.setFontHeightInPoints((short) 15);
            infoFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle infoCellStyle = sheet.getWorkbook().createCellStyle();
            infoCellStyle.setFont(infoFont);
            infoCellStyle.setAlignment(HorizontalAlignment.LEFT);

            Cell infoCell = row.createCell(1);
            infoCell.setCellStyle(infoCellStyle);
            infoCell.setCellValue("Tên khách hàng:");

            infoCell = row.createCell(2);
            infoCell.setCellStyle(commonCellStyle);
            infoCell.setCellValue(name);
            //Room number
            rowIndex++;
            row = sheet.createRow(rowIndex);
            infoCell = row.createCell(1);
            infoCell.setCellStyle(infoCellStyle);
            infoCell.setCellValue("Phòng số:");

            infoCell = row.createCell(2);
            infoCell.setCellStyle(commonCellStyle);
            infoCell.setCellValue(roomNumber);
            //Phone
            rowIndex++;
            row = sheet.createRow(rowIndex);
            infoCell = row.createCell(1);
            infoCell.setCellStyle(infoCellStyle);
            infoCell.setCellValue("Số điện thoại:");

            infoCell = row.createCell(2);
            infoCell.setCellStyle(commonCellStyle);
            infoCell.setCellValue(phone);
            //Address
            rowIndex++;
            row = sheet.createRow(rowIndex);
            infoCell = row.createCell(1);
            infoCell.setCellStyle(infoCellStyle);
            infoCell.setCellValue("Địa chỉ:");

            infoCell = row.createCell(2);
            infoCell.setCellStyle(commonCellStyle);
            infoCell.setCellValue(address);


            //Payment Bill Form
            //number of electric
            rowIndex++;

            row = sheet.createRow(rowIndex);
            Font paymentBillFormTitleFont = sheet.getWorkbook().createFont();
            paymentBillFormTitleFont.setBold(true);
            paymentBillFormTitleFont.setFontHeightInPoints((short) 15);
            paymentBillFormTitleFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle paymentBillFormCellStyle = sheet.getWorkbook().createCellStyle();
            paymentBillFormCellStyle.setFont(paymentBillFormTitleFont);
            paymentBillFormCellStyle.setAlignment(HorizontalAlignment.LEFT);

            Cell electricCell = row.createCell(1);
            electricCell.setCellStyle(paymentBillFormCellStyle);
            electricCell.setCellValue("Số điện:");

            electricCell = row.createCell(2);
            double quantityElectric = consumeEnd.getNumberElectric() - consumeStart.getNumberElectric();
            electricCell.setCellStyle(commonCellStyle);
            electricCell.setCellValue(quantityElectric);

            //old number of electric
            Cell oldElectricCell = row.createCell(4);
            oldElectricCell.setCellStyle(paymentBillFormCellStyle);
            oldElectricCell.setCellValue("Số điện cũ:");

            oldElectricCell = row.createCell(5);
            double oldQuantityElectric = consumeStart.getNumberElectric();
            oldElectricCell.setCellStyle(commonCellStyle);
            oldElectricCell.setCellValue(oldQuantityElectric);
            //new number of electric
            Cell newElectricCell = row.createCell(7);
            newElectricCell.setCellStyle(paymentBillFormCellStyle);
            newElectricCell.setCellValue("Số điện mới:");

            newElectricCell = row.createCell(8);
            double newQuantityElectric = consumeEnd.getNumberElectric();
            newElectricCell.setCellStyle(commonCellStyle);
            newElectricCell.setCellValue(newQuantityElectric);
            //number of water
            rowIndex++;
            row = sheet.createRow(rowIndex);

            Cell waterCell = row.createCell(1);
            waterCell.setCellStyle(paymentBillFormCellStyle);
            waterCell.setCellValue("Số nước:");

            waterCell = row.createCell(2);
            double quantityWater = consumeEnd.getNumberWater() - consumeStart.getNumberWater();
            waterCell.setCellStyle(commonCellStyle);
            waterCell.setCellValue(quantityWater);
            //old number of water
            Cell oldWaterCell = row.createCell(4);
            oldWaterCell.setCellStyle(paymentBillFormCellStyle);
            oldWaterCell.setCellValue("Số nước cũ:");

            oldWaterCell = row.createCell(5);
            double oldQuantityWater = consumeStart.getNumberWater();
            oldWaterCell.setCellStyle(commonCellStyle);
            oldWaterCell.setCellValue(oldQuantityWater);
            //new number of water
            Cell newWaterCell = row.createCell(7);
            newWaterCell.setCellStyle(paymentBillFormCellStyle);
            newWaterCell.setCellValue("Số nước mới:");

            newWaterCell = row.createCell(8);
            double newQuantityWater = consumeEnd.getNumberWater();
            newWaterCell.setCellStyle(commonCellStyle);
            newWaterCell.setCellValue(newQuantityWater);
            //created date
            rowIndex++;
            row = sheet.createRow(rowIndex);

            Cell createdDateCell = row.createCell(1);
            createdDateCell.setCellStyle(paymentBillFormCellStyle);
            createdDateCell.setCellValue("Ngày tạo hoá đơn:");


            createdDateCell = row.createCell(2);
            createdDateCell.setCellStyle(commonCellStyle);
            createdDateCell.setCellValue(createdDate);

            //expired date
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Cell expiredDateCell = row.createCell(1);
            expiredDateCell.setCellStyle(paymentBillFormCellStyle);
            expiredDateCell.setCellValue("Hạn thanh toán:");

            expiredDateCell = row.createCell(2);
            expiredDateCell.setCellStyle(commonCellStyle);
            expiredDateCell.setCellValue(expiredDate);

            // Total Money of Bill
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Cell totalBillCell = row.createCell(1);
            totalBillCell.setCellStyle(paymentBillFormCellStyle);
            totalBillCell.setCellValue("Tổng:");

            CellStyle moneyCellStyle = sheet.getWorkbook().createCellStyle();
            DataFormat df = workbook.createDataFormat();
            moneyCellStyle.setDataFormat(df.getFormat("#,##"));
            moneyCellStyle.setAlignment(HorizontalAlignment.LEFT);
            totalBillCell = row.createCell(2);
            totalBillCell.setCellStyle(moneyCellStyle);
            totalBillCell.setCellValue(bill.getTotalMoney());


            //Create header for Detail Services Bill form
            rowIndex++;
            rowIndex++;
            rowIndex++;

            Font cellServiceTitleFont = sheet.getWorkbook().createFont();
            cellServiceTitleFont.setBold(true);
            cellServiceTitleFont.setFontHeightInPoints((short) 15);

            CellStyle cellServiceTitleStyle = sheet.getWorkbook().createCellStyle();
            cellServiceTitleStyle.setFont(cellServiceTitleFont);
            cellServiceTitleStyle.setBorderTop(BorderStyle.THIN);
            cellServiceTitleStyle.setBorderRight(BorderStyle.THIN);
            cellServiceTitleStyle.setBorderLeft(BorderStyle.THIN);
            cellServiceTitleStyle.setBorderBottom(BorderStyle.THIN);
            cellServiceTitleStyle.setAlignment(HorizontalAlignment.CENTER);

            row = sheet.createRow(rowIndex);
            cell = row.createCell(2);
            cell.setCellValue("Bảng giá dịch vụ");

            cell.setCellStyle(cellServiceTitleStyle);
            cell = row.createCell(3);
            cell.setCellStyle(cellServiceTitleStyle);
            cell = row.createCell(4);
            cell.setCellStyle(cellServiceTitleStyle);
            cell = row.createCell(5);
            cell.setCellStyle(cellServiceTitleStyle);
            cell = row.createCell(6);
            cell.setCellStyle(cellServiceTitleStyle);
            cell = row.createCell(7);
            cell.setCellStyle(cellServiceTitleStyle);

            rowIndex++;

            writeHeader(sheet, rowIndex);

            // Write data to Services Bill Form
            rowIndex++;
            row = sheet.createRow(rowIndex);
            rowIndex = writeBill(bill, consumeStart, consumeEnd, serviceInfoList, row);
            // Write footer of the service bill form
            writeFooter(sheet, rowIndex);

            //Format merge
            sheet.addMergedRegion(CellRangeAddress.valueOf("C1:G1"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("C2:G2"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("D5:F5"));
            sheet.addMergedRegion(CellRangeAddress.valueOf("C19:H19"));

            // Auto resize column width
            int numberOfColumn = sheet.getRow(4).getPhysicalNumberOfCells();
            numberOfColumn = 10;
            autoSizeColumn(sheet, numberOfColumn);

            //output file
            //fileOut = new FileOutputStream(excelFilePath);
            //workbook.write(fileOut);
            workbook.write(resp.getOutputStream());
            fileOut.close();
            workbook.close();
            req.setAttribute("SUCCESS_EXPORT", "Xuất hoá đơn ra Excel thành công!");

        } catch (Exception e) {
            log("Error at ExportBillToExcelServlet: " + e.toString());
        }
    }

    // Write data to Bill
    private static int writeBill(Bill bill, Consume consumeStart, Consume consumeEnd, List<ServiceInfo> serviceInfoList, Row row) throws Exception {
        Workbook workbook = row.getSheet().getWorkbook();
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        Sheet sheet = row.getSheet();

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        CellStyle moneyCellStyle = sheet.getWorkbook().createCellStyle();
        moneyCellStyle.cloneStyleFrom(cellStyle);
        DataFormat df = workbook.createDataFormat();
        moneyCellStyle.setDataFormat(df.getFormat("#,##"));

        int count = 1;
        int rowIndex = row.getRowNum();


        for (ServiceInfo s : serviceInfoList) {
            row = sheet.createRow(rowIndex);
            Cell cell = row.createCell(COLUMN_INDEX_STT);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(count);

            cell = row.createCell(COLUMN_INDEX_NAME);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(s.getServiceName());

            cell = row.createCell(COLUMN_INDEX_UNIT);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(s.getUnit());

            cell = row.createCell(COLUMN_INDEX_QUANTITY);
            cell.setCellStyle(cellStyle);
            double quantityElectric = consumeEnd.getNumberElectric() - consumeStart.getNumberElectric();
            double quantityWater = consumeEnd.getNumberWater() - consumeStart.getNumberWater();
            if (s.getServiceName().equalsIgnoreCase("Điện")) {
                cell.setCellValue(quantityElectric);
            } else if (s.getServiceName().equalsIgnoreCase("Nước")) {
                cell.setCellValue(quantityWater);
            } else {
                cell.setCellValue(1);
            }

            cell = row.createCell(COLUMN_INDEX_PRICE);
            cell.setCellStyle(moneyCellStyle);
            cell.setCellValue(s.getServicePrice());


            cell = row.createCell(COLUMN_INDEX_TOTAL_MONEY);
            cell.setCellStyle(moneyCellStyle);

            if (s.getServiceName().equalsIgnoreCase("Điện")) {
                double totalElectricMoney = quantityElectric * s.getServicePrice();
                cell.setCellValue(totalElectricMoney);
            } else if (s.getServiceName().equalsIgnoreCase("Nước")) {
                double totalWaterMoney = quantityWater * s.getServicePrice();
                cell.setCellValue(totalWaterMoney);
            } else {
                cell.setCellValue(s.getServicePrice());
            }

            count++;
            rowIndex++;
        }
        return rowIndex;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {

        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create Row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_STT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("STT");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên");

        cell = row.createCell(COLUMN_INDEX_UNIT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Đơn vị");

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số lượng");

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Đơn giá");

        cell = row.createCell(COLUMN_INDEX_TOTAL_MONEY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thành tiền");
    }

    // Create CellStyle for title
    private static CellStyle createStyleForTitle(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 18); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        DataFormat df = sheet.getWorkbook().createDataFormat();
        cellStyle.setDataFormat(df.getFormat("#,##"));
        Font sumLabelCellFont = sheet.getWorkbook().createFont();
        sumLabelCellFont.setBold(true);
        sumLabelCellFont.setFontHeightInPoints((short) 13);

        CellStyle sumLabeCellStyle = sheet.getWorkbook().createCellStyle();
        sumLabeCellStyle.setFont(sumLabelCellFont);
        sumLabeCellStyle.setBorderTop(BorderStyle.THIN);
        sumLabeCellStyle.setBorderRight(BorderStyle.THIN);
        sumLabeCellStyle.setBorderLeft(BorderStyle.THIN);
        sumLabeCellStyle.setBorderBottom(BorderStyle.THIN);
        sumLabeCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(6);
        cell.setCellStyle(sumLabeCellStyle);
        cell.setCellValue("Tổng: ");
        cell = row.createCell(7, CellType.FORMULA);
        cell.setCellStyle(cellStyle);
        cell.setCellFormula("SUM(H21:H25)");
    }

    // Auto resize column width
    private static void autoSizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}

