package com.example.RegisterLogin.EmployeeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.response.LoginResponse;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")

public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping(path ="/register")
	public String saveEmployee(@RequestBody EmployeeDTO employeeDTO)
	{
		String id = employeeService.addEmployee(employeeDTO);
		return id;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?>loginEmployee(@RequestBody LoginDTO loginDTO)
	{
		LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
		return ResponseEntity.ok(loginResponse);
	}
}




