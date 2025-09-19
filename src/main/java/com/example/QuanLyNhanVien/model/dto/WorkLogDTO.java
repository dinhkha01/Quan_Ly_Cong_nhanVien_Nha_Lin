package com.example.QuanLyNhanVien.model.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkLogDTO {
    @NotNull
    private Long staffId;
    @NotNull
    private LocalDate workDate;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
}


