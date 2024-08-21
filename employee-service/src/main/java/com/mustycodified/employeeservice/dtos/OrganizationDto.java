package com.mustycodified.employeeservice.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Schema(description = "Organization DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;

    @Schema(description = "Organization name")
    private String organizationName;

    @Schema(description = "Organization description")
    private String organizationDescription;

    @Schema(description = "Organization code")

    private String organizationCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }

}
