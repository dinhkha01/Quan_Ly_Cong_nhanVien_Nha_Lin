package com.example.QuanLyNhanVien.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryCalculationDTO {
    private Long staffId;
    private String staffName;
    private int year;
    private int month;
    private int totalWorkHours;
    private BigDecimal hourlyRate;
    private BigDecimal totalSalary;
    private LocalDate calculationDate;
}
