package com.example.QuanLyNhanVien.service;

import com.example.QuanLyNhanVien.model.dto.SalaryCalculationDTO;
import com.example.QuanLyNhanVien.model.dto.StaffDTO;
import com.example.QuanLyNhanVien.model.entity.DailyWorkSummary;
import com.example.QuanLyNhanVien.model.entity.staff;
import com.example.QuanLyNhanVien.repository.DailyWorkSummaryRepository;
import com.example.QuanLyNhanVien.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final DailyWorkSummaryRepository dailyWorkSummaryRepository;

    @Transactional
    public staff create(StaffDTO dto) {
        staff s = new staff();
        s.setName(dto.getName());
        return staffRepository.save(s);
    }

    @Transactional
    public staff update(Long id, StaffDTO dto) {
        staff s = staffRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Staff not found"));
        s.setName(dto.getName());
        return staffRepository.save(s);
    }

    @Transactional
    public void delete(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new EntityNotFoundException("Staff not found");
        }
        staffRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public staff get(Long id) {
        return staffRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Staff not found"));
    }

    @Transactional(readOnly = true)
    public List<staff> list() {
        return staffRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SalaryCalculationDTO calculateSalary(Long staffId, int year, int month, BigDecimal hourlyRate) {
        // Kiểm tra nhân viên có tồn tại không
        staff staff = staffRepository.findById(staffId)
            .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        // Lấy tất cả bản ghi công việc trong tháng
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        
        List<DailyWorkSummary> summaries = dailyWorkSummaryRepository
            .findByStaffIdAndWorkDateBetween(staffId, startDate, endDate);

        // Tính tổng giờ công
        int totalWorkHours = summaries.stream()
            .mapToInt(DailyWorkSummary::getTotalHours)
            .sum();

        // Tính lương
        BigDecimal totalSalary = hourlyRate.multiply(BigDecimal.valueOf(totalWorkHours));

        return new SalaryCalculationDTO(
            staffId,
            staff.getName(),
            year,
            month,
            totalWorkHours,
            hourlyRate,
            totalSalary,
            LocalDate.now()
        );
    }
}


