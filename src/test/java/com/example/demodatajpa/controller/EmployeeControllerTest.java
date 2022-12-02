package com.example.demodatajpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demodatajpa.domain.Employee;
import com.example.demodatajpa.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
	@InjectMocks
	private EmployeeController controller;
	@Mock
	private EmployeeService service;
	
	@Test
	public void testGetAllEmployees() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(service.getAllEmployees()).thenReturn(list);
		List<Employee> emps = controller.getAllEmployees();
		verify(service, times(1)).getAllEmployees();
		assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
	}
	
	@Test
	public void testGetEmpByName() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(service.getEmpByName(any(String.class))).thenReturn(list);
		List<Employee> emps = controller.getEmpByName("Sai");
		verify(service, times(1)).getEmpByName("Sai");
		assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
	}
	
	@Test
	public void testGetEmpByAge() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(service.getEmpByAge(any(Integer.class))).thenReturn(list);
		List<Employee> emps = controller.getEmpByAge(34);
		verify(service, times(1)).getEmpByAge(34);
		assertNotNull(emps.get(0));
		assertEquals(34, emps.get(0).getAge());	
	}
	
	@Test
	public void testGetEmpByNameAndAge() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(service.getEmpByNAmeAndAge(any(String.class), any(Integer.class))).thenReturn(list);
		List<Employee> emps = controller.getEmpByNameAndAge("Sai", 34);
		verify(service, times(1)).getEmpByNAmeAndAge("Sai", 34);
		assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
		assertEquals(34, emps.get(0).getAge());	
	}
	
	@Test
	public void testGetEmployee() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		when(service.getEmployeeById(any(Integer.class))).thenReturn(emp1);
		Employee emp = controller.getEmployee(1);
		verify(service, times(1)).getEmployeeById(1);
		assertNotNull(emp);
		assertEquals("Sai", emp.getName());	
		assertEquals(34, emp.getAge());	
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		doNothing().when(service).deleteEmployeeById(any(Integer.class));
		controller.deleteEmployee(1);
		verify(service, times(1)).deleteEmployeeById(1);
	}
	
	@Test
	public void testAddEmployee() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		doNothing().when(service).saveOrUpdate(any());
		controller.addEmployee(emp1);
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		doNothing().when(service).saveOrUpdate(any());
		controller.updateEmployee(emp1);
	}
	
}
