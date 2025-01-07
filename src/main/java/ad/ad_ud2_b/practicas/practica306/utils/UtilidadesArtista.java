package ad.ad_ud2_b.practicas.practica306.utils;




import ad.ad_ud2_b.practicas.practica306.model.Artista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UtilidadesArtista {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate convertStringToLocalDate(String fechaHora) {
        // String str = "1986-04-08 12:30";
        LocalDate date = LocalDate.parse(fechaHora, formatter);
        return date;
    }

    public String convertLocalDateString(LocalDate fecha) {
        // String str = "1986-04-08";
        String fechaStr = fecha.format(formatter);
        return fechaStr;

    }

    public List<String> getLinesFromArtistas(List<Artista> citas) {
        List<String> lines = new ArrayList<>();
        for (Artista artista: citas) {
            lines.add(getLineFromCita(artista));
        }
        return lines;
    }

    public List<Artista> getArtistasFromLines(List<String> lines) {
        List<Artista> citas = new ArrayList<>();
        for (String line: lines) {
            citas.add(getArtistasFromLine(line));
        }
        return citas;
    }

    public Artista getArtistasFromLine(String line) {
        String [] camposArtista  = line.split(";");
        Artista artista = new Artista();
        artista.setNombre(camposArtista[0]);
        artista.setSalario(Double.parseDouble(camposArtista[1]));
        artista.setFechaNacimiento(convertStringToLocalDate(camposArtista[2]));
        return artista;
    }

    public String getLineFromCita(Artista artista) {
        StringBuilder sb = new StringBuilder();
        sb.append(artista.getNombre());
        sb.append(";");
        sb.append(artista.getSalario());
        sb.append(";");
        String fechaFormateada = convertLocalDateString(artista.getFechaNacimiento());
        sb.append(fechaFormateada);
        return sb.toString();

    }
}