package com.avi.RESTfulWebServiceDemo.entity;

import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonFilter;
import javax.persistence.*;

@Entity
@Table(name = "employee")
//@JsonIgnoreProperties(value= {"mobile","lname"})
@JsonFilter("EmployeeFilter")
public class EmployeeEntity extends ResourceSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;
    private String fname;
    private String lname;
    private String email;
    private String mobile;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + empId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
