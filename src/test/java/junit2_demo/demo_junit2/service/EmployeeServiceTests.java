package junit2_demo.demo_junit2.service;

import junit2_demo.demo_junit2.model.Employee;
import junit2_demo.demo_junit2.repository.EmployeeRepository;
import junit2_demo.demo_junit2.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    private  Employee employee;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp(){

        employee=Employee.builder()
                .id(1)
                .firstName("pramod")
                .lastName("saw")
                .email("pramod2gmail,com")
                .build();
       // employeeRepository = Mockito.mock(EmployeeRepository.class);
     //   employeeService= new EmployeeServiceImpl(employeeRepository);

    }
    @DisplayName("junit for saved employee")
    @Test
    public void givenEmployeeObject_whenSavedEmployee_thenReturnEmployeeObject() {
        //given  precondition or setup
//        Employee employee=Employee.builder()
//                .id(1)
//                .firstName("pramod")
//                .lastName("saw")
//                .email("pramod2gmail,com")
//                .build();
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);
        //when action or behaviour  that we are going to test
        Employee savedEmployee = employeeService.createEmployee(employee);

        // verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }
    @DisplayName("junit for saved employee throw exception")
    @Test
    public void givenEmail_whenSavedEmployee_thenThrowException() {
        //given  precondition or setup

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);
        //when action or behaviour  that we are going to test
        Employee savedEmployee = employeeService.createEmployee(employee);

        // verify the output
        Assertions.assertThat(savedEmployee).isNotNull();


    }
    }
