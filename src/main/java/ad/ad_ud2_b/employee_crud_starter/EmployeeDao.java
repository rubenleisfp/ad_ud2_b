package ad.ad_ud2_b.employee_crud_starter;

import java.math.BigDecimal;
import java.util.List;

import ad.ad_ud2_b.employee_crud_starter.model.Employee;

/**
 * Firmas con objetos limpios, desacoplado de JDBC o cualquier otra implementacion
 * 
 * El dia de mañana podría sustiuir la implementacion de JDBC por lectura de fichero p.ej
 * 
 */
public interface EmployeeDao extends GenericDao< Employee>{

	/**
	 * Obtiene todos los empleado cuya primera letra es A
	 * @return
	 * @throws Exception
	 */
	List<Employee> getAllEmployeesBeginA() throws Exception;

	/**
	 * Obtiene todos los empleados con una salario menor al recibido por argumetno
	 * 
	 * @param salaryCondition
	 * @return
	 * @throws Exception
	 */
	List<Employee> getEmployeesWithLessSalary(BigDecimal salaryCondition) throws Exception;
	
	/**
	 * Devuelve el primer empleado cuyo nombre coincida con el pasado por parametro
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Employee findByName(String name) throws Exception;

	

}
