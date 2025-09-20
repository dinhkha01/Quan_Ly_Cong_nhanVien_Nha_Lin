package com.example.QuanLyNhanVien.controller;

import com.example.QuanLyNhanVien.model.dto.SalaryCalculationDTO;
import com.example.QuanLyNhanVien.model.dto.StaffDTO;
import com.example.QuanLyNhanVien.model.entity.staff;
import com.example.QuanLyNhanVien.service.StaffService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<staff> create(@Valid @RequestBody StaffDTO dto) {
        staff created = staffService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<staff> update(@PathVariable("id") Long id, @Valid @RequestBody StaffDTO dto) {
        return ResponseEntity.ok(staffService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<staff> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(staffService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<staff>> list() {
        return ResponseEntity.ok(staffService.list());
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<SalaryCalculationDTO> calculateSalary(
            @PathVariable("id") Long staffId,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("hourlyRate") BigDecimal hourlyRate) {
        SalaryCalculationDTO result = staffService.calculateSalary(staffId, year, month, hourlyRate);
        return ResponseEntity.ok(result);
    }
}


