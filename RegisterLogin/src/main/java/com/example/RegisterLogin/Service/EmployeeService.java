package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;

import com.example.RegisterLogin.response.LoginResponse;

public interface EmployeeService {

	String addEmployee(EmployeeDTO employeeDTO);

	LoginResponse loginEmployee(LoginDTO loginDTO);

}
