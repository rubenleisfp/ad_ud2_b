package ad.ad_ud2_b.practicas.galifest.repository.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento {
    private String nombreDelEvento;
    private Date fechaHora;
    private String ubicacion;
    private String descripcion;
    private double precioDeCoste;

    // Formato de fecha y hora
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // Constructor
    public Evento(String nombreDelEvento, String fechaHoraStr, String ubicacion, String descripcion, double precioDeCoste) throws ParseException {
        this.nombreDelEvento = nombreDelEvento;
        this.fechaHora = dateFormat.parse(fechaHoraStr);
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.precioDeCoste = precioDeCoste;
    }
    
    public Evento() {
    	
    }

    // Getters y Setters
    public String getNombreDelEvento() {
        return nombreDelEvento;
    }

    public void setNombreDelEvento(String nombreDelEvento) {
        this.nombreDelEvento = nombreDelEvento;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHoraStr) throws ParseException {
        this.fechaHora = dateFormat.parse(fechaHoraStr);
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioDeCoste() {
        return precioDeCoste;
    }

    public void setPrecioDeCoste(double precioDeCoste) {
        this.precioDeCoste = precioDeCoste;
    }

    // Método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        return "Evento{" +
                "nombreDelEvento='" + nombreDelEvento + '\'' +
                ", fechaHora=" + dateFormat.format(fechaHora) +
                ", ubicacion='" + ubicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioDeCoste=" + precioDeCoste +
                '}';
    }
}
