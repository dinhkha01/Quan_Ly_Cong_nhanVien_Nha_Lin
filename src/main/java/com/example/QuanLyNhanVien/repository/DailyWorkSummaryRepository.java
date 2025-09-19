package com.example.QuanLyNhanVien.repository;

import com.example.QuanLyNhanVien.model.entity.DailyWorkSummary;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyWorkSummaryRepository extends JpaRepository<DailyWorkSummary, Long> {
    Optional<DailyWorkSummary> findByStaffIdAndWorkDate(Long staffId, LocalDate workDate);
    List<DailyWorkSummary> findByStaffIdAndWorkDateBetween(Long staffId, LocalDate start, LocalDate end);
}


