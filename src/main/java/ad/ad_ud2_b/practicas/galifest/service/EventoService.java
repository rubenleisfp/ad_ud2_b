package ad.ad_ud2_b.practicas.galifest.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import ad.ad_ud2_b.practicas.galifest.repository.model.Evento;
import ad.ad_ud2_b.practicas.galifest.service.dto.EventoDto;

public interface EventoService {

    /**
     * Obtiene todos los eventos en formato JSON.
     * 
     * @return Una cadena que contiene los eventos en formato JSON.
     * @throws Exception Si ocurre un error al obtener los eventos o al convertirlos a JSON.
     */
    String getJson() throws Exception;
    
    /**
     * Obtiene todos los eventos en formato JSON.
     * 
     * @return Una cadena que contiene los eventos en formato JSON.
     * @throws Exception Si ocurre un error al obtener los eventos o al convertirlos a JSON.
     */
    String getJsonDto() throws Exception;
    
    
    List<EventoDto> toDtos(List<Evento> eventos);
    
    EventoDto toDto(Evento evento);


    /**
     * Escribe un archivo de texto con los eventos cuyo precio de coste sea mayor o igual al mínimo especificado.
     * 
     * @param txtFile El archivo de salida donde se escribirán los eventos.
     * @param minimumCost El coste mínimo para filtrar los eventos.
     * @throws Exception Si ocurre un error al obtener los eventos o al escribir el archivo.
     */
    void writeHaciendaFile(File txtFile, double minimumCost) throws Exception;

    /**
     * Obtiene las líneas de texto que serán incluidas en el archivo para Hacienda.
     * 
     * @param eventos La lista de eventos a procesar.
     * @return Una lista de cadenas de texto, cada una representando un evento en el formato requerido.
     */
    List<String> getLinesToHacienda(List<Evento> eventos);

    /**
     * Mapea una línea de texto a un objeto Evento.
     * 
     * @param line La línea de texto que representa un evento.
     * @return Un objeto Evento mapeado desde la línea de texto.
     * @throws ParseException Si ocurre un error al analizar la línea de texto.
     */
    Evento mapLineToEvento(String line) throws ParseException;

    /**
     * Lee eventos desde un archivo CSV y los convierte en una lista de objetos Evento.
     * 
     * @param inputCsv El archivo CSV de entrada.
     * @return Una lista de objetos Evento leídos del archivo CSV.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws ParseException Si ocurre un error al analizar las líneas del archivo.
     */
    List<Evento> readFileToEvents(File inputCsv) throws IOException, ParseException;

    /**
     * Convierte una lista de líneas de texto en una lista de objetos Evento.
     * 
     * @param lines La lista de líneas de texto que representan eventos.
     * @return Una lista de objetos Evento mapeados desde las líneas de texto.
     * @throws ParseException Si ocurre un error al analizar las líneas de texto.
     */
    List<Evento> mapLinesToEventos(List<String> lines) throws ParseException;

    
    /**
     * Vuelca la informacion de un CSV en nuestra base de datos
     * 
     * @param inputCsv fichero donde se encuentra los eventos
     * @throws Exception
     */
	void writeCsvToDatabase(File inputCsv) throws Exception;

}
