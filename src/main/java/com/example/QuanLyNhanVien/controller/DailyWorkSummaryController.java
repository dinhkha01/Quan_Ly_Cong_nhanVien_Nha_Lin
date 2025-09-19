package com.example.QuanLyNhanVien.controller;

import com.example.QuanLyNhanVien.model.dto.MonthlySummaryDTO;
import com.example.QuanLyNhanVien.service.DailyWorkSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily-summaries")
@RequiredArgsConstructor
public class DailyWorkSummaryController {
    private final DailyWorkSummaryService dailyWorkSummaryService;

    @GetMapping("/monthly")
    public ResponseEntity<MonthlySummaryDTO> getMonthly(
        @RequestParam Long staffId,
        @RequestParam int year,
        @RequestParam int month
    ) {
        return ResponseEntity.ok(dailyWorkSummaryService.getMonthly(staffId, year, month));
    }
}


