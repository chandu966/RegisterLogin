package com.example.RegisterLogin.Service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.response.LoginResponse;

@Service
public class EmployeeIMPL implements EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String addEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee(
		
		employeeDTO.getEmployeeid(),
		employeeDTO.getEmployeename(),
		employeeDTO.getEmail(),
		this.passwordEncoder.encode(employeeDTO.getPassword())
		);
		
		employeeRepo.save(employee);	
		return employee.getEmployeename();
	}
  EmployeeDTO employeeDTO;
  
	@Override
	public LoginResponse loginEmployee(LoginDTO loginDTO) {
		
		String msg = "";
		Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
		if(employee1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = employee1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {
				Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
				if(employee.isPresent()) {
					return new LoginResponse("Login Success", true);
				}else {
					return new LoginResponse("Login Failed", false);
				}
			}else {
				return new LoginResponse("Email not exists", false);
			}
		}
		return null;
	}
}


