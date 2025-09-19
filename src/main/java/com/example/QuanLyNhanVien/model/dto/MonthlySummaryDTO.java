package com.example.QuanLyNhanVien.model.dto;

import java.time.YearMonth;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlySummaryDTO {
    private Long staffId;
    private YearMonth month;
    // key: yyyy-MM-dd, value: totalHours of that day
    private Map<String, Integer> dailyHours;
    private Integer totalHours;
}


