package com.example.QuanLyNhanVien.service;

import com.example.QuanLyNhanVien.model.dto.StaffDTO;
import com.example.QuanLyNhanVien.model.entity.staff;
import com.example.QuanLyNhanVien.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

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
}


