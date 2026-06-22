package org.Kardex.jF.view;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.*;
import java.awt.*;

public class ReparacionView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboOrden = new JComboBox<>(new String[]{"ORD-001", "ORD-002", "ORD-003"});
    
    private JCheckBox chkFormateo  = new JCheckBox("Formateo");
    private JCheckBox chkLimpieza  = new JCheckBox("Limpieza");
    private JCheckBox chkDisco     = new JCheckBox("Cambio de disco");
 
    private JTextArea areaRepuestos = new JTextArea("- SSD 240GB", 3, 20);
 
    private JTextField campoCosto  = new JTextField();
    private JLabel lblEstado       = new JLabel("REPARACIÓN");
    private JButton btnFinalizar   = new JButton("Finalizar");
 
    public ReparacionView() {
        setTitle("Reparación");
        setSize(380, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "REPARACIÓN",
                        TitledBorder.CENTER, TitledBorder.TOP),
                new EmptyBorder(10, 15, 10, 15)));
 
        // Orden
        JPanel filaOrden = new JPanel(new GridLayout(1, 2, 10, 0));
        filaOrden.add(new JLabel("Orden:"));
        filaOrden.add(comboOrden);
        panel.add(filaOrden);
        panel.add(Box.createVerticalStrut(10));
 
        // Servicios realizados
        panel.add(new JLabel("Servicios realizados:"));
        panel.add(chkFormateo);
        panel.add(chkLimpieza);
        panel.add(chkDisco);
        panel.add(Box.createVerticalStrut(10));
 
        // Repuestos
        panel.add(new JLabel("Repuestos:"));
        areaRepuestos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(new JScrollPane(areaRepuestos));
        panel.add(Box.createVerticalStrut(10));
 
        // Costo final
        JPanel filaCosto = new JPanel(new GridLayout(1, 2, 10, 0));
        filaCosto.add(new JLabel("Costo final:"));
        filaCosto.add(campoCosto);
        panel.add(filaCosto);
        panel.add(Box.createVerticalStrut(10));
 
        // Estado
        JPanel filaEstado = new JPanel(new GridLayout(1, 2, 10, 0));
        filaEstado.add(new JLabel("Estado:"));
        filaEstado.add(lblEstado);
        panel.add(filaEstado);
        panel.add(Box.createVerticalStrut(10));
 
        // Botón
        JPanel filaBton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaBton.add(btnFinalizar);
        panel.add(filaBton);
 
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new ReparacionView();
    }
}
