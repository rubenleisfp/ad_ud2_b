package ad.ad_ud2_b.employee_crud.repository;


import ad.ad_ud2_b.employee_crud.repository.impl.EmployeeJdbcDao;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeJdbcDaoTest {

    private EmployeeJdbcDao employeeJdbcDao;

    @Before
    public void setUp() throws Exception {
        employeeJdbcDao = new EmployeeJdbcDao();

        // Eliminar todos los datos de la tabla
        employeeJdbcDao.deleteAll();

        // Insertar datos fijos para pruebas
        employeeJdbcDao.create(new Employee(null, "Alice", new BigDecimal("15000"), LocalDateTime.now()));
        employeeJdbcDao.create(new Employee(null, "Bob", new BigDecimal("22000"), LocalDateTime.now()));
        employeeJdbcDao.create(new Employee(null, "Charlie", new BigDecimal("30000"), LocalDateTime.now()));
        employeeJdbcDao.create(new Employee(null, "David", new BigDecimal("18000"), LocalDateTime.now()));
        employeeJdbcDao.create(new Employee(null, "Eve", new BigDecimal("25000"), LocalDateTime.now()));
    }

    @Test
    public void testGetAll() throws Exception {
        List<Employee> employees = employeeJdbcDao.getAll();
        assertEquals(5, employees.size());
    }

    @Test
    public void testGetEmployeesWithLessSalary() throws Exception {
        BigDecimal salaryCondition = new BigDecimal("20000");
        List<Employee> employees = employeeJdbcDao.getEmployeesWithLessSalary(salaryCondition);

        assertEquals(2, employees.size());
        assertTrue(employees.stream().anyMatch(e -> e.getName().equals("Alice")));
        assertTrue(employees.stream().anyMatch(e -> e.getName().equals("David")));
    }

    @Test
    public void testFindByName() throws Exception {
        Employee employee = employeeJdbcDao.findByName("Alice");
        assertNotNull(employee);
        assertEquals("Alice", employee.getName());
        assertEquals(0, new BigDecimal("15000").compareTo(employee.getSalary()));
    }

    @Test
    public void testUpdate() throws Exception {
        Employee employee = employeeJdbcDao.findByName("Alice");
        assertNotNull(employee);

        employee.setName("Alice Updated");
        employee.setSalary(new BigDecimal("16000"));
        employeeJdbcDao.update(employee);

        Employee updatedEmployee = employeeJdbcDao.getById(employee.getId());
        assertNotNull(updatedEmployee);
        assertEquals("Alice Updated", updatedEmployee.getName());
        assertEquals(0, new BigDecimal("16000").compareTo(updatedEmployee.getSalary()));
    }

    @Test
    public void testDelete() throws Exception {
        Employee employee = employeeJdbcDao.findByName("Alice");
        assertNotNull(employee);

        employeeJdbcDao.delete(employee.getId());

        Employee deletedEmployee = employeeJdbcDao.getById(employee.getId());
        assertNull(deletedEmployee);
    }
}
