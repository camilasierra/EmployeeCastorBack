package com.employee.EmployeeBack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.employee.EmployeeBack.domain.TypeDocument;

public interface TypeDocumentRepositoty extends JpaRepository<TypeDocument, Long>,JpaSpecificationExecutor<TypeDocument>,
PagingAndSortingRepository<TypeDocument, Long> {
	
	@Query("SELECT TD FROM TypeDocument TD WHERE TD.idTypeDocument = :idTypeDoc ")
	TypeDocument findByID(@Param("idTypeDoc") Long idTypeDoc);

}
