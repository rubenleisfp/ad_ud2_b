package ad.ad_ud2_b.practicas.sakila.service.impl;

import java.util.Set;

import ad.ad_ud2_b.practicas.sakila.repository.model.Film;


/**
 * Capa de servicio/negocio, donde se realiza la algoritmia de nuestra aplicacion
 */
public interface FilmService {
	
	Set<Film> getAll() throws Exception;

	Film getById(int id) throws Exception;

	void create(Film film) throws Exception;

	void update(Film film) throws Exception;

	void delete(int id) throws Exception;

	
	void getAllAsJson() throws Exception;


}
