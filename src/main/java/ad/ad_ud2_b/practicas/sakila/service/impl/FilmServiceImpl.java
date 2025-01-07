package ad.ad_ud2_b.practicas.sakila.service.impl;

import java.util.Set;

import ad.ad_ud2_b.practicas.sakila.repository.FilmDao;
import ad.ad_ud2_b.practicas.sakila.repository.model.Film;

public class FilmServiceImpl implements FilmService {

	private FilmDao filmDao;
	
	public FilmServiceImpl(FilmDao filmDao) {
		this.filmDao = filmDao;
	}
	
	@Override
	public Set<Film> getAll() throws Exception {
		return filmDao.getAll();	
	}

	@Override
	public Film getById(int id) throws Exception {
		return filmDao.getById(id);
	}

	@Override
	public void create(Film film) throws Exception {
		 filmDao.create(film);
	}

	@Override
	public void update(Film film) throws Exception {
		filmDao.update(film);
		
	}

	@Override
	public void delete(int id) throws Exception {
		filmDao.delete(id);
	}

	

	@Override
	public void getAllAsJson() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
