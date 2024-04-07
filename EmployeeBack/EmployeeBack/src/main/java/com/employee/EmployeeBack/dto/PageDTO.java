package com.employee.EmployeeBack.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PageDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<?> rows;
	private Integer paginaActual;
	private Integer paginasTotales;
	private Integer totalElements;
	private String mensaje;
	
	
	public PageDTO() {
		rows = new ArrayList<>();
		paginaActual = 0;
		paginasTotales = 0;
		totalElements = 0;
		mensaje = "";
	}
	
	public PageDTO(List<?> rows, Integer paginaActual, Integer paginasTotales, Integer totalElements) {
		super();
		this.rows = rows;
		this.paginaActual = paginaActual;
		this.paginasTotales = paginasTotales;
		this.totalElements = totalElements;
	}

	
	
}