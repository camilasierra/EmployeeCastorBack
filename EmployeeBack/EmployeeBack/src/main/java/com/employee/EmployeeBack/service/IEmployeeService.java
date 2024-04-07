package com.employee.EmployeeBack.service;

import java.util.List;

import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;
import com.employee.EmployeeBack.dto.FilterEmployeeDTO;
import com.employee.EmployeeBack.dto.PageDTO;
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
	
	/**
	 * Listar los empleados por filtro
	 * @param filterEmployeeDTO filtro de entrada
	 * @return PageDTO
	 */
	PageDTO findAllForEmployee(FilterEmployeeDTO filterEmployeeDTO);
	
	/**
	 * Actualiza un empleado 
	 * @param createEmployeeRequest datos del empleado
	 * @return CreateEmployeeResponse
	 */
	CreateEmployeeResponse modifyEmployee(CreateEmployeeRequest createEmployeeRequest);
	
	/**
	 * Obtiene un empleado por id 
	 * @param idEmployee identificador del empleado
	 * @return CreateEmployeeResponse
	 */
	CreateEmployeeResponse getEmployeeById(Long idEmployee);
}
