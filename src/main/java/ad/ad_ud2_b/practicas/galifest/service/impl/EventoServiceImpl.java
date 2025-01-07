package ad.ad_ud2_b.practicas.galifest.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ad.ad_ud2_b.practicas.galifest.commons.utils.GestorBuffered;
import ad.ad_ud2_b.practicas.galifest.repository.EventoDao;
import ad.ad_ud2_b.practicas.galifest.repository.impl.EventoJdbcDao;
import ad.ad_ud2_b.practicas.galifest.repository.model.Evento;
import ad.ad_ud2_b.practicas.galifest.service.EventoService;
import ad.ad_ud2_b.practicas.galifest.service.dto.EventoDto;

public class EventoServiceImpl implements EventoService {

	private EventoDao eventoDao = new EventoJdbcDao();
	private Gson gson = new Gson();


	@Override
	public List<String> getLinesToHacienda(List<Evento> eventos) {
		List<String> lines = new ArrayList<String>();
		lines.add("CIF:T34895207");
		double acumulador = 0;
		for (Evento evento : eventos) {
			acumulador = acumulador + evento.getPrecioDeCoste();
		}
		lines.add("Precio coste total:" + acumulador);
		double precioAvg = acumulador / eventos.size();
		lines.add("Precio medio:" + precioAvg);
		return lines;
	}

	@Override
	public List<Evento> mapLinesToEventos(List<String> lines) throws ParseException {
		List<Evento> eventos = new ArrayList<Evento>();
		for (String line : lines) {
			Evento evento = mapLineToEvento(line);
			eventos.add(evento);
		}
		return eventos;
	}

	@Override
	public Evento mapLineToEvento(String line) throws ParseException {

		String[] fields = line.split(",");
		String nombre = fields[0];
		String fechaHora = fields[1];
		String ubicacion = fields[2];
		String descripcion = fields[3];
		String precioString = fields[4];
		double precio = Double.parseDouble(precioString);

		Evento evento = new Evento();
		evento.setNombreDelEvento(nombre);
		evento.setDescripcion(descripcion);
		evento.setFechaHora(fechaHora);
		evento.setUbicacion(ubicacion);
		evento.setDescripcion(descripcion);
		evento.setPrecioDeCoste(precio);

		return evento;
	}
	
	@Override
	public List<Evento> readFileToEvents(File inputCsv) throws IOException, ParseException {
		List<String> lines = GestorBuffered.read(inputCsv);
		lines.remove(0);
		List<Evento> eventos = this.mapLinesToEventos(lines);
		return eventos;
	}

	@Override
	public void writeCsvToDatabase(File inputCsv) throws Exception {
		List<Evento> eventos = readFileToEvents(inputCsv);
		for (Evento evento : eventos) {
			this.eventoDao.create(evento);
		}
	}

	@Override
	public void writeHaciendaFile(File txtFile, double minimumCost) throws Exception {
		List<Evento> eventos = eventoDao.getWithGreaterCost(minimumCost);
		List<String> linesToHacienda = getLinesToHacienda(eventos);
		GestorBuffered.writeLines(txtFile, false, linesToHacienda);
	}

	@Override
	public String getJson() throws Exception {
		List<Evento> eventos = eventoDao.getAll();
		String json = gson.toJson(eventos);
		return json;

	}

	@Override
	public String getJsonDto() throws Exception {
		List<Evento> eventos = eventoDao.getAll();
		List<EventoDto> dtos = this.toDtos(eventos);
		String json =gson.toJson(dtos);
		return json;
	}

	@Override
	public EventoDto toDto(Evento evento) {
		EventoDto dto = new EventoDto();
		dto.setNombreDelEvento(evento.getNombreDelEvento());
		dto.setPrecioDeCoste(evento.getPrecioDeCoste());
	
		return dto;
	}

	@Override
	public List<EventoDto> toDtos(List<Evento> eventos) {
		List<EventoDto> dtos = new ArrayList<>();
		for (Evento evento: eventos) {
			EventoDto dto = this.toDto(evento);
			dtos.add(dto);
		}
		return dtos;
	}

}
