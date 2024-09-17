package com.aforo.project.serviceImpl;

import com.aforo.project.dto.DivisionDTO;
import com.aforo.project.dto.OrganizationDTO;
import com.aforo.project.dto.ProducerDTO;
import com.aforo.project.exception.InvalidDataException;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.model.Division;
import com.aforo.project.model.Organization;
import com.aforo.project.model.Producer;
import com.aforo.project.repository.*;
import com.aforo.project.repository.OrganizationRepository;
import com.aforo.project.service.OrganizationService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private ProducerRepository producerRepository;

    @Transactional
    @Override
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = mapToEntity(organizationDTO);

        try {
            Organization savedOrganization = organizationRepository.save(organization);
            return mapToDTO(savedOrganization);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Invalid data provided.  Organization Name Alreday Exists.", e);
        }
    }

    @Transactional
    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
        return mapToDTO(organization);
    }

    @Transactional
    @Override
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));

        organization.setName(organizationDTO.getName());

        // Updating the Producer - ensure the Producer exists
        if (organizationDTO.getProducer() != null) {
            Producer producer = producerRepository.findById(organizationDTO.getProducer().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + organizationDTO.getProducer().getId()));
            organization.setProducer(producer);
        }

        try {
            Organization updatedOrganization = organizationRepository.save(organization);
            return mapToDTO(updatedOrganization);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Error updating Organization. Check Producer and Division details.", e);
        }
    }

    @Transactional
    @Override
    public Organization deleteOrganization(Long id) {
        try {
            Organization organization = organizationRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
            organizationRepository.deleteById(id);
            return organization;
        } catch (DataIntegrityViolationException e) {
            throw new InvalidDataException("Unable to delete Organization. Integrity constraint violation.", e);
        }
    }


    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private OrganizationDTO mapToDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setCreated_at(organization.getCreated_at());
        organizationDTO.setUpdated_at(organization.getUpdated_at());

        if (organization.getProducer() != null) {
            organizationDTO.setProducer(mapToDTO(organization.getProducer()));
        }

        if (organization.getDivisions() != null) {
            organizationDTO.setDivisions(organization.getDivisions().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        }

        return organizationDTO;
    }

    private Organization mapToEntity(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setName(organizationDTO.getName());

        // Ensure the Producer is mapped correctly
        if (organizationDTO.getProducer() != null) {
            Producer producer = producerRepository.findById(organizationDTO.getProducer().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + organizationDTO.getProducer().getId()));
            organization.setProducer(producer);
        }

        return organization;
    }

    private ProducerDTO mapToDTO(Producer producer) {
        // Logic to map Producer to ProducerDTO
        return new ProducerDTO();
    }

    private DivisionDTO mapToDTO(Division division) {
        // Logic to map Division to DivisionDTO
        return new DivisionDTO();
    }
}
