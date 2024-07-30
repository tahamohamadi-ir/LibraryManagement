package org.taha.librarymanagment.service.utill;

import org.taha.librarymanagment.model.dto.MonthlyReportDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ExcelService {
    void generateMonthlyReport(List<MonthlyReportDto> data) throws IOException;
    void generateMonthlyReport2(List<MonthlyReportDto> data) throws IOException;
}
