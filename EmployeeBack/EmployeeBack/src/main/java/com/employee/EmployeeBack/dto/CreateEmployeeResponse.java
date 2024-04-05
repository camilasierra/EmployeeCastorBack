package com.employee.EmployeeBack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeResponse {
	
	private Long idEmployee;
	private String name;
	private String lastName;
	private String photo;
	private String dateAdmission;
    private Long idPosition;
    private Long idTypeDocument;
    private Integer numberDocument;

}
