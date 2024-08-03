package org.taha.librarymanagment.service.utill;

import org.taha.librarymanagment.model.dto.MonthlyReportDto;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    void generateMonthlyReportMethod1(List<MonthlyReportDto> data) throws IOException;
    void generateMonthlyReportMethod2(List<MonthlyReportDto> data) throws IOException;
}
