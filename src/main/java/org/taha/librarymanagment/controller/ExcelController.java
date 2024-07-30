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
import java.util.Arrays;
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
    public void generateExcelFile() throws IOException {
        log.info("Excel file generated");
//        List<MonthlyReportDto> data = borrowListService.getMonthlyReport(borrowDateFrom, borrowDateThru);
        List<MonthlyReportDto> data = generateSampleData();

        excelService.generateMonthlyReport(data);
    }
    @GetMapping("/generate2")
    public void generateExcelFile2() throws IOException {
        log.info("Excel file generated");
//        List<MonthlyReportDto> data = borrowListService.getMonthlyReport(borrowDateFrom, borrowDateThru);
        List<MonthlyReportDto> data = generateSampleData();

        excelService.generateMonthlyReport2(data);
    }

    public static List<MonthlyReportDto> generateSampleData() {
        return Arrays.asList(
                MonthlyReportDto.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .nationalCode("123456789")
                        .title("Java Programming")
                        .bookNumber(1L)
                        .author(Arrays.asList("John Doe"))
                        .translators(Arrays.asList("Jane Smith"))
                        .build(),
                MonthlyReportDto.builder()
                        .firstName("Jane")
                        .lastName("Smith")
                        .nationalCode("987654321")
                        .title("Spring Boot in Action")
                        .bookNumber(2L)
                        .author(Arrays.asList("Jane Smith"))
                        .translators(Arrays.asList("John Doe"))
                        .build(),
                MonthlyReportDto.builder()
                        .firstName("Alice")
                        .lastName("Johnson")
                        .nationalCode("456789123")
                        .title("Effective Java")
                        .bookNumber(3L)
                        .author(Arrays.asList("Joshua Bloch"))
                        .translators(Arrays.asList("Alice Johnson"))
                        .build(),
                MonthlyReportDto.builder()
                        .firstName("Bob")
                        .lastName("Brown")
                        .nationalCode("789123456")
                        .title("Clean Code")
                        .bookNumber(4L)
                        .author(Arrays.asList("Robert C. Martin"))
                        .translators(Arrays.asList("Bob Brown"))
                        .build(),
                MonthlyReportDto.builder()
                        .firstName("Charlie")
                        .lastName("Davis")
                        .nationalCode("321654987")
                        .title("Design Patterns")
                        .bookNumber(5L)
                        .author(Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"))
                        .translators(Arrays.asList("Charlie Davis"))
                        .build()
        );
    }
}
