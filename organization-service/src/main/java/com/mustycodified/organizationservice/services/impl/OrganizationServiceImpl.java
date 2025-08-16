package com.mustycodified.organizationservice.services.impl;

import com.mustycodified.organizationservice.dtos.OrganizationDto;
import com.mustycodified.organizationservice.entities.Organization;
import com.mustycodified.organizationservice.repositories.OrganizationRepository;
import com.mustycodified.organizationservice.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final ModelMapper mapper;


    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        Organization storedOrganization = organizationRepository.save(organization);

     return mapper.map(storedOrganization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto findOrganizationByCode(String organizationCode) {
      Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
     return mapper.map(organization, OrganizationDto.class);
    }
}
