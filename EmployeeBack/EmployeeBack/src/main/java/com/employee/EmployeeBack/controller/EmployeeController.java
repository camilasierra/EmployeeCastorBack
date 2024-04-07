package com.employee.EmployeeBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.EmployeeBack.domain.Position;
import com.employee.EmployeeBack.dto.CreateEmployeeRequest;
import com.employee.EmployeeBack.dto.CreateEmployeeResponse;
import com.employee.EmployeeBack.dto.FilterEmployeeDTO;
import com.employee.EmployeeBack.dto.PageDTO;
import com.employee.EmployeeBack.dto.PositionDto;
import com.employee.EmployeeBack.dto.StatusCodeDto;
import com.employee.EmployeeBack.dto.TypeDocumentDto;
import com.employee.EmployeeBack.service.IEmployeeService;


@RestController
@RequestMapping("employee/")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService iEmployeeService;
	
	
	/**
	 * Lista los tipos de documento
	 * @param 
     * @return ResponseEntity<StatusCodeDto> informacion del exito 
	 */
	@GetMapping("listTypeDocument")
	public ResponseEntity<Object> listTypeDocument(){
		StatusCodeDto statusDTO = new StatusCodeDto();
		try {
			
			List<TypeDocumentDto> createEmployeeResponse = iEmployeeService.listTypeDocument();
			if(!createEmployeeResponse.isEmpty()) {
				statusDTO.setStatusCode(HttpStatus.OK.value());
				statusDTO.setMessage("Type document get exit ");
				statusDTO.setResponse(createEmployeeResponse);
				return ResponseEntity.ok(statusDTO);
			}else {
				statusDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
				statusDTO.setMessage("Error not found type document");
				statusDTO.setResponse(null);
				return ResponseEntity.ok(statusDTO);
			}
		}catch (Exception e) {
			statusDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			statusDTO.setMessage("Error interno del servidor: " + e.getMessage());
			statusDTO.setResponse(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusDTO);
		}
	}
	
	/**
	 * Lista los cargos
	 * @param 
	 * @return ResponseEntity<StatusCodeDto> informacion del exito
	 */
	@GetMapping("listPosition")
	public ResponseEntity<Object> listPosition (){
		StatusCodeDto statusDTO = new StatusCodeDto();
		try {
			
			List<PositionDto> listPosition = iEmployeeService.listPosition();
			if(!listPosition.isEmpty()) {
				statusDTO.setStatusCode(HttpStatus.OK.value());
				statusDTO.setMessage("Position get exit ");
				statusDTO.setResponse(listPosition);
				return ResponseEntity.ok(statusDTO);
			}else {
				statusDTO.setStatusCode(HttpStatus.NOT_FOUND.value());
				statusDTO.setMessage("Error not found position");
				statusDTO.setResponse(null);
				return ResponseEntity.ok(statusDTO);
			}
		}catch (Exception e) {
			statusDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			statusDTO.setMessage("Error interno del servidor: " + e.getMessage());
			statusDTO.setResponse(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusDTO);
		}
	}
	
	/**
     * Servicio donde se crea un empleado 
     *
     * @param CrearRolRequest datos del rol
     * @return ResponseEntity<StatusCodeDto> informacion del exito de la creación 
     */
	
	// POST
	@PostMapping("createEmployee")
	public ResponseEntity<StatusCodeDto> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
		StatusCodeDto statusDTO = new StatusCodeDto();
		
		try {
			
			CreateEmployeeResponse createEmployeeResponse = iEmployeeService.createEmployee(createEmployeeRequest);
			if(null != createEmployeeRequest) {
				statusDTO.setStatusCode(HttpStatus.OK.value());
				statusDTO.setMessage("Employee created successfully");
				statusDTO.setResponse(createEmployeeResponse);
				return ResponseEntity.ok(statusDTO);
			}else {
				statusDTO.setStatusCode(HttpStatus.CONFLICT.value());
				statusDTO.setMessage("Error creating employee");
				statusDTO.setResponse(null);
				return ResponseEntity.ok(statusDTO);
			}
		}catch (Exception e) {
			statusDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			statusDTO.setMessage("Error interno del servidor: " + e.getMessage());
			statusDTO.setResponse(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusDTO);
		}
		
	}
	
	/**
     * Servicio donde se actualiza un empleado 
     *
     * @param CrearRolRequest datos del rol
     * @return ResponseEntity<StatusCodeDto> informacion del exito de la creación 
     */
	
	// POST
	@PostMapping("updateEmployee")
	public ResponseEntity<StatusCodeDto> modifyEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
		StatusCodeDto statusDTO = new StatusCodeDto();
		
		try {
			
			CreateEmployeeResponse createEmployeeResponse = iEmployeeService.modifyEmployee(createEmployeeRequest);
			if(null != createEmployeeRequest) {
				statusDTO.setStatusCode(HttpStatus.OK.value());
				statusDTO.setMessage("Employee update successfully");
				statusDTO.setResponse(createEmployeeResponse);
				return ResponseEntity.ok(statusDTO);
			}else {
				statusDTO.setStatusCode(HttpStatus.CONFLICT.value());
				statusDTO.setMessage("Error updating employee");
				statusDTO.setResponse(null);
				return ResponseEntity.ok(statusDTO);
			}
		}catch (Exception e) {
			statusDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			statusDTO.setMessage("Error interno del servidor: " + e.getMessage());
			statusDTO.setResponse(null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusDTO);
		}
		
	}
	
	 @PostMapping("/findEmployee")
	    public ResponseEntity<?> findEmployee(@RequestBody FilterEmployeeDTO filterEmployeeDTO) {
	        StatusCodeDto statusDTO = new StatusCodeDto();
			
			try {
				
				PageDTO page = iEmployeeService.findAllForEmployee(filterEmployeeDTO);
				if(null != page) {
					statusDTO.setStatusCode(HttpStatus.OK.value());
					statusDTO.setMessage("Employee list successfully");
					statusDTO.setResponse(page);
					return ResponseEntity.ok(statusDTO);
				}else {
					statusDTO.setStatusCode(HttpStatus.CONFLICT.value());
					statusDTO.setMessage("Error list employee");
					statusDTO.setResponse(null);
					return ResponseEntity.ok(statusDTO);
				}
			}catch (Exception e) {
				statusDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				statusDTO.setMessage("Error interno del servidor: " + e.getMessage());
				statusDTO.setResponse(null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusDTO);
			}

	    }
	 
	 	/**
		 * Consultar empleado por identificador del  empleado 
		 *
		 * @param idEmployee identificador del empleado 
		 * @return rol obtenido
		 */
		@GetMapping("/getEmployeeById/{idEmployee}")
		public ResponseEntity<?> getEmployeeById(@PathVariable("idEmployee") Long idEmployee) {
			StatusCodeDto statusDTO = new StatusCodeDto();
			
			try {
				CreateEmployeeResponse createEmployeeResponse = iEmployeeService.getEmployeeById(idEmployee);
				if(null != createEmployeeResponse) {
					statusDTO.setStatusCode(HttpStatus.OK.value());
					statusDTO.setMessage("Get Employee successfully");
					statusDTO.setResponse(createEmployeeResponse);
					return ResponseEntity.ok(statusDTO);
				}else {
					statusDTO.setStatusCode(HttpStatus.CONFLICT.value());
					statusDTO.setMessage("Error get employee");
					statusDTO.setResponse(null);
					return ResponseEntity.ok(statusDTO);
				}
			}catch (Exception e) {
				statusDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				statusDTO.setMessage("Error interno del servidor: " + e.getMessage());
				statusDTO.setResponse(null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusDTO);
			}
		}
}
