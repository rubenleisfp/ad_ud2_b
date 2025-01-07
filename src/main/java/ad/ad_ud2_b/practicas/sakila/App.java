package ad.ad_ud2_b.practicas.sakila;

import java.util.Set;

import ad.ad_ud2_b.practicas.sakila.repository.impl.FilmJdbcDao;
import ad.ad_ud2_b.practicas.sakila.repository.model.Film;

public class App {
	
    FilmJdbcDao filmDao= new FilmJdbcDao();
    
    public static void main(String[] args) {
    	App app = new App();
    	app.run();


        // Crear un nuevo film
//        Film newFilm = new Film();
//        newFilm.setTitle("Nuevo Film");
//        newFilm.setDescription("Descripción del nuevo film");
//        newFilm.setReleaseYear((short) 2023);
//        newFilm.setLanguageId(1);
//        newFilm.setRentalDuration(5);
//        newFilm.setRentalRate(new BigDecimal("3.99"));
//        newFilm.setReplacementCost(new BigDecimal("14.99"));
//        newFilm.setRating("PG");
//        Set<String> specialFeatures = new HashSet<>();
//        specialFeatures.add("Trailers");
//        specialFeatures.add("Commentaries");
//        newFilm.setSpecialFeatures(specialFeatures);
//
//        int newFilmId = filmCRUD.createFilm(newFilm);
//        System.out.println("Nuevo film creado con ID: " + newFilmId);
//
//        // Obtener y mostrar todos los films
//        filmDao.getAllFilms().forEach(System.out::println);
//
//        // Obtener y mostrar un film por ID
//        Film filmById = filmDao.getFilmById(newFilmId);
//        System.out.println("Film recuperado por ID:\n" + filmById);
//
//        // Actualizar el film recién creado
//        newFilm.setTitle("Nuevo Título");
//        newFilm.setDescription("Nueva descripción del film");
//        filmDao.updateFilm(newFilm);
//
//        // Obtener y mostrar todos los films después de la actualización
//        filmDao.getAllFilms().forEach(System.out::println);
//
//        // Eliminar el film recién creado
//        filmDao.deleteFilm(newFilmId);
//
//        // Obtener y mostrar todos los films después de la eliminación
//        filmDao.getAllFilms().forEach(System.out::println);
    }
    
    public void run() {
    	String autor = "JENN";
    	Set<Film> allFilmsByActor = filmDao.getAllFilmsByActor(autor, false);
    	System.out.println("AllFilmsByActor");
    	for (Film f: allFilmsByActor) {
    		System.out.println("film:" + f);
    	}
    	
    	System.out.println("Done!");
    }
}
