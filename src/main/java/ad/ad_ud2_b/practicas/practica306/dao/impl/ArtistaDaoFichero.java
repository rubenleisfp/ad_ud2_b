package ad.ad_ud2_b.practicas.practica306.dao.impl;

import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.exceptions.RegistroDuplicado;
import ad.ad_ud2_b.practicas.practica306.model.Artista;
import ad.ad_ud2_b.practicas.practica306.utils.GestorBuffered;
import ad.ad_ud2_b.practicas.practica306.utils.UtilidadesArtista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDaoFichero implements ArtistaDao {

	private String nombreFichero;

	private UtilidadesArtista utilidades = new UtilidadesArtista();

	public ArtistaDaoFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	/**
	 * Agrega un artista a la agenda.
	 *
	 * @param artista nombre del artista que se quiere agregar
	 * @throws RegistroDuplicado en caso de que exista un artista con el mismo nombre
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public void agregarArtista(Artista artista) throws  ExcepcionGestorArtista {

		//TODO
		//1.- Crear una lista vacia
		//2.- Añadir el artista recibido como argumento a la lista
		//3.- Transforma la lista de artistas en una lista de lineas mediante utilidades
		//4.- Volcar la lista a fichero mediante GestorBuffered
		//5.- Capturar la excepcion y generar una ExcepcionGestorArtista
		try {
			List<Artista> artistasNuevos = new ArrayList<>();
			artistasNuevos.add(artista);
			List<String> linesFromCitas = utilidades.getLinesFromArtistas(artistasNuevos);
			GestorBuffered.writeLines(this.nombreFichero, true, linesFromCitas);
		} catch (IOException  e) {
			throw new ExcepcionGestorArtista("Error consultando el fichero",e);
		}

	}

	/**
	 * Muestra todos los artistas guardados.
	 *
	 * @return lista de nombres de artistas
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public List<Artista> mostrarArtistas() throws ExcepcionGestorArtista {
		//TODO
		//1.- Leer el fichero mediante GestorBuffered y obtener una lista de lineas
		//2.- Convertir las lineas en artistas mediante utilidades y retornarlos
		//3.- Capturar la excepcion y generar una ExcepcionGestorArtista en caso de error leyendo el fichero
		try {
			List<String> lineas = GestorBuffered.read(this.nombreFichero);
			return utilidades.getArtistasFromLines(lineas);
		}  catch (IOException e) {
			throw new ExcepcionGestorArtista("Error consultando el fichero",e);
		}
	}

	/**
	 * Indica si existe algún artista con el nombre recibido como argumento.
	 *
	 * @param nombre nombre del artista
	 * @return true si existe el artista, false si no
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public boolean tieneArtista(String nombre) throws ExcepcionGestorArtista {
		//TODO
		//1.- Leer el fichero mediante GestorBuffered
		//2.- Comprobar si en la lista anterior esta el nombre buscado
		//3.- Capturar la excepcion y generar una ExcepcionGestorArtista

		List<String> lines = null;
		try {
			lines = GestorBuffered.read(this.nombreFichero);
		} catch (IOException e) {
			throw new ExcepcionGestorArtista("Error consultando el fichero",e);
		}
		List<Artista> citas = utilidades.getArtistasFromLines(lines);
		return obtenerArtista(citas, nombre) != null;
	}

	/**
	 * Obtiene un artista por su nombre.
	 *
	 * @param nombre nombre del artista
	 * @return el artista si existe
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public Artista getArtista(String nombre) throws ExcepcionGestorArtista {
		//TODO
		//1.- Leer el fichero mediante GestorBuffered
		//2.- Comprobar si en la lista anterior esta el nombre buscado.
		// 	  Si esta devolver el artista
		//	  Sino esta devolver nulo
		//3.- Capturar la excepcion y generar una ExcepcionGestorArtista
		List<String> lines = null;
		try {
			lines = GestorBuffered.read(this.nombreFichero);
		} catch (IOException e) {
			throw new ExcepcionGestorArtista("Error consultando el fichero",e);
		}
		List<Artista> artistas = utilidades.getArtistasFromLines(lines);
		return obtenerArtista(artistas, nombre);
	}

	/**
	 * Elimina el artista indicado por su nombre.
	 *
	 * @param nombre nombre del artista
	 * @return true si el artista fue eliminado, false si no
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public boolean eliminarArtista(String nombre) throws ExcepcionGestorArtista {
		//TODO
		//1.- Leer el fichero mediante GestorBuffered y volcar en una lista de lineas
		//2.- Mediante utilidades transformar las lineas en artistas
		//3.- Borrar el artista de la lista
		//4.- Volcar la lista a fichero sobreescribiendolo
		try {
			List<String> lineas = GestorBuffered.read(this.nombreFichero);
			List<Artista> artistas = utilidades.getArtistasFromLines(lineas);

			Artista artistaAEliminar = obtenerArtista(artistas, nombre);
			if (artistaAEliminar == null) {
				return false;
			}
			artistas.remove(artistaAEliminar);

			List<String> lineasActualizadas = utilidades.getLinesFromArtistas(artistas);
			GestorBuffered.writeLines(this.nombreFichero, false, lineasActualizadas);
			return true;

		} catch (IOException e) {
			// 4.- Capturar la excepción y generar una ExcepcionGestorArtista
			throw new ExcepcionGestorArtista("Error al eliminar el artista", e);
		}
	}

	/**
	 * Actualiza el nombre de un artista.
	 *
	 * @param nombre nombre actual del artista
	 * @param nuevoArtista nuevo artista
	 * @return true si el artista fue actualizado, false si no
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public boolean actualizarArtista(String nombre, Artista nuevoArtista) throws ExcepcionGestorArtista {
		//1.- Leer el fichero mediante GestorBuffered y volcar en una lista de lineas
		//2.- Mediante utilidades transformar las lineas en artistas
		//3.- Obtener el artista existente y eliminarlo de la lista
		//4.- Añadir el nuevo artista en la lista
		//5.- Volcar la lista a fichero sobreescribiendolo
		//6.- Capturar la excepcion y generar una ExcepcionGestorArtista

		try {
			List<String> lineas = GestorBuffered.read(this.nombreFichero);
			List<Artista> artistas = utilidades.getArtistasFromLines(lineas);

			Artista artistaExistente = obtenerArtista(artistas, nombre);
			if (artistaExistente == null) {
				return false;
			}
			artistas.remove(artistaExistente);
			artistas.add(nuevoArtista);

			List<String> lineasActualizadas = utilidades.getLinesFromArtistas(artistas);
			GestorBuffered.writeLines(this.nombreFichero, false, lineasActualizadas);
			return true;

		} catch (IOException e) {
			throw new ExcepcionGestorArtista("Error al actualizar el artista", e);
		}
	}

	/**
	 * Elimina todos los artistas de la agenda.
	 *
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos
	 */
	@Override
	public void eliminarTodosArtistas() throws ExcepcionGestorArtista {
		//TODO
		//1.- Borrar la lista mediante GestorBuffered pasandole una lista vacia
		//2.- Capturar la excepcion y generar una ExcepcionGestorArtista

		try {
			// 1.- Borrar la lista mediante GestorBuffered pasando una lista vacía
			GestorBuffered.writeLines(this.nombreFichero, false, new ArrayList<>());

		} catch (IOException e) {
			// 2.- Capturar la excepción y generar una ExcepcionGestorArtista
			throw new ExcepcionGestorArtista("Error al eliminar todos los artistas", e);
		}
	}

	private Artista obtenerArtista(List<Artista> artistas, String nif) {
		for (Artista artista : artistas) {
			if (artista.getNombre().equals(nif)) {
				return artista;
			}
		}
		return null;
	}
}
