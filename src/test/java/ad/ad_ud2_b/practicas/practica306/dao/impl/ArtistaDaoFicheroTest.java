package ad.ad_ud2_b.practicas.practica306.dao.impl;


import ad.ad_ud2_b.practicas.practica306.dao.impl.ArtistaDaoFichero;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.exceptions.RegistroDuplicado;
import ad.ad_ud2_b.practicas.practica306.model.Artista;
import ad.ad_ud2_b.practicas.practica306.utils.GestorBuffered;
import ad.ad_ud2_b.practicas.practica306.utils.UtilidadesArtista;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArtistaDaoFicheroTest {

    private static final String TEST_FILE = "test_artistas.txt";
    private ArtistaDaoFichero dao;

    private UtilidadesArtista utilidadesArtista = new UtilidadesArtista();

    @Before
    public void setUp() throws IOException {
        // Asegurarse de que el archivo de prueba esté vacío antes de cada prueba
        new File(TEST_FILE).delete();
        GestorBuffered.writeLines(TEST_FILE, false, new ArrayList<>());
        dao = new ArtistaDaoFichero(TEST_FILE);
    }

    @After
    public void tearDown() {
        // Eliminar el archivo de prueba después de las pruebas
        new File(TEST_FILE).delete();
    }

    @Test
    public void testAgregarArtista() throws ExcepcionGestorArtista, RegistroDuplicado {
        Artista artista = new Artista("Nombre Artista", 2000.0, utilidadesArtista.convertStringToLocalDate("1985-05-10"));
        dao.agregarArtista(artista);

        List<Artista> artistas = dao.mostrarArtistas();
        assertEquals(1, artistas.size());
        assertEquals("Nombre Artista", artistas.get(0).getNombre());
    }

    @Test
    public void testMostrarArtistas() throws ExcepcionGestorArtista, RegistroDuplicado {
        Artista artista1 = new Artista("Artista 1", 1000.0, utilidadesArtista.convertStringToLocalDate("1990-01-01"));
        Artista artista2 = new Artista("Artista 2", 1500.0, utilidadesArtista.convertStringToLocalDate("1980-12-12"));
        dao.agregarArtista(artista1);
        dao.agregarArtista(artista2);

        List<Artista> artistas = dao.mostrarArtistas();
        assertEquals(2, artistas.size());
    }

    @Test
    public void testTieneArtista() throws ExcepcionGestorArtista, RegistroDuplicado {
        Artista artista = new Artista("Artista Test", 1200.0, utilidadesArtista.convertStringToLocalDate("2000-07-20"));
        dao.agregarArtista(artista);

        assertTrue(dao.tieneArtista("Artista Test"));
        assertFalse(dao.tieneArtista("Artista Inexistente"));
    }

    @Test
    public void testEliminarArtista() throws ExcepcionGestorArtista, RegistroDuplicado {
        Artista artista = new Artista("Artista Test", 1200.0, utilidadesArtista.convertStringToLocalDate("2000-07-20"));
        dao.agregarArtista(artista);

        boolean eliminado = dao.eliminarArtista("Artista Test");
        assertTrue(eliminado);
        assertFalse(dao.tieneArtista("Artista Test"));
    }

    @Test
    public void testActualizarArtista() throws ExcepcionGestorArtista, RegistroDuplicado {
        Artista artista = new Artista("Artista Original", 1200.0, utilidadesArtista.convertStringToLocalDate("2000-07-20"));
        dao.agregarArtista(artista);

        Artista nuevoArtista = new Artista("Artista Actualizado", 1400.0, utilidadesArtista.convertStringToLocalDate("1999-09-09"));
        boolean actualizado = dao.actualizarArtista("Artista Original", nuevoArtista);

        assertTrue(actualizado);
        assertFalse(dao.tieneArtista("Artista Original"));
        assertTrue(dao.tieneArtista("Artista Actualizado"));
    }

    @Test
    public void testEliminarTodosArtistas() throws ExcepcionGestorArtista, RegistroDuplicado {
        dao.agregarArtista(new Artista("Artista 1", 1000.0, utilidadesArtista.convertStringToLocalDate("1990-01-01")));
        dao.agregarArtista(new Artista("Artista 2", 1500.0, utilidadesArtista.convertStringToLocalDate("1980-12-12")));

        dao.eliminarTodosArtistas();
        List<Artista> artistas = dao.mostrarArtistas();
        assertTrue(artistas.isEmpty());
    }
}
