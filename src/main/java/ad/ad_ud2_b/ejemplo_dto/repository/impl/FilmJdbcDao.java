package ad.ad_ud2_b.ejemplo_dto.repository.impl;

import ad.ad_ud2_b.ejemplo_dto.repository.FilmDao;
import ad.ad_ud2_b.ejemplo_dto.repository.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FilmJdbcDao implements FilmDao {

	private static final String FILM_BY_AUTOR = "SELECT f.* FROM sakila.actor a "
			+ "inner join sakila.film_actor fa on a.actor_id = fa.actor_id "
			+ "inner join sakila.film f on fa.film_id = f.film_id " + "where first_name like ? "
			+ "order by release_year";

	private static final String CREATE_FILM = "INSERT INTO film (title, description, release_year, language_id, "
			+ " rental_duration, rental_rate, replacement_cost, rating, special_features) "
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_ALL = "SELECT * FROM film";

	private static final String DELETE_ALL = "DELETE FROM film";

	private static final String COUNT_ALL_FILMS = "SELECT count(*) from sakila.film";

	private static final String GET_BY_ID = "SELECT * FROM film WHERE film_id = ?";

	private static final String UPDATE = "UPDATE film SET title = ?, description = ?, release_year = ?, "
			+ "language_id = ?, rental_duration = ?, rental_rate = ?, replacement_cost = ?, "
			+ "rating = ?, special_features = ? WHERE film_id = ?";

	@Override
	public int countAllFilms() {
		try (Connection connection = DriverHelper.getConnection()) {

			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(COUNT_ALL_FILMS)) {
					if (resultSet.next()) {
						return resultSet.getInt(1);
					} else {
						throw new SQLException("Error al contar los films, no se obtuvo el resultado.");
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al contar los films.", e);
		}
	}

	@Override
	public Set<Film> getAllFilmsByActor(String autor, boolean order) {
		Set<Film> films = new HashSet<>();
		try (Connection connection = DriverHelper.getConnection()) {
			String query = FILM_BY_AUTOR;
			if (order) {
				query = query + " asc";
			} else {
				query = query + " desc";
			}

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, "%" + autor + "%");

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Film film = mapResultSetToFilm(resultSet);
						films.add(film);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener el film por ID.", e);
		}
		return films;
	}

	// Métodos CRUD
	@Override
	public void create(Film film) {
		try (Connection connection = DriverHelper.getConnection()) {
			String insertQuery = CREATE_FILM;

			try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
					Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, film.getTitle());
				preparedStatement.setString(2, film.getDescription());
				preparedStatement.setShort(3, film.getReleaseYear());
				preparedStatement.setInt(4, film.getLanguageId());
				preparedStatement.setInt(5, film.getRentalDuration());
				preparedStatement.setBigDecimal(6, film.getRentalRate());
				preparedStatement.setBigDecimal(7, film.getReplacementCost());
				preparedStatement.setString(8, film.getRating());
				preparedStatement.setString(9, String.join(",", film.getSpecialFeatures()));

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Creación del film fallida, no se crearon filas.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al crear el film.", e);
		}
	}

	@Override
	public Film getById(Integer filmId) {
		try (Connection connection = DriverHelper.getConnection()) {

			try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
				preparedStatement.setInt(1, filmId);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						return mapResultSetToFilm(resultSet);
					} else {
						throw new SQLException("No se encontró el film con ID: " + filmId);
					}
				}
			}
		} catch (SQLException e) {

			throw new RuntimeException("Error al obtener el film por ID.", e);
		}
	}

	@Override
	public Set<Film> getAll() {
		Set<Film> films = new HashSet<>();

		try (Connection connection = DriverHelper.getConnection()) {

			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
					while (resultSet.next()) {
						Film film = mapResultSetToFilm(resultSet);
						films.add(film);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener todos los films.", e);
		}

		return films;
	}

	@Override
	public void update(Film film) {
		try (Connection connection = DriverHelper.getConnection()) {


			try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
				preparedStatement.setString(1, film.getTitle());
				preparedStatement.setString(2, film.getDescription());
				preparedStatement.setShort(3, film.getReleaseYear());
				preparedStatement.setInt(4, film.getLanguageId());
				preparedStatement.setInt(5, film.getRentalDuration());
				preparedStatement.setBigDecimal(6, film.getRentalRate());
				preparedStatement.setBigDecimal(7, film.getReplacementCost());
				preparedStatement.setString(8, film.getRating());
				preparedStatement.setString(9, String.join(",", film.getSpecialFeatures()));
				preparedStatement.setInt(10, film.getFilmId());

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Actualización del film fallida, no se actualizó ninguna fila.");
				}
			}
		} catch (SQLException e) {

			throw new RuntimeException("Error al actualizar el film.", e);
		}
	}

	@Override
	public void delete(Integer filmId) {
		try (Connection connection = DriverHelper.getConnection()) {
			String deleteQuery = "DELETE FROM film WHERE film_id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
				preparedStatement.setInt(1, filmId);

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Eliminación del film fallida, no se eliminó ninguna fila.");
				}
			}
		} catch (SQLException e) {

			throw new RuntimeException("Error al eliminar el film.", e);
		}
	}

	private Film mapResultSetToFilm(ResultSet resultSet) throws SQLException {
		Film film = new Film();
		film.setFilmId(resultSet.getInt("film_id"));
		film.setTitle(resultSet.getString("title"));
		film.setDescription(resultSet.getString("description"));
		film.setReleaseYear(resultSet.getShort("release_year"));
		film.setLanguageId(resultSet.getInt("language_id"));
		film.setOriginalLanguageId(resultSet.getInt("original_language_id"));
		film.setRentalDuration(resultSet.getInt("rental_duration"));
		film.setRentalRate(resultSet.getBigDecimal("rental_rate"));
		film.setLength(resultSet.getInt("length"));
		film.setReplacementCost(resultSet.getBigDecimal("replacement_cost"));
		film.setRating(resultSet.getString("rating"));
		Set<String> specialFeatures = new HashSet<>();
		String[] featuresArray = resultSet.getString("special_features").split(",");
		for (String feature : featuresArray) {
			specialFeatures.add(feature);
		}
		film.setSpecialFeatures(specialFeatures);
		film.setLastUpdate(resultSet.getTimestamp("last_update"));

		return film;
	}

	@Override
	public List<Film> searchFilms(String rating, Integer length, Integer rentalDuration) {
		List<Film> matchingFilms = new ArrayList<>();

		try (Connection connection = DriverHelper.getConnection()) {
			StringBuilder searchQuery = new StringBuilder("SELECT * FROM film WHERE 1=1");

			if (rating != null) {
				searchQuery.append(" AND rating = ?");
			}

			if (length != null) {
				searchQuery.append(" AND length = ?");
			}

			if (rentalDuration != null) {
				searchQuery.append(" AND rental_duration = ?");
			}

			try (PreparedStatement preparedStatement = connection.prepareStatement(searchQuery.toString())) {
				int parameterIndex = 1;

				if (rating != null) {
					preparedStatement.setString(parameterIndex++, rating);
				}

				if (length != null) {
					preparedStatement.setInt(parameterIndex++, length);
				}

				if (rentalDuration != null) {
					preparedStatement.setInt(parameterIndex++, rentalDuration);
				}

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Film film = mapResultSetToFilm(resultSet);
						matchingFilms.add(film);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al buscar películas.", e);
		}

		return matchingFilms;
	}


}