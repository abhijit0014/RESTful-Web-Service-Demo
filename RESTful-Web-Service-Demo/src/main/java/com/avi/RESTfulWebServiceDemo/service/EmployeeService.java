package com.avi.RESTfulWebServiceDemo.service;

import com.avi.RESTfulWebServiceDemo.entity.EmployeeEntity;
import com.avi.RESTfulWebServiceDemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity save(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity update(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    public Optional<EmployeeEntity> getById(Long id){
        return employeeRepository.findById(id);
    }

    public List<EmployeeEntity> getAll(){
        return employeeRepository.findAll();
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }
}
