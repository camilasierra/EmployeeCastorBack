package com.employee.EmployeeBack.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.employee.EmployeeBack.domain.Employee;
import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;

@Mapper
public interface EmployeeMapper {
	
	@Mapping(source = "idPosition", target = "idPosition.idPosition")
	@Mapping(source = "idTypeDocument", target = "idTypeDocument.idTypeDocument")
	public Employee toEntity(CreateEmployeeRequest createEmployeeRequest);
	
	@Mapping(source = "idPosition.idPosition", target = "idPosition")
	@Mapping(source = "idTypeDocument.idTypeDocument", target = "idTypeDocument")
	public CreateEmployeeRequest toEntity(Employee employee);
	
	
	@Mapping(source = "idPosition.idPosition", target = "idPosition")
	@Mapping(source = "idTypeDocument.idTypeDocument", target = "idTypeDocument")
	public CreateEmployeeResponse toEntityResponse(Employee employee);

}
