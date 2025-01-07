package ad.ad_ud2_b.practicas.practica306_starter.dao.impl;


import ad.ad_ud2_b.practicas.practica306_starter.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306_starter.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306_starter.model.Artista;

import java.sql.*;
import java.util.List;

public class ArtistaDaoJdbc implements ArtistaDao {

    private static final String CREATE_ARTISTA= "INSERT INTO artista ( nombre, salario, fecha_nacimiento) " + "	VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM artista";

    @Override
    public void agregarArtista(Artista artista) throws  ExcepcionGestorArtista {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    @Override
    public List<Artista> mostrarArtistas() throws ExcepcionGestorArtista {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    @Override
    public boolean tieneArtista(String nombre) throws ExcepcionGestorArtista {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    @Override
    public Artista getArtista(String nombre) throws ExcepcionGestorArtista {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    @Override
    public boolean eliminarArtista(String nombre) throws ExcepcionGestorArtista {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    @Override
    public boolean actualizarArtista(String nombre, Artista nuevoArtista) throws ExcepcionGestorArtista {
       throw new UnsupportedOperationException("A implementar por el alumno");

    }

    @Override
    public void eliminarTodosArtistas() throws ExcepcionGestorArtista {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    private Artista mapResultSet(ResultSet resultSet) throws SQLException {
        String nombreArtista = resultSet.getString("nombre");
        double salario = resultSet.getDouble("salario");
        java.sql.Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");

        Artista artista = new Artista();
        artista.setNombre(nombreArtista);
        artista.setSalario(salario);
        artista.setFechaNacimiento( fechaNacimiento.toLocalDate());

        return artista;
    }
}
