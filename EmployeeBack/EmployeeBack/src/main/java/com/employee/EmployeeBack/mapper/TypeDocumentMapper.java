package com.employee.EmployeeBack.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.employee.EmployeeBack.domain.TypeDocument;
import com.employee.EmployeeBack.dto.TypeDocumentDto;

@Mapper
public interface  TypeDocumentMapper {
	
	 public TypeDocument toEntity(TypeDocumentDto typeDocumentDto);
	 public TypeDocumentDto toDto(TypeDocument typeDocument);
	 
	 public List<TypeDocument> toEntityList(List<TypeDocumentDto> listTypeDocDto);
	 public List<TypeDocumentDto> toDtoyList(List<TypeDocument> listTypeDoc);

}
