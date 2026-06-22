package org.Kardex.jF.view;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ConsultaView extends JFrame{

	private static final long serialVersionUID = 1L;
    private JComboBox<String> comboEstado  = new JComboBox<>(new String[]{"Estado", "Recibido", "Diagnóstico", "Reparación", "Listo", "Entregado"});
    private JComboBox<String> comboFecha   = new JComboBox<>(new String[]{"Fecha", "Hoy", "Esta semana", "Este mes"});
    private JComboBox<String> comboCliente = new JComboBox<>(new String[]{"Cliente", "Juan Pérez", "María López"});
    private JButton btnBuscar = new JButton("Buscar");
    private JTable tabla;
    public ConsultaView() {
        setTitle("Consulta de Órdenes");
        setSize(560, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "CONSULTA",
                        TitledBorder.CENTER, TitledBorder.TOP),
                new EmptyBorder(10, 15, 10, 15)));
 
        // ── Filtros ──────────────────────────────────────────────────────
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        panelFiltros.add(new JLabel("Filtro:"));
        panelFiltros.add(comboEstado);
        panelFiltros.add(comboFecha);
        panelFiltros.add(comboCliente);
        panelFiltros.add(btnBuscar);
 
        // ── Tabla ────────────────────────────────────────────────────────
        String[] columnas = {"Orden", "Cliente", "Equipo", "Estado", "Fecha"};
        Object[][] datos = {
            {"001", "Juan Pérez",   "Laptop HP",  "Reparación", "2025-06-01"},
            {"002", "María López",  "PC Escritorio", "Listo",   "2025-06-10"},
            {"003", "Carlos Ríos",  "Tablet",     "Recibido",   "2025-06-20"}
        };
        tabla = new JTable(datos, columnas);
        JScrollPane scrollTabla = new JScrollPane(tabla);
 
        panel.add(panelFiltros,  BorderLayout.NORTH);
        panel.add(scrollTabla,   BorderLayout.CENTER);
 
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
