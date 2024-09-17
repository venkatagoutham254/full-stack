package com.aforo.project.service;

import com.aforo.project.dto.DivisionDTO;
import java.util.List;


public interface DivisionService {
    DivisionDTO createDivision(DivisionDTO divisionDTO);
    DivisionDTO getDivisionById(Long id);
    DivisionDTO updateDivision(Long id, DivisionDTO divisionDTO);
    void deleteDivision(Long id);
    List<DivisionDTO> getAllDivisions();
}
