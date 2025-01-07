package ad.ad_ud2_b.practicas.practica306.dao.impl;


import ad.ad_ud2_b.practicas.practica306.model.Artista;
import ad.ad_ud2_b.practicas.practica306.utils.UtilidadesArtista;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ArtistaDaoMemoriaTest {

    private ArtistaDaoMemoria daoArtistas;
    private final UtilidadesArtista utilidadesArtista = new UtilidadesArtista();

    @Before
    public void setUp() {
        daoArtistas = new ArtistaDaoMemoria();
    }

    @Test
    public void testAgregarArtista() throws Exception {
        Artista artista = new Artista("Pablo Picasso", 50000, utilidadesArtista.convertStringToLocalDate("1881-10-25"));
        daoArtistas.agregarArtista(artista);
        assertTrue(daoArtistas.tieneArtista("Pablo Picasso"));
    }

    @Test
    public void testMostrarArtistas() throws Exception {
        Artista artista1 = new Artista("Van Gogh", 40000, utilidadesArtista.convertStringToLocalDate("1853-03-30"));
        Artista artista2 = new Artista("Leonardo da Vinci", 70000, utilidadesArtista.convertStringToLocalDate("1452-04-15"));
        daoArtistas.agregarArtista(artista1);
        daoArtistas.agregarArtista(artista2);
        List<Artista> artistas = daoArtistas.mostrarArtistas();
        assertEquals(2, artistas.size());
    }

    @Test
    public void testGetArtistaExistente() throws Exception {
        Artista artista = new Artista("Claude Monet", 55000, utilidadesArtista.convertStringToLocalDate("1840-11-14"));
        daoArtistas.agregarArtista(artista);
        Artista resultado = daoArtistas.getArtista("Claude Monet");
        assertEquals(artista, resultado);
    }

    @Test
    public void testGetArtistaInexistente() throws Exception {
        Artista resultado = daoArtistas.getArtista("Artista Desconocido");
        assertNull(resultado);
    }

    @Test
    public void testEliminarArtistaExistente() throws Exception {
        Artista artista = new Artista("Salvador Dalí", 60000, utilidadesArtista.convertStringToLocalDate("1904-05-11"));
        daoArtistas.agregarArtista(artista);
        assertTrue(daoArtistas.eliminarArtista("Salvador Dalí"));
        assertFalse(daoArtistas.tieneArtista("Salvador Dalí"));
    }

    @Test
    public void testEliminarArtistaInexistente() throws Exception {
        assertFalse(daoArtistas.eliminarArtista("Artista Inexistente"));
    }

    @Test
    public void testActualizarArtistaExistente() throws Exception {
        Artista artista = new Artista("Edvard Munch", 35000, utilidadesArtista.convertStringToLocalDate("1863-12-12"));
        daoArtistas.agregarArtista(artista);
        Artista nuevoArtista = new Artista("Edvard Munch", 40000, utilidadesArtista.convertStringToLocalDate("1863-12-12"));
        assertTrue(daoArtistas.actualizarArtista("Edvard Munch", nuevoArtista));
        Artista actualizado = daoArtistas.getArtista("Edvard Munch");
        assertEquals(nuevoArtista.getSalario(), actualizado.getSalario(),0.0001);
    }

    @Test
    public void testActualizarArtistaInexistente() throws Exception {
        Artista nuevoArtista = new Artista("Artista Fantasma", 30000, utilidadesArtista.convertStringToLocalDate("2000-01-01"));
        assertFalse(daoArtistas.actualizarArtista("Artista Inexistente", nuevoArtista));
    }

    @Test
    public void testEliminarTodosArtistas() throws Exception {
        daoArtistas.agregarArtista(new Artista("Gustav Klimt", 45000, utilidadesArtista.convertStringToLocalDate("1862-07-14")));
        daoArtistas.agregarArtista(new Artista("Jackson Pollock", 50000, utilidadesArtista.convertStringToLocalDate("1912-01-28")));
        daoArtistas.eliminarTodosArtistas();
        assertTrue(daoArtistas.mostrarArtistas().isEmpty());
    }
}
