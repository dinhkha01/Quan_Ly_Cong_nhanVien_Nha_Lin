package com.example.QuanLyNhanVien.repository;

import com.example.QuanLyNhanVien.model.entity.WorkLog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    List<WorkLog> findByStaffIdAndWorkDate(Long staffId, LocalDate workDate);
    List<WorkLog> findByStaffIdAndWorkDateBetween(Long staffId, LocalDate start, LocalDate end);
}


