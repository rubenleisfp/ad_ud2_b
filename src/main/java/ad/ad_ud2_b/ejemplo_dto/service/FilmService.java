package ad.ad_ud2_b.ejemplo_dto.service;

import ad.ad_ud2_b.ejemplo_dto.repository.FilmDao;
import ad.ad_ud2_b.ejemplo_dto.repository.model.Film;
import ad.ad_ud2_b.ejemplo_dto.service.dto.FilmDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilmService  {

	private FilmDao filmDao;
	private Gson gson = new Gson();
	
	public FilmService(FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	public Set<Film> getAll() throws Exception {
		return filmDao.getAll();	
	}

	public Film getById(int id) throws Exception {
		return filmDao.getById(id);
	}

	public void create(Film film) throws Exception {
		 filmDao.create(film);
	}

	public void update(Film film) throws Exception {
		filmDao.update(film);
	}

	public void delete(int id) throws Exception {
		filmDao.delete(id);
	}

	/**
	 * Transforma un film en un dto
	 *
	 * @param film
	 * @return
	 */
	public FilmDto toDto(Film film) {
		throw new UnsupportedOperationException("Falta implementar por el alumno");
	}

	/**
	 * Transforma una lista de films en una lista de dtos
	 * @param films
	 * @return
	 */
	public List<FilmDto> toDtos(Set<Film> films) {
		throw new UnsupportedOperationException("Falta implementar por el alumno");
	}

	/**
	 * Obtiene todas las peliculas en formato JSON,
	 * pero solo su titulo y año
	 * @return
	 * @throws Exception
	 */
	public String getFilmTitleAndYearAsJson() throws Exception {
		throw new UnsupportedOperationException("Falta implementar por el alumno");
	}

}
