package com.example.demodatajpa.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demodatajpa.domain.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {
	@Autowired
    private EmployeeRepository employeeRepository;
    
    @Test
    public void testFindByName() {
    	Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
    	employeeRepository.save(emp1);
    	List<Employee> emps = employeeRepository.findByName("Sai");
    	assertNotNull(emps.get(0));
		assertEquals("Sai", emps.get(0).getName());	
    }
    
    @Test
    public void testFindByAge() {
    	Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
    	employeeRepository.save(emp1);
    	List<Employee> emps = employeeRepository.findByAge(34);
    	assertNotNull(emps.get(0));
		assertEquals(34, emps.get(0).getAge());	
    }
    
    @Test
    public void testFindMyRecords() {
    	Employee emp1 = new Employee(1, "Sai", 34, "sai@test.com");
    	employeeRepository.save(emp1);
    	List<Employee> emps = employeeRepository.findMyRecords("Sai", 34);
    	assertNotNull(emps.get(0));
    	assertEquals("Sai", emps.get(0).getName());	
		assertEquals(34, emps.get(0).getAge());	
    }
}
