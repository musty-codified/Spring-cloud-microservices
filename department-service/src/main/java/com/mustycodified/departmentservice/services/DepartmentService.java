package com.mustycodified.departmentservice.services;

import com.mustycodified.departmentservice.dtos.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto findDepartmentByCode(String departmentCode);
}
