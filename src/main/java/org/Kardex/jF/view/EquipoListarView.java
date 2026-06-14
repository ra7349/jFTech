package org.Kardex.jF.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.Kardex.jF.bean.entity.Equipo;
import org.Kardex.jF.model.EquipoModel;

public class EquipoListarView extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelo;
    private JTable tabla;
    private EquipoModel dao = new EquipoModel();

    public EquipoListarView() {
        setTitle("Listado de Equipos");
        setSize(1000, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
            new String[]{"ID","Código","Marca","Modelo","N° Serie","Tipo","Estado","Fecha Ingreso","Cliente"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnEliminar  = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        btnEliminar .addActionListener(e -> eliminar());
        btnRefrescar.addActionListener(e -> cargarDatos());
        cargarDatos();
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        for (Equipo e : dao.listar()) {
            modelo.addRow(new Object[]{
                e.getId(), e.getCodigo(), e.getMarca(), e.getModelo(),
                e.getNumeroSerie(), e.getTipoEquipo(),
                Boolean.TRUE.equals(e.getEstado()) ? "Activo" : "Inactivo",
                e.getFechaIngreso(), e.getNombreCliente()
            });
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione un equipo."); return; }
        int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar equipo?",
                "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (dao.eliminar(id)) { cargarDatos(); JOptionPane.showMessageDialog(this, "Equipo eliminado."); }
            else JOptionPane.showMessageDialog(this, "No se pudo eliminar. Puede tener órdenes activas.");
        }
    }
}
