package ad.ad_ud2_b.practicas.practica306.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class GestorBufferedTest {

    private String testFilePath = "testFile.txt";
    private File testFile;

    @Before
    public void setUp() throws Exception {
        testFile = new File(testFilePath);

        // Limpiamos el fichero de prueba antes de cada test
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @After
    public void tearDown() throws Exception {
        // Eliminar el fichero después de cada test
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testReadFileNoFileExists() throws IOException {
        // Test para leer cuando el fichero no existe
        List<String> result = GestorBuffered.read(testFilePath);

        assertTrue(result.isEmpty()); // Debe devolver una lista vacía
    }

    @Test
    public void testWriteLinesAppendFalse() throws IOException {
        // Test para escribir líneas en el fichero (sin append)
        List<String> lines = Arrays.asList("Línea 1", "Línea 2", "Línea 3");
        GestorBuffered.writeLines(testFilePath, false, lines);

        List<String> result = GestorBuffered.read(testFilePath);

        assertEquals(3, result.size());
        assertEquals("Línea 1", result.get(0));
        assertEquals("Línea 2", result.get(1));
        assertEquals("Línea 3", result.get(2));
    }

    @Test
    public void testWriteLinesAppendTrue() throws IOException {
        // Test para escribir líneas en el fichero (con append)
        List<String> lines1 = Arrays.asList("Línea 1", "Línea 2");
        List<String> lines2 = Arrays.asList("Línea 3", "Línea 4");

        // Primero escribe sin append
        GestorBuffered.writeLines(testFilePath, false, lines1);
        // Ahora escribe con append
        GestorBuffered.writeLines(testFilePath, true, lines2);

        List<String> result = GestorBuffered.read(testFilePath);

        assertEquals(4, result.size());
        assertEquals("Línea 1", result.get(0));
        assertEquals("Línea 2", result.get(1));
        assertEquals("Línea 3", result.get(2));
        assertEquals("Línea 4", result.get(3));
    }

    @Test
    public void testReadFileFileExists() throws IOException {
        // Test para leer líneas cuando el fichero ya tiene contenido
        List<String> lines = Arrays.asList("Línea 1", "Línea 2", "Línea 3");
        GestorBuffered.writeLines(testFilePath, false, lines);

        List<String> result = GestorBuffered.read(testFilePath);

        assertEquals(3, result.size());
        assertEquals("Línea 1", result.get(0));
        assertEquals("Línea 2", result.get(1));
        assertEquals("Línea 3", result.get(2));
    }
}