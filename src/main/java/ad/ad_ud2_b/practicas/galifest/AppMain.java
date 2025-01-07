package ad.ad_ud2_b.practicas.galifest;

import java.io.File;
import java.util.Scanner;

import ad.ad_ud2_b.practicas.galifest.service.impl.EventoServiceImpl;

public class AppMain {

	private static final String CSV_INPUT_PATH = "src/main/resources/practicas/galifest/input.csv";
	private static final String HACIENDA_OUTPUT_PATH = "src/main/resources/practicas/galifest/hacienda.txt";
	private static final double MINIMUM_COST = 50;

	public static void main(String[] args) {
		EventoServiceImpl eventoService = new EventoServiceImpl();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Seleccione una opción:");
			System.out.println("J. Obtener eventos en formato JSON");
			System.out.println("D. Obtener eventos en formato JSON para los fumapuros de los directivos");
			System.out.println("C. Leer eventos desde un archivo CSV y guardarlos en BBDD.");
			System.out.println("H. Escribir archivo para Hacienda");
			System.out.println("Z. Salir");

			String option = scanner.nextLine();

			try {
				switch (option.toUpperCase()) {
				case "J":
					String json = eventoService.getJson();
					System.out.println("JSON Output:");
					System.out.println(json);
					break;
				case "D":
					String jsonDto = eventoService.getJsonDto();
					System.out.println("JSON DTO Output:");
					System.out.println(jsonDto);
					break;
				case "C":

					File inputCsv = new File(CSV_INPUT_PATH);
					eventoService.writeCsvToDatabase(inputCsv);
					System.out.println("Eventos guardados en base de datos");
					break;
				case "H":

					File outputTxt = new File(HACIENDA_OUTPUT_PATH);
					System.out.println("Ingrese el coste mínimo:");

					eventoService.writeHaciendaFile(outputTxt, MINIMUM_COST);
					System.out.println("Archivo para Hacienda escrito.");
					break;
				case "Z":
					System.out.println("Saliendo...");
					scanner.close();
					return;
				default:
					System.out.println("Opción no válida. Por favor, intente nuevamente.");
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
