package com.example.awsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.awsapp.entity.Employee;
import com.example.awsapp.model.EmployeeResponse;
import com.example.awsapp.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
	@Autowired
	 EmployeeRepository empRepo;
	
	public List<EmployeeResponse> getEmployeeList() {
		List<Employee> empList = empRepo.findAll();
		 List<EmployeeResponse> response = empList.stream()
		            .map(entity -> new EmployeeResponse(
		                    entity.getId(),
		                    entity.getName(),
		                    entity.getDept()
		            )) .toList();
		return response;
	}

}
