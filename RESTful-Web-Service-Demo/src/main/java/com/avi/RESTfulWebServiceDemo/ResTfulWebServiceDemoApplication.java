package com.avi.RESTfulWebServiceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResTfulWebServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResTfulWebServiceDemoApplication.class, args);
	}
	
	//	Project Details
	//	----------------
	//	Exception Handling - 404 Not Found
	//	Internationalization					## http://localhost:8181/test-internationalization
	//	Api Documentation						## http://localhost:8181/swagger-ui.html
	//	Static Filtering for RESTful Service	## EmployeeEntity.java Annotation
	//	Dynamic Filtering for RESTful Service	## http://localhost:8181/dynamic-filtering
}
