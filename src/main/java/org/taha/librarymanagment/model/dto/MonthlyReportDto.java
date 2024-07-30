package org.taha.librarymanagment.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReportDto {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String title;
    private Long bookNumber;
    private List<String> author;
    private List<String> translators;
}
