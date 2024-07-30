package org.taha.librarymanagment.service.utill;

import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.MonthlyReportDto;

import java.io.*;
import java.util.List;

@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void generateMonthlyReport(List<MonthlyReportDto> data) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Monthly Report");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = new XSSFFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        String[] headerTitles = {"نام", "نام خانوادگی", "کد ملی", "عنوان کتاب", "شماره کتاب", "نویسنده کتاب", "مترجم کتاب"};
        for (int i = 0; i < headerTitles.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headerTitles[i]);
            headerCell.setCellStyle(headerStyle);
        }

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int rowCount = 1;
        for (MonthlyReportDto report : data) {
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(report.getFirstName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(report.getLastName());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(report.getNationalCode());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(report.getTitle());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(report.getBookNumber() != null ? report.getBookNumber().toString() : "");
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(String.join(", ", report.getAuthor()));
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(String.join(", ", report.getTranslators()));
            cell.setCellStyle(style);
        }

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "monthly_report.xlsx";

        try (FileOutputStream outputStream = new FileOutputStream(fileLocation)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }

    @Override
    public void generateMonthlyReport2(List<MonthlyReportDto> data) throws IOException {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(); FileOutputStream outputStream = new FileOutputStream("monthly_report.xlsx")) {
            Sheet sheet = workbook.createSheet("Monthly Report");
            createHeaderRow(sheet, workbook);
            populateDataRows(sheet, data, workbook);
            workbook.write(outputStream);
        }
    }

    private void createHeaderRow(Sheet sheet, Workbook workbook) {
        Row header = sheet.createRow(0);
        CellStyle headerStyle = createHeaderCellStyle(workbook);

        String[] headerTitles = {"نام", "نام خانوادگی", "کد ملی", "عنوان کتاب", "شماره کتاب", "نویسنده کتاب", "مترجم کتاب"};
        for (int i = 0; i < headerTitles.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headerTitles[i]);
            headerCell.setCellStyle(headerStyle);
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = new XSSFFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);

        return headerStyle;
    }

    private void populateDataRows(Sheet sheet, List<MonthlyReportDto> data, Workbook workbook) {
        CellStyle dataCellStyle = createDataCellStyle(workbook);
        int rowCount = 1;

        for (MonthlyReportDto report : data) {
            Row row = sheet.createRow(rowCount++);
            createDataCell(row, 0, report.getFirstName(), dataCellStyle);
            createDataCell(row, 1, report.getLastName(), dataCellStyle);
            createDataCell(row, 2, report.getNationalCode(), dataCellStyle);
            createDataCell(row, 3, report.getTitle(), dataCellStyle);
            createDataCell(row, 4, report.getBookNumber() != null ? report.getBookNumber().toString() : "", dataCellStyle);
            createDataCell(row, 5, String.join(", ", report.getAuthor()), dataCellStyle);
            createDataCell(row, 6, String.join(", ", report.getTranslators()), dataCellStyle);
        }

        // Track all columns for auto-sizing
        if (sheet instanceof SXSSFSheet) {
            ((SXSSFSheet) sheet).trackAllColumnsForAutoSizing();
        }

        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }

    private void createDataCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
}