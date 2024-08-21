package com.mustycodified.organizationservice.services;

import com.mustycodified.organizationservice.dtos.OrganizationDto;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto findOrganizationByCode(String organizationCode);

}
