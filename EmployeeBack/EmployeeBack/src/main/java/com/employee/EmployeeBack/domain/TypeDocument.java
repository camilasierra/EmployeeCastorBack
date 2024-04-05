package com.employee.EmployeeBack.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "typedocument")
public class TypeDocument implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idTypeDocument", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeDocument;
	
	@Column(name = "codeTypeDocument")
	private String codeTypeDocument;
	
	
	
	


}