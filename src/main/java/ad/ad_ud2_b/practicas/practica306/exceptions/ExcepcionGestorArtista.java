package ad.ad_ud2_b.practicas.practica306.exceptions;

/**
 * Excepcion generica usada para cualquier tipo de anomalia en la BBDD
 */
public class ExcepcionGestorArtista extends Exception {
    public ExcepcionGestorArtista(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ExcepcionGestorArtista(String message) {
        super(message);
    }
}