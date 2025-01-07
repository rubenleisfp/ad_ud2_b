package ad.ad_ud2_b.first_crud;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoJdbcDao {


	public List<Employee> getAllEmployees() {
		List<Employee> empleados = new ArrayList<>();
		String SQL_SELECT = "Select * from EMPLOYEE";

		try (PreparedStatement preparedStatement = getPreparedStatement(SQL_SELECT)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Employee emp = getEmpleado(resultSet);

				empleados.add(emp);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return empleados;
	}

	public List<Employee> emplyoeeWhereSalaryLess(Double salaryInput) {
		List<Employee> empleados = new ArrayList<>();
		String SQL_SELECT = "Select * from EMPLOYEE WHERE SALARY < ?";

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC",
				"root", "castelao"); PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

			preparedStatement.setDouble(1, salaryInput);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Employee emp = getEmpleado(resultSet);

				empleados.add(emp);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return empleados;
	}
	
	private PreparedStatement getPreparedStatement(String sql) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC", "root",
				"castelao");
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		return preparedStatement;
	}


	private Employee getEmpleado(ResultSet resultSet) throws SQLException {
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

}