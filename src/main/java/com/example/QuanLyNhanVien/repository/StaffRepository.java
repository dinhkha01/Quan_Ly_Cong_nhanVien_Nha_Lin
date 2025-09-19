package com.example.QuanLyNhanVien.repository;

import com.example.QuanLyNhanVien.model.entity.staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<staff, Long> {
}


