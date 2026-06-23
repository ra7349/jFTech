package org.Kardex.jF.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.Kardex.jF.bean.entity.OrdenServicio;
import org.Kardex.jF.model.OrdenServicioModel;

public class ConsultaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TODOS_LOS_ESTADOS = "Todos";
    private static final String[] ESTADOS_ORDEN = {
        TODOS_LOS_ESTADOS,
        "RECIBIDO",
        "EN_DIAGNOSTICO",
        "EN_REPARACION",
        "ESPERANDO_REPUESTO",
        "LISTO",
        "ENTREGADO",
        "CANCELADO"
    };

    private final JComboBox<String> comboEstado = new JComboBox<>(ESTADOS_ORDEN);
    private final JButton btnBuscar = new JButton("Buscar");
    private final DefaultTableModel modelo;
    private final OrdenServicioModel ordenServicioModel = new OrdenServicioModel();
    private JTable tabla;

    public ConsultaView() {
        setTitle("Consulta de Órdenes");
        setSize(820, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "CONSULTA",
                        TitledBorder.CENTER, TitledBorder.TOP),
                new EmptyBorder(10, 15, 10, 15)));

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        panelFiltros.add(new JLabel("Estado:"));
        panelFiltros.add(comboEstado);
        panelFiltros.add(btnBuscar);

        modelo = new DefaultTableModel(
                new String[]{"Orden", "Cliente", "Equipo", "Técnico", "Estado", "Fecha Apertura", "Fecha Cierre"}, 0) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
        tabla = new JTable(modelo);
        JScrollPane scrollTabla = new JScrollPane(tabla);

        btnBuscar.addActionListener(e -> buscarPorEstado(true));
        buscarPorEstado(false);

        panel.add(panelFiltros, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        add(panel);
        setLocationRelativeTo(null);
    }

    private void buscarPorEstado(boolean mostrarMensajeSinResultados) {
        String estadoSeleccionado = Objects.toString(comboEstado.getSelectedItem(), TODOS_LOS_ESTADOS);
        List<OrdenServicio> ordenes = ordenServicioModel.listar();

        modelo.setRowCount(0);
        for (OrdenServicio orden : ordenes) {
            if (TODOS_LOS_ESTADOS.equals(estadoSeleccionado) || estadoSeleccionado.equals(orden.getEstado())) {
                modelo.addRow(new Object[]{
                    orden.getCodigo(),
                    orden.getNombreCliente(),
                    orden.getCodigoEquipo(),
                    orden.getNombreTecnico(),
                    orden.getEstado(),
                    orden.getFechaApertura(),
                    orden.getFechaCierre()
                });
            }
        }

        if (mostrarMensajeSinResultados && modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron órdenes con el estado seleccionado.");
        }
    }
}
