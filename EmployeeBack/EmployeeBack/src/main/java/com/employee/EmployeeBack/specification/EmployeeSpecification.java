package com.employee.EmployeeBack.specification;


import org.springframework.data.jpa.domain.Specification;

import com.employee.EmployeeBack.domain.Employee;

import java.util.Arrays;
import java.util.Date;

public class EmployeeSpecification {
	public static Specification<Employee> dateBetween(Date fechaInicial, Date fechaFinal){
		return (root, query, builder) -> {
			if (fechaInicial == null && fechaFinal == null)
				return null;
			else if (fechaFinal == null)
				return builder.greaterThanOrEqualTo(root.get("dateAdmission"), fechaInicial);
			else if (fechaInicial == null)
				return builder.lessThanOrEqualTo(root.get("dateAdmission"), fechaFinal);
			else
				return builder.between(root.get("dateAdmission"), fechaInicial, fechaFinal);
		};
	}

	public static Specification<Employee> name(String name){
		return (root, query, builder) -> {
			if(name == null)
				return null;
			return builder.equal(root.get("name"), name);
		};
	}
	
	public static Specification<Employee> lastName(String lastName){
		return (root, query, builder) -> {
			if(lastName == null)
				return null;
			return builder.equal(root.get("lastName"), lastName);
		};
	}

	public static Specification<Employee> numberDocument(Long numberDocument){
		return (root, query, builder) -> {
			if(numberDocument == null)
				return null;
			return builder.equal(root.get("numberDocument"), numberDocument);
		};
	}

	public static Specification<Employee> position(Long idPosition){
		return (root, query, builder) -> {
			if(idPosition == null)
				return null;
			return builder.equal(root.get("idPosition").get("idPosition"), idPosition);
		};
	}
	

}