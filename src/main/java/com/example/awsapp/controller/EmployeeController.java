package com.example.awsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.awsapp.model.EmployeeResponse;
import com.example.awsapp.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/getEmployeeList")
	public List<EmployeeResponse> getEmployeeList(){
		return empService.getEmployeeList();
		
	}
	

}
