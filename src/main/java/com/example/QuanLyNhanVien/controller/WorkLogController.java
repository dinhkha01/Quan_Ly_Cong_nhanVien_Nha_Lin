package com.example.QuanLyNhanVien.controller;

import com.example.QuanLyNhanVien.model.dto.WorkLogDTO;
import com.example.QuanLyNhanVien.model.entity.WorkLog;
import com.example.QuanLyNhanVien.service.WorkLogService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/work-logs")
@RequiredArgsConstructor
public class WorkLogController {
    private final WorkLogService workLogService;

    @PostMapping
    public ResponseEntity<WorkLog> create(@Valid @RequestBody WorkLogDTO dto) {
        WorkLog saved = workLogService.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkLog>> listByDate(
        @RequestParam Long staffId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(workLogService.listByStaffAndDate(staffId, date));
    }
}


