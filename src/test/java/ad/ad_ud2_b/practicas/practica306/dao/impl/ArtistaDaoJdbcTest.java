package ad.ad_ud2_b.practicas.practica306.dao.impl;

import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.model.Artista;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ArtistaDaoJdbcTest {

    private ArtistaDao artistaDao;

    @Before
    public void setUp() throws ExcepcionGestorArtista {
        artistaDao = new ArtistaDaoJdbc();
        // Limpiar la base de datos antes de cada prueba
        artistaDao.eliminarTodosArtistas();
    }

    @Test
    public void testAgregarArtista() throws ExcepcionGestorArtista {
        Artista artista = new Artista("Pablo Picasso", 50000.0, LocalDate.of(1881, 10, 25));

        artistaDao.agregarArtista(artista);

        assertTrue(artistaDao.tieneArtista("Pablo Picasso"));
    }

    @Test
    public void testMostrarArtistas() throws ExcepcionGestorArtista {
        Artista artista1 = new Artista("Leonardo da Vinci", 70000.0, LocalDate.of(1452, 4, 15));
        Artista artista2 = new Artista("Vincent van Gogh", 40000.0, LocalDate.of(1853, 3, 30));

        artistaDao.agregarArtista(artista1);
        artistaDao.agregarArtista(artista2);

        List<Artista> artistas = artistaDao.mostrarArtistas();

        assertEquals(2, artistas.size());
        assertTrue(artistas.stream().anyMatch(a -> a.getNombre().equals("Leonardo da Vinci")));
        assertTrue(artistas.stream().anyMatch(a -> a.getNombre().equals("Vincent van Gogh")));
    }

    @Test
    public void testTieneArtista() throws ExcepcionGestorArtista {
        Artista artista = new Artista("Frida Kahlo", 45000.0, LocalDate.of(1907, 7, 6));

        artistaDao.agregarArtista(artista);

        assertTrue(artistaDao.tieneArtista("Frida Kahlo"));
        assertFalse(artistaDao.tieneArtista("Salvador Dalí"));
    }

    @Test
    public void testGetArtista() throws ExcepcionGestorArtista {
        Artista artista = new Artista("Claude Monet", 55000.0, LocalDate.of(1840, 11, 14));

        artistaDao.agregarArtista(artista);

        Artista encontrado = artistaDao.getArtista("Claude Monet");

        assertNotNull(encontrado);
        assertEquals("Claude Monet", encontrado.getNombre());
        assertEquals(55000.0, encontrado.getSalario(), 0.01);
        assertEquals(LocalDate.of(1840, 11, 14), encontrado.getFechaNacimiento());
    }

    @Test
    public void testEliminarArtista() throws ExcepcionGestorArtista {
        Artista artista = new Artista("Georgia O'Keeffe", 60000.0, LocalDate.of(1887, 11, 15));

        artistaDao.agregarArtista(artista);

        assertTrue(artistaDao.tieneArtista("Georgia O'Keeffe"));

        boolean eliminado = artistaDao.eliminarArtista("Georgia O'Keeffe");

        assertTrue(eliminado);
        assertFalse(artistaDao.tieneArtista("Georgia O'Keeffe"));
    }

    @Test
    public void testActualizarArtista() throws ExcepcionGestorArtista {
        Artista artista = new Artista("Andy Warhol", 75000.0, LocalDate.of(1928, 8, 6));
        Artista nuevoArtista = new Artista("Andy Warhol", 80000.0, LocalDate.of(1928, 8, 6));

        artistaDao.agregarArtista(artista);

        boolean actualizado = artistaDao.actualizarArtista("Andy Warhol", nuevoArtista);

        assertTrue(actualizado);

        Artista actualizadoArtista = artistaDao.getArtista("Andy Warhol");
        assertEquals(80000.0, actualizadoArtista.getSalario(), 0.01);
    }

    @Test
    public void testEliminarTodosArtistas() throws ExcepcionGestorArtista {
        Artista artista1 = new Artista("Rembrandt", 60000.0, LocalDate.of(1606, 7, 15));
        Artista artista2 = new Artista("Caravaggio", 50000.0, LocalDate.of(1571, 9, 29));

        artistaDao.agregarArtista(artista1);
        artistaDao.agregarArtista(artista2);

        artistaDao.eliminarTodosArtistas();

        List<Artista> artistas = artistaDao.mostrarArtistas();
        assertTrue(artistas.isEmpty());
    }
}
