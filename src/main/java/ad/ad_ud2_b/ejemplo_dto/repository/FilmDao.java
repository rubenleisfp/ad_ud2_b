package ad.ad_ud2_b.ejemplo_dto.repository;



import ad.ad_ud2_b.ejemplo_dto.repository.model.Film;

import java.util.List;
import java.util.Set;

public interface FilmDao extends GenericDao<Film, Integer> {

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
