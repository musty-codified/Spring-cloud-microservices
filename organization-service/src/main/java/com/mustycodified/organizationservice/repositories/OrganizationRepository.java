package com.mustycodified.organizationservice.repositories;

import com.mustycodified.organizationservice.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
       Organization findByOrganizationCode(String organizationCode);
}
