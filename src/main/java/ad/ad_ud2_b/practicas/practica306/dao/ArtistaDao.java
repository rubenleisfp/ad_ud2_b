package ad.ad_ud2_b.practicas.practica306.dao;

import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.model.Artista;

import java.util.List;

/**
 * Clase para gestionar la agenda de artistas.
 *
 * Sirve para realizar un CRUD y más operaciones sobre un origen de datos.
 * El origen de datos será un fichero, BBDD, ...
 *
 */
public interface ArtistaDao {

	/**
	 * Agrega un artista a la agenda.
	 *
	 * @param artista artista que se quiere agregar
	 *
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	void agregarArtista(Artista artista) throws  ExcepcionGestorArtista;

	/**
	 * Muestra todos los artistas guardados.
	 *
	 * @return lista de nombres de artistas
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	List<Artista> mostrarArtistas() throws ExcepcionGestorArtista;

	/**
	 * Indica si existe algún artista con el nombre recibido como argumento.
	 *
	 * @param nombre nombre del artista
	 * @return true si existe el artista, false si no
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	boolean tieneArtista(String nombre) throws ExcepcionGestorArtista;

	/**
	 * Obtiene un artista por su nombre.
	 *
	 * @param nombre nombre del artista
	 * @return el nombre del artista si existe
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	Artista getArtista(String nombre) throws ExcepcionGestorArtista;

	/**
	 * Elimina el artista indicado por su nombre.
	 *
	 * @param nombre nombre del artista
	 * @return true si el artista fue eliminado, false si no
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	boolean eliminarArtista(String nombre) throws ExcepcionGestorArtista;

	/**
	 * Actualiza el nombre de un artista.
	 *
	 * @param nombre nombre actual del artista
	 * @param nuevoArtista datos del nuevo artista, incluyendo su nombre
	 * @return true si el artista fue actualizado, false si no
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	boolean actualizarArtista(String nombre, Artista nuevoArtista) throws ExcepcionGestorArtista;

	/**
	 * Elimina todos los artistas de la agenda.
	 *
	 * @throws ExcepcionGestorArtista en caso de que surja un error con el origen de datos
	 */
	void eliminarTodosArtistas() throws ExcepcionGestorArtista;
}
