package com.aforo.project.controller;

import com.aforo.project.dto.DivisionDTO;
import com.aforo.project.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    // Get All Divisions
    @GetMapping
    public ResponseEntity<List<DivisionDTO>> getAllDivisions() {
        List<DivisionDTO> divisions = divisionService.getAllDivisions();
        return ResponseEntity.ok(divisions);
    }

    // Get a Single Division by ID
    @GetMapping("/{id}")
    public ResponseEntity<DivisionDTO> getDivisionById(@PathVariable Long id) {
        DivisionDTO divisionDTO = divisionService.getDivisionById(id);
        return ResponseEntity.ok(divisionDTO);
    }

    // Create a new Division
    @PostMapping
    public ResponseEntity<DivisionDTO> createDivision(@RequestBody DivisionDTO divisionDTO) {
        DivisionDTO createdDivision = divisionService.createDivision(divisionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDivision);
    }

    // Update an existing Division
    @PutMapping("/{id}")
    public ResponseEntity<DivisionDTO> updateDivision(@PathVariable Long id, @RequestBody DivisionDTO divisionDTO) {
        DivisionDTO updatedDivision = divisionService.updateDivision(id, divisionDTO);
        return ResponseEntity.ok(updatedDivision);
    }

    // Delete a Division by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
        return ResponseEntity.ok("Division deleted successfully");
    }

}

