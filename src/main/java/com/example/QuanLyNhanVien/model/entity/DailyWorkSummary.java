package com.example.QuanLyNhanVien.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "daily_work_summaries",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_staff_date", columnNames = {"staff_id", "work_date"})
    }
)
@Getter
@Setter
@Data
public class DailyWorkSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private staff staff;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(name = "total_hours", nullable = false)
    private Integer totalHours;
}


