package ad.ad_ud2_b.practicas.practica306.service;

import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.exceptions.RegistroDuplicado;
import ad.ad_ud2_b.practicas.practica306.model.Artista;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Clase para gestionar una agenda de artistas.
 *
 * Un artista solo puede tener un registro.
 * Es decir, el nombre del artista se podría considerar clave única en el fichero para buscar, eliminar y actualizar.
 *
 */
public class ArtistaService {

	private ArtistaDao artistaDao; // Reutilizamos DaoCitas como ArtistaDao para el ejemplo

	public ArtistaService(ArtistaDao artistaDao) {
		this.artistaDao = artistaDao;
	}

	/**
	 * Agrega un artista a la agenda.
	 *
	 * @param artista Artista a agregar
	 * @throws RegistroDuplicado en caso de que exista un registro con el mismo nombre.
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public void agregarArtista(Artista artista) throws RegistroDuplicado, ExcepcionGestorArtista {
		if (artistaDao.tieneArtista(artista.getNombre())) {
			throw new RegistroDuplicado("El artista con el nombre:  " + artista.getNombre() + " ya existe en base de datos");
		}
		artistaDao.agregarArtista(artista);  // Cambiado para trabajar con String
	}

	private static final double SALARIO_MINIMO = 1000.0;

	public void validarYAgregarArtista(Artista artista) throws RegistroDuplicado, ExcepcionGestorArtista {
		// 1. Verificar si el artista ya existe
		if (artistaDao.tieneArtista(artista.getNombre())) {
			throw new RegistroDuplicado("El artista con el nombre: " + artista.getNombre() + " ya existe en base de datos.");
		}

		// 2. Verificar que el salario sea válido
		if (artista.getSalario() < SALARIO_MINIMO) {
			throw new ExcepcionGestorArtista("El salario del artista debe ser mayor a " + SALARIO_MINIMO);
		}

		// 3. Validar longitud del nombre
		if (artista.getNombre().length() < 3) {
			throw new ExcepcionGestorArtista("El nombre del artista debe tener al menos 3 caracteres.");
		}

		// 4. Validar la edad mínima
		LocalDate hoy = LocalDate.now();
		if (ChronoUnit.YEARS.between(artista.getFechaNacimiento(), hoy) < 18) {
			throw new ExcepcionGestorArtista("El artista debe ser mayor de 18 años.");
		}

		// 5. Agregar el artista si pasa todas las validaciones
		artistaDao.agregarArtista(artista);
	}


	/**
	 * Muestra todos los artistas guardados.
	 *
	 * @return Lista de artistas.
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public List<Artista> mostrarArtistas() throws ExcepcionGestorArtista {
		return artistaDao.mostrarArtistas();  // Devuelve la lista de artistas
	}

	/**
	 * Indica si el artista existe en la agenda.
	 *
	 * @param artista Nombre del artista.
	 * @return Verdadero si el artista tiene un registro.
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public boolean tieneArtista(String artista) throws ExcepcionGestorArtista {
		return artistaDao.tieneArtista(artista);  // Verifica si el artista ya existe
	}

	/**
	 * Obtiene el registro del artista.
	 *
	 * @param artista Nombre del artista.
	 * @return El artista buscado
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public Artista getArtista(String artista) throws ExcepcionGestorArtista {
		return artistaDao.getArtista(artista);  // Devuelve el nombre del artista
	}

	/**
	 * Elimina el registro de un artista.
	 *
	 * @param artista Nombre del artista a eliminar.
	 * @return Verdadero si se eliminó correctamente.
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public boolean eliminarArtista(String artista) throws ExcepcionGestorArtista {
		return artistaDao.eliminarArtista(artista);  // Elimina el registro del artista
	}

	/**
	 * Actualiza el nombre del artista.
	 *
	 * @param artista Nombre actual del artista.
	 * @param nuevoArtista Artista a agregar
	 * @return Verdadero si se actualizó correctamente.
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public boolean actualizarArtista(String artista, Artista nuevoArtista) throws ExcepcionGestorArtista {
		return artistaDao.actualizarArtista(artista, nuevoArtista);  // Actualiza el nombre del artista
	}

	/**
	 * Elimina todos los registros de artistas.
	 *
	 * @throws ExcepcionGestorArtista en caso de que surge un error con el origen de datos.
	 */
	public void eliminarTodosArtistas() throws ExcepcionGestorArtista {
		artistaDao.eliminarTodosArtistas();  // Elimina todos los registros
	}
}
