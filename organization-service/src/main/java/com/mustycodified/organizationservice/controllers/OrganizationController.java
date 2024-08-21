package com.mustycodified.organizationservice.controllers;


import com.mustycodified.organizationservice.dtos.OrganizationDto;
import com.mustycodified.organizationservice.services.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name="Organization Microservice - Organization Controller",
        description = "organization Controller Exposes REST APIs endpoint for Organization Microservice"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Operation(
            summary = "Store Organization REST API",
            description = "Creates and stores an organization object in the database"

    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )

    @PostMapping("/save")
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto){
       OrganizationDto dto = organizationService.saveOrganization(organizationDto);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get Organization REST API",
            description = "Retrieves an organization object from the database"

    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{organizationCode}")
    public ResponseEntity<OrganizationDto> findOrganization(@PathVariable String organizationCode){
        OrganizationDto dto = organizationService.findOrganizationByCode(organizationCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
