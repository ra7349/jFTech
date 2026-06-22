package org.Kardex.jF.view;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class DiagnosticoView extends JFrame {

	private static final long serialVersionUID = 1L;

    private JComboBox<String> comboOrden   = new JComboBox<>(new String[]{"ORD-001", "ORD-002", "ORD-003"});
    private JLabel lblFalla                = new JLabel("(auto cargada)");
    private JTextField campoDiagnostico    = new JTextField();
    private JTextField campoCosto          = new JTextField();
    private JLabel lblEstado               = new JLabel("DIAGNÓSTICO");
    private JButton btnGuardar             = new JButton("Guardar");

    public DiagnosticoView() {
        setTitle("Diagnóstico");
        setSize(380, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "DIAGNÓSTICO",
                        TitledBorder.CENTER, TitledBorder.TOP),
                new EmptyBorder(10, 15, 10, 15)));
 
        panel.add(new JLabel("Orden:"));
        panel.add(comboOrden);
 
        panel.add(new JLabel("Falla:"));
        panel.add(lblFalla);
 
        panel.add(new JLabel("Diagnóstico:"));
        panel.add(campoDiagnostico);
 
        panel.add(new JLabel("Costo estimado:"));
        panel.add(campoCosto);
 
        panel.add(new JLabel("Estado:"));
        panel.add(lblEstado);
 
        panel.add(btnGuardar);
        panel.add(new JLabel());
 
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}


