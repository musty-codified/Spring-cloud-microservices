package com.mustycodified.employeeservice.services;

import com.mustycodified.employeeservice.dtos.ApiResponseDto;
import com.mustycodified.employeeservice.dtos.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
    ApiResponseDto findEmployeeById(Long employeeId);

}
