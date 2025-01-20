package ad.ad_ud2_b.practicas.practica306.dao.impl;


import ad.ad_ud2_b.ejemplo_dto.repository.impl.DriverHelper;
import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.model.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDaoJdbc implements ArtistaDao {

    private static final String CREATE_ARTISTA= "INSERT INTO artista ( nombre, salario, fecha_nacimiento) " + "	VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM artista";
    private static final String HAS_ARTIST = "SELECT count(*) FROM gestor_artistas.artista where nombre = ?";

    @Override
    public void agregarArtista(Artista artista) throws  ExcepcionGestorArtista {
        try(Connection connection = DriverHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(CREATE_ARTISTA);
            ps.setString(1, artista.getNombre());
            ps.setDouble(2, artista.getSalario());
            ps.setDate(3, Date.valueOf(artista.getFechaNacimiento()));
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new ExcepcionGestorArtista("Creacion del artista fallida, no se creo la fila");
            }

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error creando el artista:",e);
        }
    }

    @Override
    public List<Artista> mostrarArtistas() throws ExcepcionGestorArtista {
        List<Artista> artistas = new ArrayList<>();
        try(Connection connection = DriverHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Artista artista = mapResultSet(rs);
                artistas.add(artista);
            }
            return artistas;

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error leyendo los artistas:",e);
        }
    }

    @Override
    public boolean tieneArtista(String nombre) throws ExcepcionGestorArtista {

        try(Connection connection = DriverHelper.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(HAS_ARTIST);
           // ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();

           if (rs.next()) {
               int numeroApariciones = rs.getInt(1);
               return numeroApariciones >0;
           }
           return false;

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error en tiene artista:",e);
        }
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
