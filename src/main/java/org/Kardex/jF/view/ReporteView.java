package org.Kardex.jF.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ReporteView extends JFrame {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cboReporte;
    private JButton btnGenerar;
    private JButton btnExportarPDF;
    private JButton btnExportarExcel;
    private JTable tblReporte;
    private JScrollPane scrollTabla;
 
    public ReporteView() {
 
        setTitle("Reportes");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
 
        // Panel superior
        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 10, 10));
 
        panelSuperior.add(new JLabel("Tipo de Reporte:"));
 
        cboReporte = new JComboBox<>();
        cboReporte.addItem("Clientes");
        cboReporte.addItem("Equipos");
        cboReporte.addItem("Servicios");
        cboReporte.addItem("Inventario");
        cboReporte.addItem("Ingresos");
 
        panelSuperior.add(cboReporte);
 
        btnGenerar = new JButton("Generar");
        btnGenerar.addActionListener(this::generarReporte);
 
        btnExportarPDF = new JButton("Exportar PDF");
        btnExportarPDF.addActionListener(this::exportarPDF);
 
        panelSuperior.add(btnGenerar);
        panelSuperior.add(btnExportarPDF);
 
        add(panelSuperior, BorderLayout.NORTH);
 
        // Tabla
        tblReporte = new JTable();
        scrollTabla = new JScrollPane(tblReporte);
 
        add(scrollTabla, BorderLayout.CENTER);
 
        // Panel inferior
        JPanel panelInferior = new JPanel(new GridLayout(1, 1));
 
        btnExportarExcel = new JButton("Exportar Excel");
        btnExportarExcel.addActionListener(this::exportarExcel);
 
        panelInferior.add(btnExportarExcel);
 
        add(panelInferior, BorderLayout.SOUTH);
 
        UiStyle.applyTo(this);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    private void generarReporte(ActionEvent e) {
        // Implementar lógica posteriormente
    }
 
    private void exportarPDF(ActionEvent e) {
        // Implementar lógica posteriormente
    }
 
    private void exportarExcel(ActionEvent e) {
        // Implementar lógica posteriormente
    }
 
    public static void main(String[] args) {
        new ReporteView();
    }
}
