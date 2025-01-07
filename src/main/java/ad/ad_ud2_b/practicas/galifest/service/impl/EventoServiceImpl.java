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
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public List<Evento> mapLinesToEventos(List<String> lines) throws ParseException {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public Evento mapLineToEvento(String line) throws ParseException {

		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}
	
	@Override
	public List<Evento> readFileToEvents(File inputCsv) throws IOException, ParseException {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public void writeCsvToDatabase(File inputCsv) throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public void writeHaciendaFile(File txtFile, double minimumCost) throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public String getJson() throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");

	}

	@Override
	public String getJsonDto() throws Exception {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public EventoDto toDto(Evento evento) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

	@Override
	public List<EventoDto> toDtos(List<Evento> eventos) {
		throw new UnsupportedOperationException("Operacion a implementar por el alumno");
	}

}
