package com.employee.EmployeeBack.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "position")
public class Position implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idPosition")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPosition;
	
	@Column(name = "name")
	private String name;
	
	
	
	


}