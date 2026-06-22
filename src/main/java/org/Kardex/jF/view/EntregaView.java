package org.Kardex.jF.view;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EntregaView extends JFrame {

	private static final long serialVersionUID = 1L;
    private JComboBox<String> comboOrden = new JComboBox<>(new String[]{"ORD-001", "ORD-002", "ORD-003"});
    private JLabel lblCliente  = new JLabel("Juan Pérez");
    private JLabel lblTotal    = new JLabel("S/ 150");
    private JLabel lblEstado   = new JLabel("LISTO  →  ENTREGADO");
    private JButton btnConfirmar = new JButton("Confirmar Entrega");
    private JButton btnImprimir  = new JButton("Imprimir");
    
    public EntregaView() {
        setTitle("Entrega");
        setSize(380, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "ENTREGA",
                        TitledBorder.CENTER, TitledBorder.TOP),
                new EmptyBorder(10, 15, 10, 15)));
 
        panel.add(new JLabel("Orden:"));
        panel.add(comboOrden);
 
        panel.add(new JLabel("Cliente:"));
        panel.add(lblCliente);
 
        panel.add(new JLabel("Total a pagar:"));
        panel.add(lblTotal);
 
        panel.add(new JLabel("Estado:"));
        panel.add(lblEstado);
 
        panel.add(btnConfirmar);
        panel.add(btnImprimir);
 
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
