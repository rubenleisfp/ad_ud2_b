package ad.ad_ud2_b.employee_crud.repository.impl;

import ad.ad_ud2_b.employee_crud.repository.EmployeeDao;

/**
 * Factoria encargada de obtener una implementacion de EmployeeDao
 * 
 * JDBC, FILE, etc
 */
public class EmployeeDaoFactory {


	public EmployeeDao getEmployeeDao(String type) {
		if (type == null) {
			return null;
		}
		if (type.equalsIgnoreCase("FILE")) {
			return new EmployeeFileDao("src/main/resources/employee.csv");

		} else if (type.equalsIgnoreCase("JDBC")) {
			return new EmployeeJdbcDao();

		}
		return null;
	}
}