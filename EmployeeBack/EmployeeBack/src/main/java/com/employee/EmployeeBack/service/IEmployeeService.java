package com.employee.EmployeeBack.service;

import java.util.List;

import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;
import com.employee.EmployeeBack.dto.PositionDto;
import com.employee.EmployeeBack.dto.TypeDocumentDto;

public interface IEmployeeService  {
	
	/**
	 * Crear un empleado
	 * @param CreateEmployeeRequest crearEmployeeRequest, datos del empleado
	 * @return CreateEmployeeResponse
	 */
	CreateEmployeeResponse createEmployee(CreateEmployeeRequest crearEmployeeRequest);
	
	/**
	 * Listar los tipos de documento existentes
	 * @param 
	 * @return List<TypeDocumentDto>
	 */
	List<TypeDocumentDto> listTypeDocument();
	
	/**
	 * Listar los cargos existentes
	 * @param 
	 * @return List<PositionDto>
	 */
	List<PositionDto> listPosition();
}
