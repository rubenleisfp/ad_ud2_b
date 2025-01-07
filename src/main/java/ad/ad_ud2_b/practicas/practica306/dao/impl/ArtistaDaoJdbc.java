package ad.ad_ud2_b.practicas.practica306.dao.impl;


import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.model.Artista;
import ad.ad_ud2_b.practicas.practica306.utils.DriverHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDaoJdbc implements ArtistaDao {

    private static final String CREATE_ARTISTA= "INSERT INTO artista ( nombre, salario, fecha_nacimiento) " + "	VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM artista";

    private static final String DELETE_BY_NAME = "DELETE FROM artista WHERE nombre = ?";
    private static final String UPDATE = "UPDATE artista SET nombre = ?, salario = ?, fecha_nacimiento = ? WHERE nombre = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM artista WHERE nombre = ?";

    @Override
    public void agregarArtista(Artista artista) throws  ExcepcionGestorArtista {
        try (Connection connection = DriverHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ARTISTA)) {

            preparedStatement.setString(1, artista.getNombre());
            preparedStatement.setDouble(2, artista.getSalario());
            preparedStatement.setDate(3, Date.valueOf(artista.getFechaNacimiento()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new ExcepcionGestorArtista("Creación del artista fallido, no se crearon filas.");
            }

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error al crear el evento.", e);
        }
    }

    @Override
    public List<Artista> mostrarArtistas() throws ExcepcionGestorArtista {
        List<Artista> artistas = new ArrayList<>();
        try (Connection conn = DriverHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Artista artista = mapResultSet(resultSet);
                artistas.add(artista);
            }

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista(e.getMessage());
        } catch (Exception e) {
            throw new ExcepcionGestorArtista(e.getMessage());
        }
        return artistas;
    }

    @Override
    public boolean tieneArtista(String nombre) throws ExcepcionGestorArtista {
        final String QUERY = "SELECT COUNT(*) FROM artista WHERE nombre = ?";
        try (Connection connection = DriverHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error al verificar si existe el artista.", e);
        }
    }

    @Override
    public Artista getArtista(String nombre) throws ExcepcionGestorArtista {

        try (Connection connection = DriverHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {

            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSet(resultSet);
            }
            return null;

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error al obtener el artista.", e);
        }
    }

    @Override
    public boolean eliminarArtista(String nombre) throws ExcepcionGestorArtista {

        try (Connection connection = DriverHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NAME)) {

            preparedStatement.setString(1, nombre);
            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error al eliminar el artista.", e);
        }
    }

    @Override
    public boolean actualizarArtista(String nombre, Artista nuevoArtista) throws ExcepcionGestorArtista {

        try (Connection connection = DriverHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, nuevoArtista.getNombre());
            preparedStatement.setDouble(2, nuevoArtista.getSalario());
            preparedStatement.setDate(3, Date.valueOf(nuevoArtista.getFechaNacimiento()));
            preparedStatement.setString(4, nombre);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error al actualizar el artista.", e);
        }
    }

    @Override
    public void eliminarTodosArtistas() throws ExcepcionGestorArtista {
        final String QUERY = "DELETE FROM artista";
        try (Connection connection = DriverHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ExcepcionGestorArtista("Error al eliminar todos los artistas.", e);
        }
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
