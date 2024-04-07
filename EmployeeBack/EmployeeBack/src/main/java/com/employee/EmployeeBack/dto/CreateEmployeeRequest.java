package com.employee.EmployeeBack.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeRequest {
	
	private Long idEmployee;
	private String name;
	private String lastName;
	private String photoBase64;
	private Date dateAdmission;
    private Long idPosition;
    private Long idTypeDocument;
    private Long numberDocument;
    private String namePhoto;
	

}
