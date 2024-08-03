package org.taha.librarymanagment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taha.librarymanagment.model.dto.MonthlyReportDto;
import org.taha.librarymanagment.service.borrowList.BorrowListService;
import org.taha.librarymanagment.service.utill.ExcelService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@Slf4j
@RestController("/api/v1/excel")
@RequestMapping("/excel")
public class ExcelController {
    private final ExcelService excelService;
    private final BorrowListService borrowListService;
    public ExcelController(ExcelService excelService, BorrowListService borrowListService) {
        this.excelService = excelService;
        this.borrowListService = borrowListService;
    }

    @GetMapping("/generate")
    public void generateExcelFile(@RequestParam Timestamp borrowDateFrom, @RequestParam Timestamp borrowDateThru) throws IOException {
        log.info("Excel file generated");
        List<MonthlyReportDto> data = borrowListService.getMonthlyReport(borrowDateFrom, borrowDateThru);
        excelService.generateMonthlyReportMethod1(data);
    }

    @GetMapping("/generate2")
    public void generateExcelFile2(@RequestParam Timestamp borrowDateFrom, @RequestParam Timestamp borrowDateThru) throws IOException {
        log.info("Excel file generated");
        List<MonthlyReportDto> data = borrowListService.getMonthlyReport(borrowDateFrom, borrowDateThru);
        excelService.generateMonthlyReportMethod2(data);
    }
}