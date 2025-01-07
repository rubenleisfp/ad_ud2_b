package ad.ad_ud2_b.practicas.sakila.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para obtener la conexión del driver
 */
public class DriverHelper {

	public static Connection getConnection() throws SQLException { 
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakila?serverTimezone=UTC",
			"root", "castelao");
		return conn;
	}
}
