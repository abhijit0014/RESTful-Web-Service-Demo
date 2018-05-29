package com.avi.RESTfulWebServiceDemo.repository;

import com.avi.RESTfulWebServiceDemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
