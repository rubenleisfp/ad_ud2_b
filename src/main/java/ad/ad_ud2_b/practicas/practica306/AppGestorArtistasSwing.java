package ad.ad_ud2_b.practicas.practica306;

import ad.ad_ud2_b.practicas.practica306.dao.ArtistaDao;
import ad.ad_ud2_b.practicas.practica306.dao.impl.ArtistaDaoJdbc;
import ad.ad_ud2_b.practicas.practica306.exceptions.ExcepcionGestorArtista;
import ad.ad_ud2_b.practicas.practica306.exceptions.RegistroDuplicado;
import ad.ad_ud2_b.practicas.practica306.model.Artista;
import ad.ad_ud2_b.practicas.practica306.service.ArtistaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppGestorArtistasSwing extends JFrame {

    private ArtistaService artistaService;
    private JTable tablaArtistas;
    private DefaultTableModel modeloTabla;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AppGestorArtistasSwing() {
        setTitle("Gestor de Artistas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemAgregar = new JMenuItem("Agregar Artista");
        JMenuItem menuItemEliminar = new JMenuItem("Eliminar Artista");
        JMenuItem menuItemActualizar = new JMenuItem("Actualizar Artista");
        JMenuItem menuItemEliminarTodos = new JMenuItem("Eliminar Todos");
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        menuArchivo.add(menuItemAgregar);
        menuArchivo.add(menuItemEliminar);
        menuArchivo.add(menuItemActualizar);
        menuArchivo.add(menuItemEliminarTodos);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        JPanel panelCentral = new JPanel(new BorderLayout());
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Salario", "Fecha Nacimiento"}, 0);
        tablaArtistas = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaArtistas);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Cargar Artistas");
        panelCentral.add(btnActualizar, BorderLayout.SOUTH);

        add(panelCentral);

        menuItemAgregar.addActionListener(e -> mostrarFormularioAgregar());
        menuItemEliminar.addActionListener(e -> eliminarArtista());
        menuItemActualizar.addActionListener(e -> mostrarFormularioActualizar());
        menuItemEliminarTodos.addActionListener(e -> eliminarTodosArtistas());
        menuItemSalir.addActionListener(e -> System.exit(0));

        btnActualizar.addActionListener(e -> cargarArtistasEnTabla());
    }

    private void mostrarFormularioAgregar() {
        JFrame formulario = new JFrame("Agregar Artista");
        formulario.setSize(300, 200);
        formulario.setLocationRelativeTo(this);
        formulario.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField txtNombre = new JTextField();
        JTextField txtSalario = new JTextField();
        JTextField txtFechaNacimiento = new JTextField();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Salario:"));
        formulario.add(txtSalario);
        formulario.add(new JLabel("Fecha de Nacimiento (yyyy-MM-dd):"));
        formulario.add(txtFechaNacimiento);
        formulario.add(btnGuardar);
        formulario.add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            try {
                double salario = Double.parseDouble(txtSalario.getText());
                LocalDate fechaNacimiento = LocalDate.parse(txtFechaNacimiento.getText(), FORMATTER);
                Artista nuevoArtista = new Artista(nombre, salario, fechaNacimiento);
                artistaService.agregarArtista(nuevoArtista);
                JOptionPane.showMessageDialog(formulario, "Artista agregado con éxito.");
                formulario.dispose();
                cargarArtistasEnTabla();
            } catch (RegistroDuplicado ex) {
                JOptionPane.showMessageDialog(formulario, "Error: " + ex.getMessage(), "Registro Duplicado", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formulario, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> formulario.dispose());

        formulario.setVisible(true);
    }

    private void eliminarArtista() {
        int filaSeleccionada = tablaArtistas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
            try {
                boolean eliminado = artistaService.eliminarArtista(nombre);
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Artista eliminado con éxito.");
                    cargarArtistasEnTabla();
                }
            } catch (ExcepcionGestorArtista ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un artista para eliminar.");
        }
    }

    private void mostrarFormularioActualizar() {
        int filaSeleccionada = tablaArtistas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombreActual = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

            JFrame formulario = new JFrame("Actualizar Artista");
            formulario.setSize(300, 200);
            formulario.setLocationRelativeTo(this);
            formulario.setLayout(new GridLayout(4, 2, 10, 10));

            JTextField txtNuevoNombre = new JTextField();
            JTextField txtNuevoSalario = new JTextField();
            JTextField txtNuevaFechaNacimiento = new JTextField();
            JButton btnActualizar = new JButton("Actualizar");
            JButton btnCancelar = new JButton("Cancelar");

            formulario.add(new JLabel("Nuevo Nombre:"));
            formulario.add(txtNuevoNombre);
            formulario.add(new JLabel("Nuevo Salario:"));
            formulario.add(txtNuevoSalario);
            formulario.add(new JLabel("Nueva Fecha de Nacimiento (yyyy-MM-dd):"));
            formulario.add(txtNuevaFechaNacimiento);
            formulario.add(btnActualizar);
            formulario.add(btnCancelar);

            btnActualizar.addActionListener(e -> {
                try {
                    String nuevoNombre = txtNuevoNombre.getText();
                    double nuevoSalario = Double.parseDouble(txtNuevoSalario.getText());
                    LocalDate nuevaFechaNacimiento = LocalDate.parse(txtNuevaFechaNacimiento.getText(), FORMATTER);

                    Artista nuevoArtista = new Artista(nuevoNombre, nuevoSalario, nuevaFechaNacimiento);
                    boolean actualizado = artistaService.actualizarArtista(nombreActual, nuevoArtista);
                    if (actualizado) {
                        JOptionPane.showMessageDialog(formulario, "Artista actualizado con éxito.");
                        formulario.dispose();
                        cargarArtistasEnTabla();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formulario, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnCancelar.addActionListener(e -> formulario.dispose());

            formulario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un artista para actualizar.");
        }
    }

    private void eliminarTodosArtistas() {
        try {
            artistaService.eliminarTodosArtistas();
            JOptionPane.showMessageDialog(this, "Todos los artistas fueron eliminados.");
            cargarArtistasEnTabla();
        } catch (ExcepcionGestorArtista ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarArtistasEnTabla() {
        modeloTabla.setRowCount(0);
        try {
            List<Artista> artistas = artistaService.mostrarArtistas();
            for (Artista artista : artistas) {
                modeloTabla.addRow(new Object[]{
                        artista.getNombre(),
                        artista.getSalario(),
                        artista.getFechaNacimiento().toString()
                });
            }
        } catch (ExcepcionGestorArtista ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        AppGestorArtistasSwing gestorArtistasFrame = new AppGestorArtistasSwing();
        gestorArtistasFrame.cfg();
        SwingUtilities.invokeLater(() -> gestorArtistasFrame.setVisible(true));
    }

    private void cfg() {
        ArtistaDao artistaDao = new ArtistaDaoJdbc();
        artistaService = new ArtistaService(artistaDao);
    }
}
