package com.mustycodified.departmentservice.controllers;


import com.mustycodified.departmentservice.dtos.DepartmentDto;
import com.mustycodified.departmentservice.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Department Microservice - Department Controller", description = "Department controller exposes REST API endpoint pertaining to Department service")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")

public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Stores department in database", description = "Creates and stores a department object in the database")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 created")
    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
       DepartmentDto dto = departmentService.createDepartment(departmentDto);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieves department", description = "Retrieves a specific department object from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/{departmentCode}")
    public ResponseEntity<DepartmentDto> findDepartment(@PathVariable String departmentCode){
        DepartmentDto dto = departmentService.findDepartmentByCode(departmentCode);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}
