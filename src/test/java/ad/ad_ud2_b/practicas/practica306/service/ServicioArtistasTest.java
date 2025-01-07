package ad.ad_ud2_b.practicas.practica306.service;


import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.exceptions.RegistroDuplicado;
import ad.ad_ud2_b.practicas.practica306.model.Artista;
import ad.ad_ud2_b.practicas.practica306.utils.UtilidadesArtista;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ServicioArtistasTest {

    private ArtistaDao artistaDaoMock;
    private ArtistaService artistaService;

    private UtilidadesArtista utilidadesArtista = new UtilidadesArtista();

    @Before
    public void setUp() {

        artistaDaoMock = mock(ArtistaDao.class);
        artistaService = new ArtistaService(artistaDaoMock);
    }

    @Test
    public void agregarArtista_exito() throws Exception {
        Artista artista = new Artista("Artista1", 1000.0, utilidadesArtista.convertStringToLocalDate("1980-01-01"));
        doNothing().when(artistaDaoMock).agregarArtista(artista);

        artistaService.agregarArtista(artista);

        verify(artistaDaoMock, times(1)).agregarArtista(artista);
    }

    @Test(expected = RegistroDuplicado.class)
    public void agregarArtista_registroDuplicado() throws Exception {
        Artista artista = new Artista("Artista1", 1000.0, utilidadesArtista.convertStringToLocalDate("1980-01-01"));
        when(artistaDaoMock.tieneArtista(artista.getNombre())).thenReturn(true);

        artistaService.agregarArtista(artista);
    }

    @Test
    public void mostrarArtistas_exito() throws Exception {
        List<Artista> artistas = Arrays.asList(
                new Artista("Artista1", 1000.0, utilidadesArtista.convertStringToLocalDate("1980-01-01")),
                new Artista("Artista2", 2000.0, utilidadesArtista.convertStringToLocalDate("1990-01-01"))
        );
        when(artistaDaoMock.mostrarArtistas()).thenReturn(artistas);

        List<Artista> resultado = artistaService.mostrarArtistas();

        assertEquals(2, resultado.size());
        assertEquals("Artista1", resultado.get(0).getNombre());
        verify(artistaDaoMock, times(1)).mostrarArtistas();
    }

    @Test
    public void tieneArtista_artistaExiste() throws Exception {
        String nombreArtista = "Artista1";
        when(artistaDaoMock.tieneArtista(nombreArtista)).thenReturn(true);

        boolean resultado = artistaService.tieneArtista(nombreArtista);

        assertTrue(resultado);
        verify(artistaDaoMock, times(1)).tieneArtista(nombreArtista);
    }

    @Test
    public void eliminarArtista_exito() throws Exception {
        String nombreArtista = "Artista1";
        when(artistaDaoMock.eliminarArtista(nombreArtista)).thenReturn(true);

        boolean resultado = artistaService.eliminarArtista(nombreArtista);

        assertTrue(resultado);
        verify(artistaDaoMock, times(1)).eliminarArtista(nombreArtista);
    }

    @Test
    public void actualizarArtista_exito() throws Exception {
        String nombreActual = "Artista1";
        Artista nuevoArtista = new Artista("Artista2", 1500.0, utilidadesArtista.convertStringToLocalDate("1985-01-01"));
        when(artistaDaoMock.actualizarArtista(nombreActual, nuevoArtista)).thenReturn(true);

        boolean resultado = artistaService.actualizarArtista(nombreActual, nuevoArtista);

        assertTrue(resultado);
        verify(artistaDaoMock, times(1)).actualizarArtista(nombreActual, nuevoArtista);
    }

    @Test
    public void eliminarTodosArtistas_exito() throws Exception {
        doNothing().when(artistaDaoMock).eliminarTodosArtistas();

        artistaService.eliminarTodosArtistas();

        verify(artistaDaoMock, times(1)).eliminarTodosArtistas();
    }
}
