package com.aforo.project.serviceImpl;

import com.aforo.project.dto.DivisionDTO;
import com.aforo.project.dto.OrganizationDTO;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.model.Division;
import com.aforo.project.model.Organization;
import com.aforo.project.repository.*;
import com.aforo.project.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public DivisionDTO createDivision(@Valid DivisionDTO divisionDTO) {
        Division division = mapToEntity(divisionDTO);
        Division savedDivision = divisionRepository.save(division);
        return mapToDTO(savedDivision);
    }

    @Override
    public DivisionDTO getDivisionById(Long id) {
        Division division = divisionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));
        return mapToDTO(division);
    }

    @Override
    public DivisionDTO updateDivision(Long id, @Valid DivisionDTO divisionDTO) {
        Division division = divisionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Division not found with id: " + id));
        
        division.setName(divisionDTO.getName());
        Division updatedDivision = divisionRepository.save(division);
        return mapToDTO(updatedDivision);
    }

    @Override
    public void deleteDivision(Long id) {
        if (!divisionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Division not found with id: " + id);
        }
        divisionRepository.deleteById(id);
    }

    @Override
    public List<DivisionDTO> getAllDivisions() {
        List<Division> divisions = divisionRepository.findAll();
        return divisions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Autowired
    private OrganizationRepository organizationRepository;

    private Division mapToEntity(DivisionDTO divisionDTO) {
        Division division = new Division();
        division.setName(divisionDTO.getName());

        // Map organization if it exists in DTO
        if (divisionDTO.getOrganization() != null && divisionDTO.getOrganization().getId() != null) {
            Organization organization = organizationRepository.findById(divisionDTO.getOrganization().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + divisionDTO.getOrganization().getId()));
            division.setOrganization(organization);
        }
        return division;
    }

    private DivisionDTO mapToDTO(Division division) {
        OrganizationDTO organizationDTO = division.getOrganization() != null ?
            new OrganizationDTO(division.getOrganization().getId(), division.getOrganization().getName(), null, null, null, null) : null;

        return new DivisionDTO(
            division.getId(),
            division.getName(),
            division.getCreated_at(),
            division.getUpdated_at(),
            organizationDTO  // Include organization DTO
        );
    }

}
