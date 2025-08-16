package com.mustycodified.departmentservice.services.impl;

import com.mustycodified.departmentservice.dtos.DepartmentDto;
import com.mustycodified.departmentservice.entities.Department;
import com.mustycodified.departmentservice.repositories.DepartmentRepository;
import com.mustycodified.departmentservice.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsById(departmentDto.getId()))
            throw new RuntimeException("Department already exists");

        Department department = new Department();
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setId(department.getId());

       Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto findDepartmentByCode(String departmentCode) {
        if(departmentRepository.findByDepartmentCode(departmentCode) == null)
             throw new RuntimeException("Department not found");
       Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return modelMapper.map(department, DepartmentDto.class);
    }
}
