package com.mustycodified.employeeservice.services;


import com.mustycodified.employeeservice.dtos.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



//Out -of -the box load balancing
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {
    @RequestMapping("/api/v1/departments/{departmentCode}")
    DepartmentDto findDepartment(@PathVariable("departmentCode") String departmentCode);
}

//echo "# Spring-cloud-microservices" >> README.md
//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/musty-codified/Spring-cloud-microservices.git
//git push -u origin main