package com.example.demodatajpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demodatajpa.domain.Employee;
import com.example.demodatajpa.repos.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@InjectMocks
	private EmployeeService service;
	
	@Mock
	private EmployeeRepository repository;
	   @Mock
	   private CircuitBreaker circuitBreaker;
	   
	   @Mock
	   private TimeLimiter timeLimiter;
	
	@Test
	public void testGetEmployeeById() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		when(repository.findById(any(Integer.class))).thenReturn(Optional.of(emp1));
		Employee emp = service.getEmployeeById(1);
		verify(repository, times(1)).findById(1);
		assertNotNull(emp);
		assertEquals("Sai", emp.getName());	
		assertEquals(34, emp.getAge());	
	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		Supplier<List<Employee>> spy = Mockito.spy(new Supplier<List<Employee>>() {
	        @Override
	        public List<Employee> get() {
	            return (List<Employee>) repository.findAll();
	        }
	    });
	    spy.get();
		when(circuitBreaker.executeSupplier(ArgumentMatchers.<Supplier<List<Employee>>>any())).thenReturn((List<Employee>) list);
		List<Employee> emps = service.getAllEmployees();
		verify(repository, times(1)).findAll();
		assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
	}
	
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		when(repository.save(any())).thenReturn(emp1);
		service.saveOrUpdate(emp1);
	}
	
	@Test
	public void testDeleteEmployeeById() throws Exception {
		doNothing().when(repository).deleteById(any(Integer.class));
		service.deleteEmployeeById(1);
		verify(repository, times(1)).deleteById(1);
	}
	
	@Test
	public void testGetEmpByName() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(repository.findByName(any(String.class))).thenReturn(list);
		List<Employee> emps = service.getEmpByName("Sai");
		verify(repository, times(1)).findByName("Sai");
		assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
	}
	
	@Test
	public void testGetEmpByAge() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(repository.findByAge(any(Integer.class))).thenReturn(list);
		List<Employee> emps = service.getEmpByAge(34);
		verify(repository, times(1)).findByAge(34);
		assertNotNull(emps.get(0));
		assertEquals(34, emps.get(0).getAge());	
	}
	
	@Test
	public void testGetEmpByNAmeAndAge() throws Exception {
		Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
		List<Employee> list = new ArrayList<>();
		list.add(emp1);
		when(repository.findMyRecords(any(String.class), any(Integer.class))).thenReturn(list);
		List<Employee> emps = service.getEmpByNAmeAndAge("Sai", 34);
		verify(repository, times(1)).findMyRecords("Sai", 34);
		assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
		assertEquals(34, emps.get(0).getAge());	
	}
}
