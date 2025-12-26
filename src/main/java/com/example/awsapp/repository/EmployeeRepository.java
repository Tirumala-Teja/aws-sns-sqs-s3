package com.example.awsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.awsapp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value = "SELECT * FROM EMP_TBL", nativeQuery = true)
	List<Employee> findAllNative();
}