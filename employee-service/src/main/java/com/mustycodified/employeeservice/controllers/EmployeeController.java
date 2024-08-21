package com.mustycodified.employeeservice.controllers;


import com.mustycodified.employeeservice.dtos.ApiResponseDto;
import com.mustycodified.employeeservice.dtos.EmployeeDto;
import com.mustycodified.employeeservice.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employee Microservice Controller", description = "Employee controller exposes REST API endpoints pertaining to employee service")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employees")

public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "Creates and store new employee", description = "Creates and stores a new employee object in the database. ")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 created")
    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto dto = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get employee REST API", description = " Retrieves an employee object from the database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponseDto> findEmployee(@PathVariable Long employeeId){
        ApiResponseDto dto = employeeService.findEmployeeById(employeeId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
