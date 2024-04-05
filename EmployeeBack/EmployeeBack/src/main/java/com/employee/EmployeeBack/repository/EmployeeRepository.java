package com.employee.EmployeeBack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.employee.EmployeeBack.domain.Employee;
import com.employee.EmployeeBack.domain.TypeDocument;



public interface EmployeeRepository extends JpaRepository<Employee, Long>,JpaSpecificationExecutor<Employee>,
PagingAndSortingRepository<Employee, Long> {
	
	public List<Employee>  findByIdTypeDocumentAndNumberDocument(TypeDocument idTypeDocument, Integer numberDocument);

}
