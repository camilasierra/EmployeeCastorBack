package com.employee.EmployeeBack.dto;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

	
	private Long idEmployee;
	private String name;
	private Long numberDocument;
	private String lastName;
	private String photo;
	private Date dateAdmission;
    private Long idPosition;
    private Long idTypeDocument;
    private String codeTypeDocument;
    private String namePosition;

}
