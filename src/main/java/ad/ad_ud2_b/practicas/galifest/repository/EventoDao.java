package ad.ad_ud2_b.practicas.galifest.repository;

import java.util.List;

import ad.ad_ud2_b.practicas.galifest.repository.model.Evento;

public interface EventoDao {

    /**
     * Obtiene todos los eventos.
     * 
     * @return Una lista de objetos Evento que representa todos los eventos almacenados.
     * @throws Exception Si ocurre un error al obtener los eventos.
     */
    List<Evento> getAll() throws Exception;

    /**
     * Crea un nuevo evento en la base de datos.
     * 
     * @param evento El objeto Evento que se desea crear.
     * @throws Exception Si ocurre un error al crear el evento.
     */
    void create(Evento evento) throws Exception;

    /**
     * Obtiene todos los eventos cuyo precio de coste sea mayor o igual al mínimo especificado.
     * 
     * @param minimumCost El coste mínimo para filtrar los eventos.
     * @return Una lista de objetos Evento que cumplen con el criterio del coste mínimo.
     * @throws Exception Si ocurre un error al obtener los eventos.
     */
    List<Evento> getWithGreaterCost(double minimumCost) throws Exception;

}
