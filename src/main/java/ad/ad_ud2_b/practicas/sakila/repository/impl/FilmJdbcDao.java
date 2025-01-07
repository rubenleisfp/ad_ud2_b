package ad.ad_ud2_b.practicas.sakila.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ad.ad_ud2_b.practicas.sakila.repository.FilmDao;
import ad.ad_ud2_b.practicas.sakila.repository.model.Film;

public class FilmJdbcDao implements FilmDao {



	@Override
	public int countAllFilms() {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public Set<Film> getAllFilmsByActor(String autor, boolean order) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	// Métodos CRUD
	@Override
	public void create(Film film) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public Film getById(Integer filmId) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public Set<Film> getAll() {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public void update(Film film) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public void delete(Integer filmId) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	private Film mapResultSetToFilm(ResultSet resultSet) throws SQLException {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public List<Film> searchFilms(String rating, Integer length, Integer rentalDuration) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}


}