package ad.ad_ud2_b.employee_crud.repository.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ad.ad_ud2_b.employee_crud.repository.EmployeeDao;
import ad.ad_ud2_b.employee_crud.repository.model.Employee;

/**
 * Implementación del DAO mediante JDBC
 */
public class EmployeeJdbcDao implements EmployeeDao {

	private static final String SQL_SELECT_FIND_BY_NAME = "Select * from EMPLOYEE where name like ?";
	private static final String SQL_SELECT_FIND_BY_ID = "Select * from EMPLOYEE where ID = ?";
	private static final String SQL_SELECT_BEGIN_A = "Select * from EMPLOYEE where name like 'A%'";
	private static final String SQL_SELECT_ALL = "Select * from EMPLOYEE";
	private static final String SQL_SELECT_WITH_LESS_SALARY = "Select * from EMPLOYEE WHERE SALARY < ?";

	private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";
	private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET NAME=?, SALARY=? WHERE ID = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE from EMPLOYEE where ID = ?";
	private static final String SQL_DELETE_ALL = "DELETE from EMPLOYEE where ID >= 1";

	@Override
	public List<Employee> getAllEmployeesBeginA() throws Exception {
		return query(SQL_SELECT_BEGIN_A);
	}

	@Override
	public List<Employee> getAll() throws Exception {
		return query(SQL_SELECT_ALL);
	}

	@Override
	public Employee findByName(String name) throws Exception {
		Employee employee = null;
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_FIND_BY_NAME)) {
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = mapResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return employee;
	}

	
	
	@Override
	public List<Employee> getEmployeesWithLessSalary(BigDecimal salaryCondition) throws Exception {
		List<Employee> result = new ArrayList<>();
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_WITH_LESS_SALARY)) {
			preparedStatement.setBigDecimal(1, salaryCondition);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee emp = mapResultSet(resultSet);
				result.add(emp);
			}

		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}

	/**
	 * Funcion comun para aquellas queries sin criterios de busqueda
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	private List<Employee> query(String query) throws Exception {
		List<Employee> result = new ArrayList<>();
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee obj = mapResultSet(resultSet);
				result.add(obj);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return result;
	}

	@Override
	public void create(Employee t) throws Exception {
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {
			preparedStatement.setString(1, t.getName());
			preparedStatement.setBigDecimal(2, t.getSalary());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(t.getCreatedDate()));

			int rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Se ha"
						+ " creado un nuevo empleado correctamente.");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Mapea todos los objetos JDBC al objeto Java Empleado
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private Employee mapResultSet(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong("ID");
		String name = resultSet.getString("NAME");
		BigDecimal salary = resultSet.getBigDecimal("SALARY");
		Timestamp createdDate = resultSet.getTimestamp("CREATED_DATE");

		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setSalary(salary);
		// Timestamp -> LocalDateTime
		emp.setCreatedDate(createdDate.toLocalDateTime());
		return emp;
	}

	@Override
	public Employee getById(Long id) throws Exception {
		Employee employee = null;
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_FIND_BY_ID)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = mapResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return employee;
	}

	@Override
	public void update(Employee e) throws Exception {
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setString(1, e.getName());
			preparedStatement.setBigDecimal(2, e.getSalary());
			preparedStatement.setLong(3, e.getId());

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Se ha actualizado el empleado con ID " + e.getId() + " correctamente.");
			} else {
				System.out.println("No se encontró el empleado con ID " + e.getId() + ".");
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		} catch (Exception ex) {
			throw new Exception(ex);
		}

	}

	@Override
	public void delete(Long id) throws Exception {
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_BY_ID)) {
			preparedStatement.setLong(1, id);

			int rowsDeleted = preparedStatement.executeUpdate();
			if (rowsDeleted <= 0) {
				throw new IllegalStateException("No se ha borrado ningun registro");
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public void deleteAll() throws Exception {
		try (Connection conn = DriverHelper.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE_ALL)) {
			int row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}