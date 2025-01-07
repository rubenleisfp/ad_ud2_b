package ad.ad_ud2_b.practicas.practica306_starter;


import ad.ad_ud2_b.practicas.practica306_starter.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306_starter.dao.impl.ArtistaDaoFichero;
import ad.ad_ud2_b.practicas.practica306_starter.dao.impl.ArtistaDaoJdbc;
import ad.ad_ud2_b.practicas.practica306_starter.dao.impl.ArtistaDaoMemoria;
import ad.ad_ud2_b.practicas.practica306_starter.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306_starter.exceptions.RegistroDuplicado;
import ad.ad_ud2_b.practicas.practica306_starter.model.Artista;
import ad.ad_ud2_b.practicas.practica306_starter.service.ArtistaService;
import ad.ad_ud2_b.practicas.practica306_starter.utils.PropertiesHandler;
import ad.ad_ud2_b.practicas.practica306_starter.utils.UtilidadesArtista;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AppGestorArtistas {

    private static final String RUTA_FICHERO = "artistas.txt";
    private static final String TIPO_IMPLEMENTACION_FICHERO ="fichero";
    private static final String TIPO_IMPLEMENTACION_MEMORIA="memoria";
    private static final String TIPO_IMPLEMENTACION_BBDD="bbdd";
    private static final int END_NUMBER = 99;
    private ArtistaDao artistaDao;
    private ArtistaService artistaService;

    private UtilidadesArtista utilidades = new UtilidadesArtista();



    public static void main(String[] args) throws IOException {
        // De esta manera evitamos que el resto de métodos sean estáticos
        AppGestorArtistas appGestorArtistas = new AppGestorArtistas();
        appGestorArtistas.run();
    }

    /**
     * Método que contiene la funcionalidad de mi aplicación.
     */
    private void run() {
        Scanner scanner = new Scanner(System.in);
        cfgApp();
        try {
            menuOpciones(scanner);
        } catch (RegistroDuplicado | ExcepcionGestorArtista e) {
            System.out.println("Error al realizar la operativa");
            System.out.println(e);
        } finally {
            scanner.close();
        }
    }

    /**
     * Instancia los componentes necesarios de negocio y de acceso a datos.
     */
    private void cfgApp() {
        PropertiesHandler propertiesHandler = new PropertiesHandler();
        String tipoImplementacion = propertiesHandler.getPropertyString("tipoImplementacion");
        if (tipoImplementacion.equals(TIPO_IMPLEMENTACION_FICHERO)) {
            artistaDao = new ArtistaDaoFichero(RUTA_FICHERO);
        } else if (tipoImplementacion.equals(TIPO_IMPLEMENTACION_MEMORIA)) {
            artistaDao = new ArtistaDaoMemoria();
        } else if (tipoImplementacion.equals(TIPO_IMPLEMENTACION_BBDD)) {
            artistaDao = new ArtistaDaoJdbc();
        } else {
            throw new IllegalStateException("Implementacion no indicada en el fichero de configuracion de la app. Revise la misma.");
        }
        artistaService = new ArtistaService(artistaDao);
    }

    /**
     * Contiene un menú con todas las opciones sugeridas al usuario para que pueda interactuar con la app.
     *
     * @param scanner
     * @throws RegistroDuplicado
     * @throws ExcepcionGestorArtista
     */
    private void menuOpciones(Scanner scanner) throws RegistroDuplicado, ExcepcionGestorArtista {
        int opcion;
        do {
            System.out.println("1. Agregar artista");
            System.out.println("2. Consultar artistas");
            System.out.println("3. Tiene artista");
            System.out.println("4. Obtener artista");
            System.out.println("5. Actualizar artista");
            System.out.println("6. Eliminar artista");
            System.out.println("7. Eliminar todos los artistas");
            System.out.println("99. Salir del programa");
            System.out.print("Introduzca una opción: ");
            opcion = scanner.nextInt();

            scanner.nextLine(); // Limpiar el buffer después de leer un entero
            Artista artista = null;
            String nombre = "";
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese los datos del nuevo artista:");
                    artista = getDatosArtista(scanner);
                    if (artistaService.tieneArtista(artista.getNombre())) {
                        System.out.println("El artista indica ya existe");
                    } else {
                        artistaService.agregarArtista(artista);
                        System.out.println("Artista agregado correctamente.");
                    }
                    break;
                case 2:
                    System.out.println("Artistas guardadas en la agenda:");
                    List<Artista> artistas = artistaService.mostrarArtistas();
                    for (Artista art : artistas) {
                        System.out.println(art);
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el nombre del artista para saber si existe:");
                    nombre = scanner.nextLine();
                    System.out.println(artistaService.tieneArtista(nombre));
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del artista para obtenerlo:");
                    nombre = scanner.nextLine();
                    System.out.println(artistaService.getArtista(nombre));
                    break;
                case 5:
                    System.out.println("Ingrese el nombre del artista cuya informacion queremos actualizar:");
                    nombre = scanner.nextLine();
                    if (artistaService.tieneArtista(nombre)) {
                        Artista nuevoArtista = getDatosArtista(scanner);
                        artistaService.actualizarArtista(nombre,nuevoArtista);
                    } else {
                        System.out.println("No hay artistas con ese nombre. No se puede actualizar");
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el nombre del artista que desea eliminar:");
                    nombre = scanner.nextLine(); // Leer el nombre del artista a eliminar
                    if (artistaService.tieneArtista(nombre)) {
                        boolean artistaEliminado = artistaService.eliminarArtista(nombre);
                        if (artistaEliminado) {
                            System.out.println("Artista eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el artista.");
                        }
                    } else {
                        System.out.println("No hay registro para ese artista. No se puede eliminar.");
                    }
                    break;
                case 7:
                    System.out.println("Se procede a eliminar todos los registros.");
                    artistaService.eliminarTodosArtistas();
                    System.out.println("Todos los artistas han sido eliminados.");
                    break;
                case 99:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != END_NUMBER);
    }

    /**
     * Codigo repetitivo para solicitar datos completos de un artista
     *
     * @param scanner
     * @return
     */
    private Artista getDatosArtista(Scanner scanner) {
        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine(); // Leer el nombre del artista
        System.out.println("Ingrese el salario :");
        String salario = scanner.nextLine(); // Leer el salario del artista
        System.out.println("Fecha/Hora de nacimiento (yyyy-MM-dd):");
        String fechaNacimiento = scanner.nextLine(); // Leer la fecha del artista

        Artista artista = new Artista();
        artista.setNombre(nombre);
        artista.setSalario(Double.parseDouble(salario));
        artista.setFechaNacimiento(utilidades.convertStringToLocalDate(fechaNacimiento));

        return artista;
    }
}