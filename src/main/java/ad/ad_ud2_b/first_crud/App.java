package ad.ad_ud2_b.first_crud;

import java.util.ArrayList;
import java.util.List;

public class App {

	private EmpleadoJdbcDao empleadoJdbcDao = new EmpleadoJdbcDao();
	
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}
	
	private void run() {
		List<Employee> employees  = new ArrayList<Employee>();
		employees = empleadoJdbcDao.getAllEmployees();
		System.out.println("All Employees:");
		for (Employee e: employees) {
			System.out.println(e);
		}
		
		System.out.println("Employees with salary less than:");
		employees = empleadoJdbcDao.getAllEmployees();
		for (Employee e: employees) {
			System.out.println(e);
		}
		
	}
}
