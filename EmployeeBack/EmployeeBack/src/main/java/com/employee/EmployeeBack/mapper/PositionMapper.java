package com.employee.EmployeeBack.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.employee.EmployeeBack.domain.Position;
import com.employee.EmployeeBack.dto.PositionDto;

@Mapper
public interface  PositionMapper {
	
	 public Position toEntity(PositionDto positionDto);
	 public PositionDto toDto(Position position);
	 
	 public List<Position> toEntityList(List<PositionDto> listPositionDto);
	 public List<PositionDto> toDtoyList(List<Position> listPosition);

}
