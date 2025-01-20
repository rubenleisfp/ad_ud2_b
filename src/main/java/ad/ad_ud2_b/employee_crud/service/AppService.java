package ad.ad_ud2_b.employee_crud.service;

import java.math.BigDecimal;
import java.util.List;

import ad.ad_ud2_b.employee_crud.repository.EmployeeDao;
import ad.ad_ud2_b.employee_crud.repository.impl.EmployeeJdbcDao;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;

public class AppService {

	EmployeeService employeeService;
	EmployeeDao employeeDao;

	public static void main(String[] args) {
		AppService appService = new AppService();
		appService.cfgComponentes();
		try {
			appService.procesar();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Configuramos los componentes que utilizaremos en nuestra app:
	 * 
	 * DAOs y Services con DAOs inyectados
	 * 
	 */
	private void cfgComponentes() {
		employeeDao = new EmployeeJdbcDao();
		employeeService = new EmployeeService(employeeDao);
	}

	private void procesar() throws Exception {
		//String employeesWithLessSalarySeparatedWithCommas = employeeService.getEmployeesWithLessSalarySeparatedWithCommas(new BigDecimal(30000));
		//System.out.println("employeesWithLessSalarySeparatedWithCommas: " + employeesWithLessSalarySeparatedWithCommas);

		String response = employeeService.getEmployeesWithLessSalaryAsJson(new BigDecimal(30000));
		System.out.println(response);

		employeeService.increaseSalary();

		List<Employee> employeeList = employeeService.getAll();
		//FORMA1
		employeeList.stream().forEach(System.out::println);

//		//FORMA2
//		for (Employee employee : employeeList)
//		{
//			System.out.println(employee);;
//		}
	}

}