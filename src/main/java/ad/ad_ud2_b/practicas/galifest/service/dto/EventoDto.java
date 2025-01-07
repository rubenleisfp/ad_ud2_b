package ad.ad_ud2_b.practicas.galifest.service.dto;

public class EventoDto {
    private String nombreDelEvento;
    private double precioDeCoste;
    private boolean tieneDescripcion;
    
    
	public boolean isTieneDescripcion() {
		return tieneDescripcion;
	}
	public void setTieneDescripcion(boolean tieneDescripcion) {
		this.tieneDescripcion = tieneDescripcion;
	}
	public String getNombreDelEvento() {
		return nombreDelEvento;
	}
	public void setNombreDelEvento(String nombreDelEvento) {
		this.nombreDelEvento = nombreDelEvento;
	}
	public double getPrecioDeCoste() {
		return precioDeCoste;
	}
	public void setPrecioDeCoste(double precioDeCoste) {
		this.precioDeCoste = precioDeCoste;
	}
    
    
}
