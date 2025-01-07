package ad.ad_ud2_b.practicas.practica306_starter.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Trabaja con BufferedReader y BufferedWriter
 */
public class GestorBuffered {

	/**
	 * Lee un fichero y vuelca las lineas en una lista.
	 * Si el fichero no existe lo crea y devuelve una lista vacía.
	 *
	 * @param nombreFichero
	 * @return lista de líneas leídas
	 * @throws IOException
	 */
	public static List<String> read(String nombreFichero) throws IOException {
		//TODO
		// 1.- Si el fichero no existe crearlo y devolvere una lista vacia
		// 2.- Leer el fichero y volcarlo en una lista de String

		List<String> lineas = new ArrayList<>();
		File file = new File(nombreFichero);

		// Verifica si el fichero existe
		if (!file.exists()) {
			return lineas; // Devuelve una lista vacía si no existe
		}

		// Si existe, procede con la lectura
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String linea;
			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		}
		return lineas;
	}

	/**
	 * Si el fichero no existe, lo crea
	 *
	 * Vuelca las lineas recibidas como argumento al fichero, teniendo en cuenta
	 * el append para saber si tiene que sobreescribir o escribir desde cero.
	 *
	 * @param nombreFichero
	 * @param append TRUE si va a añadir lineas al final, FALSE si sobreescribe el fichero
	 * @param lines lista de lineas a volcar en fichero
	 * @throws IOException
	 */
	public static void writeLines(String nombreFichero, boolean append, List<String> lines) throws IOException {
		//TODO
		// 1.- Si el fichero no existe crearlo
		// 2.- Volcar las lineas recibidas en el fichero, con el append recibido como argumento
		File file = new File(nombreFichero);

		// Si el fichero no existe, lo crea
		if (!file.exists()) {
			file.createNewFile();
		}

		try (FileWriter fw = new FileWriter(file, append); BufferedWriter bw = new BufferedWriter(fw)) {
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
		}
	}
}