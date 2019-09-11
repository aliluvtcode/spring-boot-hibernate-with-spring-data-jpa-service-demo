package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee =employeeService.findById(employeeId);
	
		if(theEmployee==null) {
			throw new CustomerNotFoundException("Employee Id is not found- "+employeeId);
		}
		return theEmployee;
		
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		
	//also just in case the pass an id in JSON set the id =0
	// this is force the hibernate session to save new item instead of making update on it 	
		theEmployee.setId(0);//if the id null or 0 DAO will insert new customer 
		
		
		employeeService.save(theEmployee);
		
		
		return theEmployee;
		
	}
	
	//add the mapping PUT / customers - Update the customer
	@PutMapping("/employees")
	public Employee updateCustomer(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

	return theEmployee;



	}

	// add the mapping fro GET/customers/{customerId}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		
		Employee tempEmployee =employeeService.findById(employeeId);
		
		if((tempEmployee==null)){
			throw new CustomerNotFoundException("Employee id is not found- "+employeeId);
		
		}
		employeeService.deleteById(employeeId);
		
		//check the studentId against list size
		
		return "Deleted the employee id- "+employeeId;
		
		}
	


	
}










