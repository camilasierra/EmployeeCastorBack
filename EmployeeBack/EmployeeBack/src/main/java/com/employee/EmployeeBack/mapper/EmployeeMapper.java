package com.employee.EmployeeBack.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.employee.EmployeeBack.domain.Employee;
import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;
import com.employee.EmployeeBack.dto.EmployeeDto;

@Mapper
public interface EmployeeMapper {
	
	@Mapping(source = "idPosition", target = "idPosition.idPosition")
	@Mapping(source = "idTypeDocument", target = "idTypeDocument.idTypeDocument")
	public Employee toEntityReq(CreateEmployeeRequest createEmployeeRequest);
	
	@Mapping(source = "idPosition", target = "idPosition.idPosition")
	@Mapping(source = "idTypeDocument", target = "idTypeDocument.idTypeDocument")
	public Employee toEntity(EmployeeDto employeeDto);
	
	@Mapping(source = "idPosition.idPosition", target = "idPosition")
	@Mapping(source = "idTypeDocument.idTypeDocument", target = "idTypeDocument")
	@Mapping(source = "idTypeDocument.codeTypeDocument", target = "codeTypeDocument")
	@Mapping(source = "idPosition.name", target = "namePosition")
	public EmployeeDto toDto(Employee employee);
	
	@Mapping(source = "idPosition.idPosition", target = "idPosition")
	@Mapping(source = "idTypeDocument.idTypeDocument", target = "idTypeDocument")
	public CreateEmployeeRequest toDtoReq(Employee employee);
	
	
	@Mapping(source = "idPosition.idPosition", target = "idPosition")
	@Mapping(source = "idTypeDocument.idTypeDocument", target = "idTypeDocument")
	public CreateEmployeeResponse toEntityResponse(Employee employee);

}
