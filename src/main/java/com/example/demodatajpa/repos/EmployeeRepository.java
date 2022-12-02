package com.example.demodatajpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demodatajpa.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	public List<Employee> findByName(String name);
	public List<Employee> findByAge(int age);
	
	@Query("select e from Employee e where e.name=:name and e.age=:age")
	public List<Employee> findMyRecords(@Param("name") String name, @Param("age") int age);
}
