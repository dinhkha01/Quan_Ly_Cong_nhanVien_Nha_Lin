
package com.example.QuanLyNhanVien.service;

import com.example.QuanLyNhanVien.model.dto.WorkLogDTO;
import com.example.QuanLyNhanVien.model.entity.DailyWorkSummary;
import com.example.QuanLyNhanVien.model.entity.WorkLog;
import com.example.QuanLyNhanVien.model.entity.staff;
import com.example.QuanLyNhanVien.repository.DailyWorkSummaryRepository;
import com.example.QuanLyNhanVien.repository.StaffRepository;
import com.example.QuanLyNhanVien.repository.WorkLogRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkLogService {
    private final WorkLogRepository workLogRepository;
    private final StaffRepository staffRepository;
    private final DailyWorkSummaryRepository dailyWorkSummaryRepository;

    @Transactional
    public WorkLog create(WorkLogDTO dto) {
        if (!dto.getEndTime().isAfter(dto.getStartTime())) {
            throw new IllegalArgumentException("endTime must be after startTime");
        }
        staff staff = staffRepository.findById(dto.getStaffId())
            .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        WorkLog log = new WorkLog();
        log.setStaff(staff);
        log.setWorkDate(dto.getWorkDate());
        log.setStartTime(dto.getStartTime());
        log.setEndTime(dto.getEndTime());
        WorkLog saved = workLogRepository.save(log);

        recalculateDailySummary(staff.getId(), dto.getWorkDate());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<WorkLog> listByStaffAndDate(Long staffId, LocalDate date) {
        return workLogRepository.findByStaffIdAndWorkDate(staffId, date);
    }

    private void recalculateDailySummary(Long staffId, LocalDate date) {
        List<WorkLog> logs = workLogRepository.findByStaffIdAndWorkDate(staffId, date);
        int totalMinutes = logs.stream()
            .mapToInt(l -> (int) Duration.between(l.getStartTime(), l.getEndTime()).toMinutes())
            .sum();
        int totalHours = totalMinutes / 60;

        DailyWorkSummary summary = dailyWorkSummaryRepository
            .findByStaffIdAndWorkDate(staffId, date)
            .orElseGet(() -> {
                DailyWorkSummary s = new DailyWorkSummary();
                staff st = new staff();
                st.setId(staffId);
                s.setStaff(st);
                s.setWorkDate(date);
                return s;
            });
        summary.setTotalHours(totalHours);
        dailyWorkSummaryRepository.save(summary);
    }
}

