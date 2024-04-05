package com.employee.EmployeeBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.employee.EmployeeBack.domain.Position;


public interface PositionRepository extends JpaRepository<Position, Long>,JpaSpecificationExecutor<Position>,
PagingAndSortingRepository<Position, Long> {

}
