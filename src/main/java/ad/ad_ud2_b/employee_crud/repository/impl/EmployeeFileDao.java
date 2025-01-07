package ad.ad_ud2_b.employee_crud.repository.impl;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ad.ad_ud2_b.employee_crud.repository.EmployeeDao;
import ad.ad_ud2_b.employee_crud.repository.file.GestorFicheroBuffered;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;

public class EmployeeFileDao implements EmployeeDao {

    public static final String SEPARATOR = ";";
    private String csvFilePath;

    public EmployeeFileDao(String filePath) {
        this.csvFilePath = filePath;
    }

    @Override
    public List<Employee> getAll() throws Exception {
        List<String> employeeLines = GestorFicheroBuffered.readLines(csvFilePath);
        return mapToEmployees(employeeLines);
    }

    @Override
    public Employee getById(Long id) throws Exception {
        List<Employee> employees = getAll();
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null; // Return null if not found
    }

    @Override
    public void create(Employee employee) throws Exception {
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(employee);
        List<String> lines = mapToCsvLines(newEmployees);
        GestorFicheroBuffered.writeLines(this.csvFilePath, true, lines);
    }

    @Override
    public void update(Employee employee) throws Exception {
        List<Employee> employees = getAll();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())) {
                employees.set(i, employee);
                break;
            }
        }
        writeAllEmployees(employees);
    }

    @Override
    public void delete(Long id) throws Exception {
        List<Employee> employees = getAll();
        employees.removeIf(employee -> employee.getId().equals(id));
        writeAllEmployees(employees);
    }

    @Override
    public void deleteAll() throws Exception {
        Files.deleteIfExists(Paths.get(csvFilePath));
    }

    @Override
    public List<Employee> getAllEmployeesBeginA() throws Exception {
        List<Employee> employees = getAll();
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().toUpperCase().startsWith("A")) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    @Override
    public List<Employee> getEmployeesWithLessSalary(BigDecimal salaryCondition) throws Exception {
        List<Employee> employees = getAll();
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary().compareTo(salaryCondition) < 0) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    @Override
    public Employee findByName(String name) throws Exception {
        List<Employee> employees = getAll();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee;
            }
        }
        return null; // Return null if not found
    }

    private void writeAllEmployees(List<Employee> employees) throws Exception {
        List<String> lines = mapToCsvLines(employees);
        GestorFicheroBuffered.writeLines(this.csvFilePath, false, lines);
    }

    private List<Employee> mapToEmployees(List<String> lines) {
        List<Employee> employeeList = new ArrayList<>();
        for (String line : lines) {
            Employee employee = mapToEmployee(line);
            employeeList.add(employee);
        }
        return employeeList;
    }

    private Employee mapToEmployee(String line) {
        String[] data = line.split(SEPARATOR);
        Employee employee = new Employee();
        employee.setId(Long.parseLong(data[0]));
        employee.setName(data[1]);
        employee.setSalary(new BigDecimal(data[2]));
        String truncatedDateTimeString = data[3].substring(0, 29);
        employee.setCreatedDate(LocalDateTime.parse(truncatedDateTimeString));
        return employee;
    }

    private List<String> mapToCsvLines(List<Employee> employees) {
        List<String> csvLines = new ArrayList<>();
        for (Employee e : employees) {
            String csvLine = mapToCsvLine(e);
            csvLines.add(csvLine);
        }
        return csvLines;
    }

    private String mapToCsvLine(Employee employee) {
        return String.format("%d;%s;%s;%s", employee.getId(), employee.getName(),
                employee.getSalary().toString(), employee.getCreatedDate().toString());
    }
}
