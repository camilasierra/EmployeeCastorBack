package com.employee.EmployeeBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
public class EmployeeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeBackApplication.class, args);
	}

}
