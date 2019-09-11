package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.rest.CustomerNotFoundException;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		//we added @Qualifier("employeeDAOJpaImpl") to make the system qualify the DAO implemantation will use and also
		//we made copy best for the name of the DAOImpl but the first letter is small cuz the bean will understand it 
		employeeRepository= theEmployeeRepository;
	}

	@Override
		public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	
	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee=null;
		if(result.isPresent()) {
			
			theEmployee =result.get();
		}else {
			throw new CustomerNotFoundException("Employee id is not found- "+theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(theId);
		
	}

}
