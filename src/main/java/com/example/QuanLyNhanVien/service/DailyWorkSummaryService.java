package com.example.QuanLyNhanVien.service;

import com.example.QuanLyNhanVien.model.dto.MonthlySummaryDTO;
import com.example.QuanLyNhanVien.model.entity.DailyWorkSummary;
import com.example.QuanLyNhanVien.repository.DailyWorkSummaryRepository;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DailyWorkSummaryService {
    private final DailyWorkSummaryRepository dailyWorkSummaryRepository;

    @Transactional(readOnly = true)
    public MonthlySummaryDTO getMonthly(Long staffId, int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        List<DailyWorkSummary> list = dailyWorkSummaryRepository
            .findByStaffIdAndWorkDateBetween(staffId, start, end);

        Map<String, Integer> daily = new LinkedHashMap<>();
        int total = 0;
        for (DailyWorkSummary s : list) {
            daily.put(s.getWorkDate().toString(), s.getTotalHours());
            total += s.getTotalHours();
        }
        return new MonthlySummaryDTO(staffId, ym, daily, total);
    }
}


