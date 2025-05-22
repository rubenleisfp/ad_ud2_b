package ad.ad_ud2_b.ejemplo_dto;

import ad.ad_ud2_b.ejemplo_dto.repository.FilmDao;
import ad.ad_ud2_b.ejemplo_dto.repository.impl.FilmJdbcDao;
import ad.ad_ud2_b.ejemplo_dto.service.FilmService;

public class App {
	
    FilmService filmService;
	FilmDao filmDao;
    
    public static void main(String[] args) {
		App app = new App();
		app.run();
	}
    
    public void run() {
 		cfg();
        getJson();
    }

    /**
     * Instanciamos el DAO y la clase SERVICE
     */
    private void cfg() {
        filmDao = new FilmJdbcDao();
        filmService = new FilmService(filmDao);
    }

    /**
     * LLamamos al service para obtener la informacion en JSON
     */
    private void getJson() {
        try {
            String json = filmService.getFilmTitleAndYearAsJson();
            System.out.println("JSON:");
            System.out.println(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
