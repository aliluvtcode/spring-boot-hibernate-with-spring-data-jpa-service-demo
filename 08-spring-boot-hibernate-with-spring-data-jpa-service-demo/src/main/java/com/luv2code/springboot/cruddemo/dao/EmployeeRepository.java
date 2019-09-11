package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//we added this interface and we gave it Employee for the Entity and Integer for the premiry key we have (id)
	//and that's it we don't need to add any methods because this interface will give us all the crud methods 

}
