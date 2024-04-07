package com.employee.EmployeeBack.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Transactional
@Data
@Table(name = "employee")
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "idEmployee", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmployee;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "numberDocument")
	private Long numberDocument;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "dateAdmission")
	private Date dateAdmission;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idPosition")
    private Position idPosition;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idTypeDocument")
    private TypeDocument idTypeDocument;
	
	


}