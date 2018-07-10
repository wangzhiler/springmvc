package com.qaqa.springmvc.crud.entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by thinkpad on 2018/7/5.
 */
public class Employee {
    private Integer id;

    @NotEmpty
    private String lastname;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//    private String lastName;

    @Email
    private String email;

    //1 male,  0 female
    private Integer gender;

    private Department department;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @NumberFormat(pattern = "#,###,###.#")
    private Float salary;


    public Employee() {
    }

    public Employee(Integer id, String lastname, String email, Integer gender, Department department) {
        this.id = id;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                ", birth=" + birth +
                ", salary=" + salary +
                '}';
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
