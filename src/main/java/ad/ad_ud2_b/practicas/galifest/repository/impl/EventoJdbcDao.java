package ad.ad_ud2_b.practicas.galifest.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ad.ad_ud2_b.practicas.galifest.commons.utils.DriverHelper;
import ad.ad_ud2_b.practicas.galifest.repository.EventoDao;
import ad.ad_ud2_b.practicas.galifest.repository.model.Evento;


public class EventoJdbcDao implements EventoDao {

	private static final String CREATE_EVENTO = "INSERT INTO evento ( nombre_del_evento, fecha_hora, ubicacion, "
			+ " descripcion, precio_de_coste) " + "	VALUES (?, ?, ?, ?, ?)";

	private static final String SELECT_ALL = "SELECT * FROM evento";
	
	private static final String SELECT_WITH_GREATER_PRECIO_COSTE = "SELECT * FROM evento where precio_de_coste > ?";

	// Métodos CRUD
	@Override
	public void create(Evento evento) throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}
	
	@Override
	public List<Evento> getWithGreaterCost(double minimuCost) throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}


	@Override
	public List<Evento> getAll() throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	private Evento mapResultSet(ResultSet resultSet) throws SQLException {
		String nombreEvento = resultSet.getString("nombre_del_evento");
		Date fechaHora = resultSet.getDate("fecha_hora");
		String ubicacion = resultSet.getString("ubicacion");
		String descripcion = resultSet.getString("descripcion");
		double precioDeCoste = resultSet.getDouble("precio_de_coste");

		Evento evento = new Evento();
		evento.setNombreDelEvento(nombreEvento);
		evento.setFechaHora(fechaHora);
		evento.setUbicacion(ubicacion);
		evento.setDescripcion(descripcion);
		evento.setPrecioDeCoste(precioDeCoste);

		return evento;
	}

}