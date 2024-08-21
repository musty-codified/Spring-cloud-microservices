package com.mustycodified.departmentservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Department DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    
    @Schema(description = "Department description")
    private String departmentDescription;

    @Schema(description = "Department name")
    private String departmentName;

    @Schema(description = "Department code")
    private String departmentCode;
}
