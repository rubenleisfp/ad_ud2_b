package ad.ad_ud2_b.employee_crud.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ad.ad_ud2_b.employee_crud.repository.impl.EmployeeDaoFactory;
import ad.ad_ud2_b.employee_crud.repository.impl.EmployeeFileDao;
import ad.ad_ud2_b.employee_crud.repository.impl.EmployeeJdbcDao;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;

public class AppRepository {

	
	private EmployeeDao employeeDao;
	
	public static void main(String[] args) {
		AppRepository app = new AppRepository();
		app.cfgComponentes();
		app.procesar();
		System.out.println("Done!");
	}
	
	/**
	 * Configuramos los componentes que utilizaremos en nuestra app:
	 * 
	 * DAOs en este caso.
	 * 
	 * Utilizaremos la factoria para obtener una implementación. Fichero o JDBC
	 * 
	 */
	private void cfgComponentes() {
		//TODO en vez de esta linea, debe utilizarse la factoria para obtener la implementación de EmployeeDa a partir de un String
		//Probaremos a usar una u otra cambiado el argumento que le pasemos a la factoria
		//employeeDao = new EmployeeFileDao("src/main/resources/employee.csv");
		//employeeDao = new EmployeeJdbcDao();

		EmployeeDaoFactory employeeDaoFactory = new EmployeeDaoFactory();
		employeeDao = employeeDaoFactory.getEmployeeDao("JDBC");
	}
	
	/**
	 * Playground para jugar con los distintos metodos del CRUD
	 */
	private void procesar()  {
		try {
			readAll();
			//deleteAll();
			
			//create();

			//Actualizacion
			//Employee e= findByName(getEmployeeMock().getName());
			//e.setSalary(new BigDecimal("25000"));
			//update(e);
			
			//Borramos y revisamos el borrado
			//delete(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void deleteAll() throws Exception {
		System.out.println("DeleteAll");
		employeeDao.deleteAll();
		readAll();
	}

	private void create() throws Exception {
		System.out.println("Create:");
		employeeDao.create(getEmployeeMock());
		
		readAll();
	}

	private void readAll() throws Exception {
		List<Employee> allEmployees = employeeDao.getAll();
		allEmployees.forEach(x -> System.out.println(x));
	}
	
	private Employee findByName(String name) throws Exception {
		System.out.println("FindByName:");
		Employee employee = employeeDao.findByName(name);
		if (employee == null ) {
			throw new IllegalArgumentException("El empleado que esta buscando no existe. Nombre: "  + name);
		}
		return employee;
	}
	
	private void update(Employee e) throws Exception {
		System.out.println("Update:");
		employeeDao.update(e);
		readAll();
	}
	
	private void delete(Employee e) throws Exception {
		System.out.println("Delete:");
		employeeDao.delete(e.getId());
		readAll();
	}

	public static Employee getEmployeeMock() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("MockEmployee");
		employee.setSalary(new BigDecimal("15000"));
		employee.setCreatedDate(LocalDateTime.now());

		return employee;
	}
}
