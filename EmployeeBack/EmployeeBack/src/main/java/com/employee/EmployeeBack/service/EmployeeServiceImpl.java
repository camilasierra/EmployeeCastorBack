package com.employee.EmployeeBack.service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.employee.EmployeeBack.domain.Employee;
import com.employee.EmployeeBack.domain.TypeDocument;
import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;
import com.employee.EmployeeBack.dto.PositionDto;
import com.employee.EmployeeBack.dto.TypeDocumentDto;
import com.employee.EmployeeBack.mapper.EmployeeMapper;
import com.employee.EmployeeBack.mapper.PositionMapper;
import com.employee.EmployeeBack.mapper.TypeDocumentMapper;
import com.employee.EmployeeBack.repository.EmployeeRepository;
import com.employee.EmployeeBack.repository.PositionRepository;
import com.employee.EmployeeBack.repository.TypeDocumentRepositoty;
import com.employee.EmployeeBack.util.exception.NegocioException;

import lombok.extern.slf4j.Slf4j;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private  EmployeeRepository employeeRepository ;
	
	@Autowired
	private  TypeDocumentRepositoty documentRepositoty ;
	
	@Autowired
	private  TypeDocumentMapper typeDocumentMapper ;
	
	@Autowired
	private  EmployeeMapper employeeMapper;
	
	@Autowired
	private  PositionMapper positionMapper;
	
	@Autowired
	private  PositionRepository positionRepository;
	
	
	
	

	
	public EmployeeServiceImpl() {
		super();
	}


	@Override
	public CreateEmployeeResponse createEmployee(CreateEmployeeRequest crearEmployeeRequest) {
		Employee employee = new Employee();
		try {
			TypeDocument typeDocument = documentRepositoty.findByID(crearEmployeeRequest.getIdTypeDocument());
			if(null == typeDocument) {
				throw new NegocioException("The document type not exists");
			}
			if(0 != employeeRepository.findByIdTypeDocumentAndNumberDocument(typeDocument , crearEmployeeRequest.getNumberDocument()).size()) {
				throw new NegocioException("The type and number of the document already exists");
			}
			String url = uploadImage2(crearEmployeeRequest.getPhotoBase64(), crearEmployeeRequest.getNamePhoto());
			employee = employeeMapper.toEntity(crearEmployeeRequest);
			employee.setPhoto(url);
			employee = employeeRepository.save(employee);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("Employee registration failed: " + e.getMessage());
			
		}
		return employeeMapper.toEntityResponse(employee);
	}
	
	
    @SuppressWarnings("resource")
	public String uploadImage2( String imageValue, String nameImage){
        try{
            byte[] imageByte=Base64.decodeBase64(imageValue);
            String directory= "/imagenes/" +  nameImage;
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            fileOutputStream.write(imageByte);
            return  directory;
        }catch(Exception e){
           e.printStackTrace();
           return "failed ";
        }
    }
    
    @Override
	public List<TypeDocumentDto> listTypeDocument(){
		return typeDocumentMapper.toDtoyList(documentRepositoty.findAll());
	}
    
    @Override
	public List<PositionDto> listPosition(){
		return positionMapper.toDtoyList(positionRepository.findAll());
	}
	

}
