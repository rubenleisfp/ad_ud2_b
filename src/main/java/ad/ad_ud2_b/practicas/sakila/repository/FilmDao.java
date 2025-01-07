package ad.ad_ud2_b.practicas.sakila.repository;

import java.util.List;
import java.util.Set;

import ad.ad_ud2_b.practicas.sakila.repository.model.Film;

public interface FilmDao extends GenericDao<Film, Integer>{

	int countAllFilms();

	Set<Film> getAllFilmsByActor(String autor, boolean order);
	

	/**
	 * Busca la peliculas que cumplan los siguientes criterios.
	 * 
	 * Estos criterios son opcionales, es decir, el usuario puede indicar rating, length o rentalDuration
	 * o cualquier combinacion de ellos.
	 * 
	 * @param rating
	 * @param length
	 * @param rentalDuration
	 * @return
	 */
	List<Film> searchFilms(String rating, Integer length, Integer rentalDuration);
	
	
}
