package ad.ad_ud2_b.practicas.practica306.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {

	private Properties configuracion;

	// Constructor por defecto que usa una ruta predefinida
	public PropertiesHandler() {
		this("src/main/resources/practicas/practica306/conf.properties");
	}

	// Constructor que recibe una ruta de archivo de propiedades
	public PropertiesHandler(String propertiesFilePath) {
		loadProperties(propertiesFilePath);
	}

	private void loadProperties(String propertiesFilePath) {
		configuracion = new Properties();
		try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
			configuracion.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar el archivo de propiedades: " + propertiesFilePath, e);
		}
	}

	public String getPropertyString(String prop) {
		return configuracion.getProperty(prop);
	}



	// Método opcional para cambiar las propiedades después de la creación del objeto
	public void reloadProperties(String propertiesFilePath) {
		loadProperties(propertiesFilePath);
	}
}
