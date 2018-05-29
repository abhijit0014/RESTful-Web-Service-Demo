package com.avi.RESTfulWebServiceDemo.controller;

import com.avi.RESTfulWebServiceDemo.entity.EmployeeEntity;
import com.avi.RESTfulWebServiceDemo.exception.UserNotFoundException;
import com.avi.RESTfulWebServiceDemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RootController {

	@Autowired
	private MessageSource messageSource;
    private EmployeeService employeeService;
    public RootController(MessageSource messageSource, EmployeeService employeeService) {
		//this.messageSource = messageSource;
		this.employeeService = employeeService;
	}

	@GetMapping("/test")
    public String index(){
        return "ok";
    }
	

	//NOTE :: return 201 httpStatus code
    @PostMapping("/user/add")
    public ResponseEntity<Object> add(@RequestBody EmployeeEntity employeeEntity,HttpServletRequest request ){
    	System.out.println(employeeEntity.toString());
    	EmployeeEntity empEntity = employeeService.save(employeeEntity);
        
        String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
        URI location = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/user/{id}")
                .buildAndExpand(empEntity.getEmpId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    

    // NOTE :: Custom Exception Handling
    @GetMapping("/user/{id}")
    public Optional<EmployeeEntity> getById (@PathVariable ("id") Long empId)
    {
        Optional<EmployeeEntity> employeeEntity = employeeService.getById(empId);
        if (!employeeEntity.isPresent())
            throw new UserNotFoundException("User Not Found");
        
        return employeeEntity;
    }
    
    @DeleteMapping("/user/delete/{id}")
    public void userDelete (@PathVariable ("id") Long id){
        employeeService.deleteById(id);
        if (employeeService.getById(id)!=null)
            throw new UserNotFoundException("User Not Found");
    }    
    
    //NOTE :: HATEOAS used
    @GetMapping("/user/getAll")
    public Resources<EmployeeEntity> getAll()
    {
        List<EmployeeEntity> employeeList  = employeeService.getAll();
        
        if (employeeList.isEmpty())
            throw new UserNotFoundException("User Not Found");

        for (EmployeeEntity employee : employeeList) {
            Link selfLink = linkTo(RootController.class).slash("user/"+employee.getEmpId()).withRel("href");
            employee.add(selfLink);
        }

        Link link = linkTo(methodOn(RootController.class).getAll()).withSelfRel();
        Resources<EmployeeEntity> result = new Resources<EmployeeEntity>(employeeList,link);
        return result;
    }
    
    //NOTE :: spring Internationalization
    @GetMapping("/test-internationalization")
    public String internationalization (@RequestHeader(name="Accept-Language") Locale locale){
        return messageSource.getMessage("good.morning.msg", null, locale);
    }
    
  //NOTE :: Dynamic Filtering for RESTful Service
    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering() {
    	List<EmployeeEntity> employeeList  = employeeService.getAll();
    	
    	SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("mobile","fname","lname");
    	FilterProvider filters = new SimpleFilterProvider().addFilter("EmployeeFilter", filter);
    	MappingJacksonValue mapping = new MappingJacksonValue(employeeList);
		mapping.setFilters(filters);
		
		return mapping;
    }
        
}
