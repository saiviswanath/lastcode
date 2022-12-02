package com.example.demodatajpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demodatajpa.domain.Employee;
import com.example.demodatajpa.repos.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;

@Service
public class EmployeeService {
	   @Autowired
	   EmployeeRepository repository;
	   
	   @Autowired
	   private CircuitBreaker circuitBreaker;
	   
	   @Autowired
	   private TimeLimiter timeLimiter;

	   public Employee getEmployeeById(int id) {
	      return repository.findById(id).get();
	   }
	   public List<Employee> getAllEmployees() throws Exception{
		   
	     List<Employee> employees = new ArrayList<Employee>();
	      
	     List<Employee> empresult = 
	    		  (List<Employee>) applyCB(() -> repository.findAll());
	      empresult.forEach(employee -> employees.add(employee));
	      
	      return employees;
	   }
	   public void saveOrUpdate(Employee employee) {
	      repository.save(employee);
	   }
	   public void deleteEmployeeById(int id) {
	      repository.deleteById(id);
	   }
	   
	   public List<Employee> getEmpByName(String name){
		    return repository.findByName(name);
	   }
	   
	   public List<Employee> getEmpByAge(int age){
		    return repository.findByAge(age);
	   }
	   
	   public List<Employee> getEmpByNAmeAndAge(String name, int age){
		    return repository.findMyRecords(name, age);
	   }
	   
	   private <T> Supplier<T> getTLSupplier(Supplier<T> supplier) throws Exception {
		   return () -> {
			try {
				return timeLimiter.executeFutureSupplier(() -> CompletableFuture.supplyAsync(supplier));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	   }
	   
	   private <T> T applyCB(Supplier<T> supplier) throws Exception {
		   return circuitBreaker.executeSupplier(getTLSupplier(supplier));
	   }
		   
}
