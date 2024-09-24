package junit2_demo.demo_junit2.service.impl;


import junit2_demo.demo_junit2.exception.ResourceNotFoundException;
import junit2_demo.demo_junit2.model.Employee;
import junit2_demo.demo_junit2.repository.EmployeeRepository;
import junit2_demo.demo_junit2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Optional<Employee> byEmail = employeeRepository.findByEmail(employee.getEmail());

        if(byEmail.isPresent()){
            throw new ResourceNotFoundException("email already  exit with given email: " + employee.getEmail());
        }

      return  employeeRepository.save(employee);
    }
}
