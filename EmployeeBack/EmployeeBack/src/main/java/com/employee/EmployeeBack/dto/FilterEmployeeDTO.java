package com.employee.EmployeeBack.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterEmployeeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEmployee;
	private String name;
	private String lastName;
	private Date fechaInicial;
	private Date fechaFinal;
    private Long idPosition;
    private Long idTypeDocument;
    private Long numberDocument;
    private Integer pagina;
	
	
}