package com.aforo.project.controller;

import com.aforo.project.dto.OrganizationDTO;
import com.aforo.project.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    // Create a new Organization
    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        OrganizationDTO createdOrganization = organizationService.createOrganization(organizationDTO);
        return ResponseEntity.ok(createdOrganization);
    }

    // Get a single Organization by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id) {
        OrganizationDTO organizationDTO = organizationService.getOrganizationById(id);
        return ResponseEntity.ok(organizationDTO);
    }

    // Update an existing Organization
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDTO organizationDTO) {
        OrganizationDTO updatedOrganization = organizationService.updateOrganization(id, organizationDTO);
        return ResponseEntity.ok(updatedOrganization);
    }

    // Delete an Organization by ID
 // Delete an Organization by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable Long id) {
        // Delete the organization (will throw an exception if not found)
        organizationService.deleteOrganization(id);
        
        // Return a success message
        return ResponseEntity.ok("Organization deleted successfully");
    }


    // Get All Organizations
    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        List<OrganizationDTO> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }
}
