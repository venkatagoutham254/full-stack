package com.aforo.project.service;
import com.aforo.project.dto.OrganizationDTO;
import com.aforo.project.model.Organization;

import java.util.List;

public interface OrganizationService {
    OrganizationDTO createOrganization(OrganizationDTO organizationDTO);
    OrganizationDTO getOrganizationById(Long id);
    OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO);
    Organization deleteOrganization(Long id);
    List<OrganizationDTO> getAllOrganizations();
	
}
