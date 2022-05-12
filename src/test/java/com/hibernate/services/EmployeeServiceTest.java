package com.hibernate.services;

import com.hibernate.models.Employee;
import com.hibernate.util.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Log
class EmployeeServiceTest {

    private Session s;
    private static EmployeeService employeeService;

    @BeforeAll
    static void beforeAll() {
        employeeService = new EmployeeService();
        employeeService.createEmployee(new Employee("jafer", 60000));
        employeeService.createEmployee(new Employee("allen", 70000));
        employeeService.createEmployee(new Employee("ella", 80000));
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    @Order(1)
    void getAllEmployees() {
        List<Employee> actual = employeeService.getAllEmployees();
        List<Employee> expected = new ArrayList<>(Arrays.asList(new Employee(1, "jafer", 60000), new Employee(2, "allen", 70000), new Employee(3, "ella", 80000)));
        assertThat(actual).isNotEmpty().hasSize(3);

    }

    @Test
    @Order(2)
    @DisplayName("Create Employee and assert size increased to 4 total employees")
    void createEmployee() {
        Employee actual = employeeService.createEmployee(new Employee("testing", 20000));
        assertThat(actual).extracting(Employee::getName).isEqualTo("testing");
        assertThat(employeeService.getAllEmployees()).isNotEmpty().hasSize(4);

    }

    @Test
    @Order(3)
    void updateEmployee() {
        Employee db = employeeService.getEmployeeById(1);
        db.setSalary(545454);
        employeeService.updateEmployee(db);
        assertThat(employeeService.getEmployeeById(1)).extracting(Employee::getSalary).isEqualTo(545454D);
    }

    @Test
    @Order(4)
    void deleteEmployee() {
        Employee delete = employeeService.getEmployeeById(4);
        employeeService.deleteEmployee(delete);
        assertThat(employeeService.getAllEmployees()).doesNotContain(delete);
    }


    @Test
    @Order(7)
    void getEmployeeById() {
        assertThat(employeeService.getEmployeeById(3)).extracting(employee -> employee.getId()).isEqualTo(3);
        assertThat(employeeService.getEmployeeById(3)).extracting(employee -> employee.getName()).isEqualTo("ella");
        assertThat(employeeService.getEmployeeById(3)).extracting(employee -> employee.getSalary()).isEqualTo(80000D);
    }

    @Test
    @Order(6)
    void deleteEmployeeRuntimeException() {
        Employee delete = new Employee(0, "j", 120);

        assertThatThrownBy(() -> {
            employeeService.deleteEmployee(delete);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("ID equals zero");
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(strings = {"jafer", "allen", "ella"})
    void testNamesFromDb(String names) {
        List<Employee> list = employeeService.getAllEmployees();
        assertThat(list).extracting(Employee::getName).contains(names);
    }
}