package ad.ad_ud2_b.employee_crud.repository.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Trabaja con BufferedReader y BufferedWriter
 */
public class GestorFicheroBuffered {

	/**
	 * Lee un fichero y vuelva las lineas en una lista
	 * @param nombreFichero 
	 * @return
	 * @throws IOException
	 */
	public static List<String> readLines(String nombreFichero) throws IOException {
		// Con try resources no tenemos que cerrar los recursos, Java ya lo hace por
		// nosotros
		List<String> lineas = new ArrayList<String>();
		try (FileReader fr = new FileReader(nombreFichero); BufferedReader br = new BufferedReader(fr)) {
			String linea;
			while ((linea = br.readLine()) != null) {
				
				lineas.add(linea);
			}
		}
		return lineas;
	}

	/**
	 * Recibe una lista de lineas que desea volcar a fichero
	 * 
	 * @param filePath ruta del fichero
	 * @param append TRUE si va a añadir lineas al final, FALSE si sobreescribe el fichero
	 * @param lines lista de lineas a volcar en fichero
	 * @throws IOException
	 */
	public static void writeLines(String filePath, boolean append, List<String> lines) throws IOException {
		//Si el fichero no existe lo creamos
		Path path = Paths.get(filePath).getParent();
		if (path != null && !Files.exists(path)) {
			Files.createDirectories(path);
		}

		// Con try resources no tenemos que cerrar los recursos, Java ya lo hace por
		// nosotros
		try (FileWriter fw = new FileWriter(filePath, append); BufferedWriter bw = new BufferedWriter(fw)) {
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
		}
	}
	
	public static void printFileToConsole(String nombreFichero) throws IOException {
		List<String> lines = readLines(nombreFichero);
		for (String line: lines) {
			System.out.println(line);
		}
	}
}
