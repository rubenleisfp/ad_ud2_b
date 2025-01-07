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
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
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
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
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
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	/**
	 * Aumenta el salary de todos los empleados de la empresa, teniendo en cuenta la tabla de rangos salariales
	 * Dependiendo del salario se le aplicará un porcentaje de aumento
	 *
	 * @throws Exception
	 */
	public void increaseSalary() throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	/**
	 * Obtiene el porcentaje de aumento que debe recibir un empleado dependiendo de su salario
	 * @param salary
	 * @return
	 */
	public BigDecimal getIncrease(BigDecimal salary) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

}