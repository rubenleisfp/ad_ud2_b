package ad.ad_ud2_b.practicas.practica306.model;

import java.time.LocalDate;
import java.util.Objects;

public class Artista {
    private String nombre;
    private double salario;
    private LocalDate fechaNacimiento;

    public Artista() {

    }

    public Artista(String nombre, double salario, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return Double.compare(salario, artista.salario) == 0 && Objects.equals(nombre, artista.nombre) && Objects.equals(fechaNacimiento, artista.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, salario, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
