package org.Kardex.jF.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GestionServicioView extends JFrame{

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   private JTextField campoId        = new JTextField();
	    private JTextField campoNombre    = new JTextField();
	    private JTextField campoDesc      = new JTextField();
	    private JTextField campoPrecio    = new JTextField();
	    private JComboBox<String> comboEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
	 
	    private JButton btnNuevo    = new JButton("Nuevo");
	    private JButton btnGuardar  = new JButton("Guardar");
	    private JButton btnEditar   = new JButton("Editar");
	    private JButton btnEliminar = new JButton("Eliminar");
	    private JButton btnBuscar   = new JButton("Buscar");
	    private JTable tabla;
	    
	    public GestionServicioView() {
	        setTitle("Gestión de Servicios");
	        setSize(520, 550);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout(10, 10));
	 
	        // ── Panel principal con margen ──────────────────────────────────
	        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
	        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
	 
	        // ── Título ──────────────────────────────────────────────────────
	        JLabel titulo = new JLabel("GESTIÓN DE SERVICIOS", SwingConstants.CENTER);
	        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
	        panelPrincipal.add(titulo, BorderLayout.NORTH);
	 
	        // ── Formulario (GridLayout 5 filas x 2 columnas) ────────────────
	        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 12));
	        panelForm.setBorder(new EmptyBorder(10, 0, 10, 0));
	 
	        panelForm.add(new JLabel("Código:"));
	        panelForm.add(campoId);
	 
	        panelForm.add(new JLabel("Nombre Servicio:"));
	        panelForm.add(campoNombre);
	 
	        panelForm.add(new JLabel("Descripción:"));
	        panelForm.add(campoDesc);
	 
	        panelForm.add(new JLabel("Precio:"));
	        panelForm.add(campoPrecio);
	 
	        panelForm.add(new JLabel("Estado:"));
	        panelForm.add(comboEstado);
	 
	        // ── Botones (2 filas) ───────────────────────────────────────────
	        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));

	        panelBotones.add(btnNuevo);
	        panelBotones.add(btnGuardar);
	        panelBotones.add(btnEditar);
	        panelBotones.add(btnEliminar);
	        panelBotones.add(btnBuscar);// celda vacía para completar la grilla
	 
	        // ── Tabla ───────────────────────────────────────────────────────
	        String[] columnas = {"Código", "Servicio", "Precio", "Estado"};
	        Object[][] datos = {
	            {"001", "Formateo",   "50.00", "Activo"},
	            {"002", "Limpieza",   "30.00", "Activo"},
	            {"003", "Cambio RAM", "40.00", "Activo"}
	        };
	        tabla = new JTable(datos, columnas);
	        JScrollPane scrollTabla = new JScrollPane(tabla);
	        scrollTabla.setPreferredSize(new Dimension(480, 150));
	 
	        // ── Ensamblado ──────────────────────────────────────────────────
	        JPanel panelCentro = new JPanel(new BorderLayout(10, 20));
	        panelCentro.add(panelForm,     BorderLayout.NORTH);
	        panelCentro.add(panelBotones,  BorderLayout.CENTER);
	        panelCentro.add(scrollTabla,   BorderLayout.SOUTH);
	 
	        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
	        add(panelPrincipal);
	 
	        // ── Listeners ───────────────────────────────────────────────────
	        btnNuevo.addActionListener(e -> nuevo());
	        btnGuardar.addActionListener(e -> guardar());
	        btnEditar.addActionListener(e -> editar());
	        btnEliminar.addActionListener(e -> eliminar());
	        btnBuscar.addActionListener(e -> buscar());
	 
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }
	    private void nuevo()    { /* TODO: limpiar campos */ }
	    private void guardar()  { /* TODO: guardar registro */ }
	    private void editar()   { /* TODO: cargar registro seleccionado */ }
	    private void eliminar() { /* TODO: eliminar registro seleccionado */ }
	    private void buscar()   { /* TODO: buscar por código o nombre */ }
}
