package com.mustycodified.employeeservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Employee DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    @Schema(description = "First name")
    private String firstName;

    @Schema(description = "Last name")
    private String lastName;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Department code")
    private String departmentCode;

    @Schema(description = "Organization code")
    private String organizationCode;
}
