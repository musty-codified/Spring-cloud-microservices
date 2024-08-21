package com.mustycodified.employeeservice.services.impl;

import com.mustycodified.employeeservice.dtos.ApiResponseDto;
import com.mustycodified.employeeservice.dtos.DepartmentDto;
import com.mustycodified.employeeservice.dtos.EmployeeDto;
import com.mustycodified.employeeservice.dtos.OrganizationDto;
import com.mustycodified.employeeservice.entities.Employee;
import com.mustycodified.employeeservice.repositories.EmployeeRepository;
import com.mustycodified.employeeservice.services.ApiClient;
import com.mustycodified.employeeservice.services.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final ModelMapper modelMapper;
    private final WebClient webClient;
    private final ApiClient apiClient;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsById(employeeDto.getId()))
            throw new RuntimeException("Employee already exists");

        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        employee.setOrganizationCode(employeeDto.getOrganizationCode());
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "findDefaultDepartment")
//  @Retry(name = "${spring.application.name}", fallbackMethod = "findDefaultDepartment")
    @Override
    public ApiResponseDto findEmployeeById(Long employeeId) {

        LOGGER.info("Inside findEmployeeById method");

       Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty())
           throw new RuntimeException("Employee not found");
            Employee theEmployee = employee.get();

       DepartmentDto departmentDto = apiClient.findDepartment(theEmployee.getDepartmentCode());
       OrganizationDto organizationDto =  webClient.get()
                            .uri("http://localhost:8083/api/v1/organizations/" + theEmployee.getOrganizationCode())
                             .retrieve()
                             .bodyToMono(OrganizationDto.class)
                             .block();

       EmployeeDto employeeDto = modelMapper.map(theEmployee, EmployeeDto.class);

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto;
    }
         //Fallback method for fault-tolerance
    public ApiResponseDto findDefaultDepartment(Long employeeId, Exception ex) {
        LOGGER.info("Inside findDefaultDepartment method");

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty())
            throw new RuntimeException("Employee not found");
        Employee theEmployee = employee.get();
        EmployeeDto employeeDto = modelMapper.map(theEmployee, EmployeeDto.class);

           DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(employeeId);

        departmentDto.setDepartmentDescription("Human Resource Managerial Department");
           departmentDto.setDepartmentName("Hiring manager");
           departmentDto.setDepartmentCode("HR001");

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeDto);

        return apiResponseDto;
    }
}
