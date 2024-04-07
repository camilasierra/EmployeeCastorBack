package com.employee.EmployeeBack.service;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.employee.EmployeeBack.domain.Employee;
import com.employee.EmployeeBack.domain.TypeDocument;
import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;
import com.employee.EmployeeBack.dto.EmployeeDto;
import com.employee.EmployeeBack.dto.FilterEmployeeDTO;
import com.employee.EmployeeBack.dto.PageDTO;
import com.employee.EmployeeBack.dto.PositionDto;
import com.employee.EmployeeBack.dto.TypeDocumentDto;
import com.employee.EmployeeBack.mapper.EmployeeMapper;
import com.employee.EmployeeBack.mapper.PositionMapper;
import com.employee.EmployeeBack.mapper.TypeDocumentMapper;
import com.employee.EmployeeBack.repository.EmployeeRepository;
import com.employee.EmployeeBack.repository.PositionRepository;
import com.employee.EmployeeBack.repository.TypeDocumentRepositoty;
import com.employee.EmployeeBack.specification.EmployeeSpecification;
import com.employee.EmployeeBack.util.exception.NegocioException;

import org.springframework.data.jpa.domain.Specification;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.*;


@Service
@Slf4j
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
	
	//Cantidad de registros a retornar por página
	private final Integer countRows = 2;

	
	
	
	

	
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
			employee = employeeMapper.toEntityReq(crearEmployeeRequest);
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
            String directory= "c:/Users/camila alejandra/Documents/GitHub/EmployeeCastorFront/employeeFront/src/assets/" +  nameImage;
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
    
    @Override
	public PageDTO findAllForEmployee(FilterEmployeeDTO filterEmployeeDTO) {

		PageDTO page = new PageDTO();

		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
		Date fechaFin = null;
		if (filterEmployeeDTO.getFechaFinal() != null) {
			fechaFin = addDays(filterEmployeeDTO.getFechaFinal(), 1);
		}
		try {
			Specification<Employee> specification = null;
				specification = Specification.where(EmployeeSpecification
						.dateBetween(filterEmployeeDTO.getFechaInicial(), fechaFin)
						.and(EmployeeSpecification.name(filterEmployeeDTO.getName()))
						.and(EmployeeSpecification.lastName(filterEmployeeDTO.getLastName()))
						.and(EmployeeSpecification.numberDocument(filterEmployeeDTO.getNumberDocument()))
						.and(EmployeeSpecification.position(filterEmployeeDTO.getIdPosition()))
				);

				employees = employeeRepository.findAll(specification, Sort.by(Sort.Direction.DESC, "idEmployee")).stream()
						.map(employee -> {
							EmployeeDto dto = employeeMapper
									.toDto(employee);
							return dto;
						})
						.collect(Collectors.toList());
			Pageable pageable;
			if ( 0 != filterEmployeeDTO.getPagina() ) {
				pageable = PageRequest.of(filterEmployeeDTO.getPagina() - 1, countRows);
				if(((filterEmployeeDTO.getPagina() - 1 ) * countRows) >= employees.size()) {
					filterEmployeeDTO.setPagina( (employees.size() / countRows) + 1);
				}
			} else {
				pageable = PageRequest.of(0, Integer.MAX_VALUE);
			}

			int start = (int) pageable.getOffset();
			if(employees.size() <= start && start != 0) {
				start = employees.size() / start;
			}
			final int end = Math.min((start + pageable.getPageSize()), employees.size());
			Page<EmployeeDto> pageSolicitudes = new PageImpl<>(employees.subList(start, end), pageable,
					employees.size());
			page.setPaginaActual(pageable.getPageNumber() + 1);
			page.setPaginasTotales(pageSolicitudes.getTotalPages());
			page.setTotalElements(employees.size());
			page.setRows(pageSolicitudes.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return page;
	}
    
    public Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
    
    
	public Employee update(Employee entity) {
		if (entity == null) {
			throw new NullPointerException("Employee");
		}
		return employeeRepository.save(entity);

	}
    
    @Override
	@Transactional( propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CreateEmployeeResponse modifyEmployee(CreateEmployeeRequest createEmployeeRequest) {
		try {
			Optional<Employee> opEmployee =  employeeRepository.findById(createEmployeeRequest.getIdEmployee());
			if(opEmployee.isEmpty()) {
				throw new NegocioException("El empleado con id " + createEmployeeRequest.getIdEmployee() + " no existe ");
            }else {
				Employee employee = opEmployee.get();
				employee.setName(createEmployeeRequest.getName());
				employee.setLastName(createEmployeeRequest.getLastName());
				employee.setIdTypeDocument(documentRepositoty.findByID(createEmployeeRequest.getIdTypeDocument()));
				employee.setNumberDocument(createEmployeeRequest.getNumberDocument());
				employee.setIdPosition(positionRepository.findById(createEmployeeRequest.getIdPosition()).get());
				employee.setDateAdmission(createEmployeeRequest.getDateAdmission());
				String url = uploadImage2(createEmployeeRequest.getPhotoBase64(), createEmployeeRequest.getNamePhoto());
				employee.setPhoto(url);
				employee = update(employee);
				return employeeMapper.toEntityResponse(employee);
            }
        } catch (Exception e) {
			log.error("Err modify employee", e);
			return null;
		}
	}
    
    
    @Override
	public CreateEmployeeResponse getEmployeeById(Long idEmployee) {
		Employee employee = employeeRepository.findById(idEmployee).orElseThrow(
				() -> new NegocioException("Not found the employee with id " + idEmployee)
		);
		CreateEmployeeResponse createEmployeeResponse = employeeMapper.toEntityResponse(employee);
		return createEmployeeResponse;
	}

	

}
