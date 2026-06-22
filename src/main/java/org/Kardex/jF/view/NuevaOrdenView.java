package org.Kardex.jF.view;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class NuevaOrdenView extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboCliente = new JComboBox<>(new String[]{"Cliente 1", "Cliente 2", "Cliente 3"});
    private JTextField campoEquipo  = new JTextField("Laptop HP ...");
    private JTextField campoFalla   = new JTextField();
    private JTextField campoFecha   = new JTextField("2025-06-21");
    private JLabel lblEstado        = new JLabel("RECIBIDO");
    private JButton btnGuardar      = new JButton("Guardar");
    private JButton btnLimpiar      = new JButton("Limpiar");
    
	public NuevaOrdenView() {
		 setTitle("Nueva Orden");
	        setSize(380, 280);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
	        panel.setBorder(BorderFactory.createTitledBorder(
	                BorderFactory.createEtchedBorder(), "NUEVA ORDEN",
	                TitledBorder.CENTER, TitledBorder.TOP));
	        panel.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createTitledBorder(
	                        BorderFactory.createEtchedBorder(), "NUEVA ORDEN",
	                        TitledBorder.CENTER, TitledBorder.TOP),
	                new EmptyBorder(10, 15, 10, 15)));
	 
	        panel.add(new JLabel("Cliente:"));
	        panel.add(comboCliente);
	 
	        panel.add(new JLabel("Equipo:"));
	        panel.add(campoEquipo);
	 
	        panel.add(new JLabel("Falla:"));
	        panel.add(campoFalla);
	 
	        panel.add(new JLabel("Fecha:"));
	        panel.add(campoFecha);
	 
	        panel.add(new JLabel("Estado:"));
	        panel.add(lblEstado);
	 
	        panel.add(btnGuardar);
	        panel.add(btnLimpiar);
	 
	        add(panel);
	        setLocationRelativeTo(null);
	        setVisible(true);
	}
    
}
