package org.taha.librarymanagment.model.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReportDto {
    @CsvBindByName(column = "نام", required = true)
    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByName(column = "نام خانوادگی", required = true)
    @CsvBindByPosition(position = 1)
    private String lastName;
    @CsvBindByName(column = "کد ملی", required = true)
    @CsvBindByPosition(position = 2)
    private String nationalCode;
    @CsvBindByName(column = "عنوان کتاب", required = true)
    @CsvBindByPosition(position = 3)
    private String title;
    @CsvBindByName(column = "شماره کتاب", required = true)
    @CsvBindByPosition(position = 4)
    private Long bookNumber;
    @CsvBindByName(column = "نویسنده کتاب", required = true)
    @CsvBindByPosition(position = 5)
    private List<String> author;
    @CsvBindByName(column = "مترجم کتاب", required = true)
    @CsvBindByPosition(position = 6)
    private List<String> translators;
}
