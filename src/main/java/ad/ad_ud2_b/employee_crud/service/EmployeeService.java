package ad.ad_ud2_b.employee_crud.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ad.ad_ud2_b.employee_crud.repository.EmployeeDao;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;
import ad.ad_ud2_b.employee_crud.service.utils.LocalDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmployeeService  {

	private EmployeeDao employeeDao;
	
	public EmployeeService(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	

	public List<Employee> getEmployeesWithLessSalary(BigDecimal salaryCondition) throws Exception {
		return this.employeeDao.getEmployeesWithLessSalary(salaryCondition);
	}


	/**
	 * Devuelve un String separado por ; con la información de los empleados con un salario menor que el
	 * recibido como argumento
	 *
	 * @param salaryCondition
	 * @return
	 * @throws Exception
	 */
	public String getEmployeesWithLessSalarySeparatedWithCommas(BigDecimal salaryCondition) throws Exception {
		List<Employee> employeesWithLessSalary = this.employeeDao.getEmployeesWithLessSalary(salaryCondition);
		List<String> nameList = employeesWithLessSalary.stream()
			    .map(Employee::getName)
			    .collect(Collectors.toList());	
		String namesCommaSeparated = String.join(",", nameList);
		return namesCommaSeparated;
	}

	/**
	 * Obtiene un String en formato Json con todos los datos de los empleados cuyo salario esta por debajo
	 * del recibido como argumento
	 *
	 * @param salaryCondition
	 * @return
	 * @throws Exception
	 */
	public String getEmployeesWithLessSalaryAsJson(BigDecimal salaryCondition) throws Exception {
		List<Employee> employeesWithLessSalary = this.employeeDao.getEmployeesWithLessSalary(salaryCondition);
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).create();
		return gson.toJson(employeesWithLessSalary);
	}

	/**
	 * Aumenta el salary de todos los empleados de la empresa, teniendo en cuenta la tabla de rangos salariales
	 * Dependiendo del salario se le aplicará un porcentaje de aumento
	 *
	 * @throws Exception
	 */
	public void increaseSalary() throws Exception {
		List<Employee> employees = this.employeeDao.getAll();
		if (employees != null) {
			for (Employee e : employees) {
				BigDecimal aumento = getIncrease(e.getSalary());
				e.setSalary(e.getSalary().add(e.getSalary().multiply(aumento).divide(new BigDecimal(100))));
				this.employeeDao.update(e);
			}
		}
	}

	/**
	 * Obtiene el porcentaje de aumento que debe recibir un empleado dependiendo de su salario
	 * @param salary
	 * @return
	 */
	public BigDecimal getIncrease(BigDecimal salary) {
		if (salary.compareTo(new BigDecimal(18000)) <= 0) {
			return new BigDecimal(20);
		} else if (salary.compareTo(new BigDecimal(25000)) <= 0) {
			return new BigDecimal(10);
		} else {
			return new BigDecimal(5);
		}
	}

}
