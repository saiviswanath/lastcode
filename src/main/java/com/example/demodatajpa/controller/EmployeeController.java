package com.example.demodatajpa.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demodatajpa.domain.Employee;
import com.example.demodatajpa.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/emp")
@Tag(name = "emp")
@Slf4j
public class EmployeeController {
	   @Autowired
	   EmployeeService employeeService;

	   @GetMapping("/employees")
	   @Operation(security = @SecurityRequirement(name = "basicAuth"))
	   public List<Employee> getAllEmployees() throws Exception{
		   log.info("Get All employees");
	      return employeeService.getAllEmployees();
	   }
	   @GetMapping("/employeename/{name}")
	   @Operation(security = @SecurityRequirement(name = "basicAuth"))
	   public List<Employee> getEmpByName(@Parameter(name = "name") @PathVariable("name") String name){
	      return employeeService.getEmpByName(name);
	   }
	   
	   @GetMapping("/employeeage/{age}")
	   public List<Employee> getEmpByAge(@PathVariable("age") int age){
	      return employeeService.getEmpByAge(age);
	   }
	   
	   @GetMapping("/employee/{name}/{age}")
	   public List<Employee> getEmpByNameAndAge(@PathVariable("name") String name, @PathVariable("age") int age){
	      return employeeService.getEmpByNAmeAndAge(name, age);
	   }
	   
	   @GetMapping("/employee/{id}")
	   public Employee getEmployee(@PathVariable("id") int id) {
	      return employeeService.getEmployeeById(id);
	   }
	   @DeleteMapping("/employee/{id}")
	   public void deleteEmployee(@PathVariable("id") int id) {
	      employeeService.deleteEmployeeById(id);
	   }
	   @PostMapping("/employee")
	   @Operation(security = @SecurityRequirement(name = "basicAuth"))
	   public void addEmployee(@RequestBody Employee employee) {
		   log.info("Adding emp: {}", employee);
	      employeeService.saveOrUpdate(employee);   
	   }
	   @PutMapping("/employee")
	   public void updateEmployee(@RequestBody Employee employee) {
	      employeeService.saveOrUpdate(employee);
	   }
}
