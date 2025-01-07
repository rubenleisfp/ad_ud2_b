package ad.ad_ud2_b.practicas.galifest.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar el acceso y escritura a fichero
 * 
 * Trabaja con BufferedReader y BufferedWriter
 */
public class GestorBuffered {

	/**
	 * Lee un fichero y devuelve las lineas en una lista
	 * 
	 * @param file fichero que de desea devolver
	 * @return
	 * @throws IOException
	 */
	public static List<String> read(File file) throws IOException {
		// Con try resources no tenemos que cerrar los recursos, Java ya lo hace por
		// nosotros
		List<String> lineas = new ArrayList<String>();
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
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
	 * @param file			File sobre el que se va a escribir el fichero
	 * @param append        TRUE si va a añadir lineas al final, FALSE si
	 *                      sobreescribe el fichero
	 * @param lines         lista de lineas a volcar en fichero
	 * @throws IOException
	 */
	public static void writeLines(File file, boolean append, List<String> lines) throws IOException {
		// Con try resources no tenemos que cerrar los recursos, Java ya lo hace por
		// nosotros
		try (FileWriter fw = new FileWriter(file, append); BufferedWriter bw = new BufferedWriter(fw)) {
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
		}
	}

}
