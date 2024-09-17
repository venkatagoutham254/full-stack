package com.aforo.project.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.aforo.project.model.Organization;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
