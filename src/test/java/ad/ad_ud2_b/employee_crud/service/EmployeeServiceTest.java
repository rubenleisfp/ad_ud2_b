package ad.ad_ud2_b.employee_crud.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import ad.ad_ud2_b.employee_crud.repository.EmployeeDao;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeDao);
    }

    @Test
    public void testGetEmployeesWithLessSalary() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
            new Employee(1L, "Alice", new BigDecimal("15000"), LocalDateTime.now().minusDays(10)),
            new Employee(2L, "Bob", new BigDecimal("17000"), LocalDateTime.now().minusDays(5))
        );

        when(employeeDao.getEmployeesWithLessSalary(new BigDecimal("20000"))).thenReturn(mockEmployees);

        List<Employee> result = employeeService.getEmployeesWithLessSalary(new BigDecimal("20000"));

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
        verify(employeeDao, times(1)).getEmployeesWithLessSalary(new BigDecimal("20000"));
    }

    @Test
    public void testGetEmployeesWithLessSalarySeparatedWithCommas() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
            new Employee(1L, "Alice", new BigDecimal("15000"), LocalDateTime.now().minusDays(10)),
            new Employee(2L, "Bob", new BigDecimal("17000"), LocalDateTime.now().minusDays(5))
        );

        when(employeeDao.getEmployeesWithLessSalary(new BigDecimal("20000"))).thenReturn(mockEmployees);

        String result = employeeService.getEmployeesWithLessSalarySeparatedWithCommas(new BigDecimal("20000"));

        assertEquals("Alice,Bob,", result);
        verify(employeeDao, times(1)).getEmployeesWithLessSalary(new BigDecimal("20000"));
    }

    @Test
    public void testGetEmployeesWithLessSalaryAsJson() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
            new Employee(1L, "Alice", new BigDecimal("15000"), LocalDateTime.now().minusDays(10)),
            new Employee(2L, "Bob", new BigDecimal("17000"), LocalDateTime.now().minusDays(5))
        );

        when(employeeDao.getEmployeesWithLessSalary(new BigDecimal("20000"))).thenReturn(mockEmployees);

        String result = employeeService.getEmployeesWithLessSalaryAsJson(new BigDecimal("20000"));

        assertTrue(result.contains("Alice"));
        assertTrue(result.contains("Bob"));
        verify(employeeDao, times(1)).getEmployeesWithLessSalary(new BigDecimal("20000"));
    }

    @Test
    public void testIncreaseSalary() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
            new Employee(1L, "Alice", new BigDecimal("15000"), LocalDateTime.now().minusDays(10)),
            new Employee(2L, "Bob", new BigDecimal("25000"), LocalDateTime.now().minusDays(5)),
            new Employee(3L, "Charlie", new BigDecimal("30000"), LocalDateTime.now().minusDays(20))
        );

        when(employeeDao.getAll()).thenReturn(mockEmployees);

        employeeService.increaseSalary();

        verify(employeeDao, times(1)).update(new Employee(1L, "Alice", new BigDecimal("18000"), mockEmployees.get(0).getCreatedDate()));
        verify(employeeDao, times(1)).update(new Employee(2L, "Bob", new BigDecimal("27500"), mockEmployees.get(1).getCreatedDate()));
        verify(employeeDao, times(1)).update(new Employee(3L, "Charlie", new BigDecimal("31500"), mockEmployees.get(2).getCreatedDate()));
    }

    @Test
    public void testGetIncrease() {
        assertEquals(new BigDecimal("20"), employeeService.getIncrease(new BigDecimal("15000")));
        assertEquals(new BigDecimal("10"), employeeService.getIncrease(new BigDecimal("20000")));
        assertEquals(new BigDecimal("5"), employeeService.getIncrease(new BigDecimal("30000")));
    }
}
