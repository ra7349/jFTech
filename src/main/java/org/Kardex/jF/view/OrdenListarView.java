package org.Kardex.jF.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.Kardex.jF.bean.entity.OrdenServicio;
import org.Kardex.jF.model.OrdenServicioModel;

public class OrdenListarView extends JFrame {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelo;
    private JTable tabla;
    private OrdenServicioModel dao = new OrdenServicioModel();

    public OrdenListarView() {
        setTitle("Órdenes de Servicio");
        setSize(1150, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
            new String[]{"ID","Código","Cliente","Equipo","Servicio","Estado",
                         "Costo Est.","Costo Final","F. Apertura","F. Cierre"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnCambiarEstado = new JButton("Cambiar Estado");
        JButton btnEliminar      = new JButton("Eliminar");
        JButton btnRefrescar     = new JButton("Refrescar");
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnCambiarEstado);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        btnRefrescar    .addActionListener(e -> cargarDatos());
        UiStyle.applyTo(this);

        btnCambiarEstado.addActionListener(e -> cambiarEstado());
        btnEliminar     .addActionListener(e -> eliminar());
        cargarDatos();
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        for (OrdenServicio o : dao.listar()) {
            modelo.addRow(new Object[]{
                o.getId(), o.getCodigo(), o.getNombreCliente(), o.getCodigoEquipo(),
                o.getNombreServicio(), o.getEstado(),
                String.format("%.2f", o.getCostoEstimado()),
                o.getCostoFinal() != null ? String.format("%.2f", o.getCostoFinal()) : "-",
                o.getFechaApertura(), o.getFechaCierre()
            });
        }
    }

    private void cambiarEstado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione una orden."); return; }
        String[] estados = {"RECIBIDO","EN_DIAGNOSTICO","EN_REPARACION","ESPERANDO_REPUESTO","LISTO","ENTREGADO","CANCELADO"};
        String actual = modelo.getValueAt(fila, 5).toString();
        String nuevo = (String) JOptionPane.showInputDialog(
            this, "Nuevo estado:", "Cambiar Estado",
            JOptionPane.QUESTION_MESSAGE, null, estados, actual);
        if (nuevo == null || nuevo.equals(actual)) return;

        // Cargar la orden completa y actualizar solo el estado
        String id = modelo.getValueAt(fila, 0).toString();
        java.util.List<OrdenServicio> lista = dao.listar();
        OrdenServicio o = lista.stream()
            .filter(ord -> ord.getId().equals(id))
            .findFirst().orElse(null);
        if (o == null) return;
        o.setEstado(nuevo);
        if (nuevo.equals("ENTREGADO") || nuevo.equals("CANCELADO"))
            o.setFechaCierre(java.time.LocalDate.now());
        if (dao.actualizar(o)) {
            cargarDatos();
            JOptionPane.showMessageDialog(this, "Estado actualizado a: " + nuevo);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar.");
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione una orden."); return; }
        int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar orden?",
                "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (dao.eliminar(id)) { cargarDatos(); JOptionPane.showMessageDialog(this, "Orden eliminada."); }
            else JOptionPane.showMessageDialog(this, "Error al eliminar.");
        }
    }
}
