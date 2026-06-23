package org.Kardex.jF.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.Kardex.jF.bean.entity.MovimientoInventario;
import org.Kardex.jF.bean.entity.Repuesto;
import org.Kardex.jF.model.MovimientoInventarioModel;
import org.Kardex.jF.model.OrdenServicioModel;
import org.Kardex.jF.model.RepuestoModel;

public class MovimientosView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JRadioButton rbEntrada = new JRadioButton("Entrada", true);
    private JRadioButton rbSalida  = new JRadioButton("Salida");
    private JComboBox<Repuesto> comboRepuesto = new JComboBox<>();
    private JTextField campoCantidad = new JTextField("1");
    private JComboBox<String> comboMotivo = new JComboBox<>();
    private JTextField campoFecha = new JTextField(LocalDate.now().format(FORMATO_FECHA));
    private JButton btnRegistrar = new JButton("Registrar Movimiento");

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private RepuestoModel repuestoDao = new RepuestoModel();
    private OrdenServicioModel ordenDao = new OrdenServicioModel();
    private MovimientoInventarioModel movimientoDao = new MovimientoInventarioModel();

    public MovimientosView() {
        setTitle("Movimientos de Inventario");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(), "MOVIMIENTOS DE INVENTARIO",
                        TitledBorder.CENTER, TitledBorder.TOP),
                new EmptyBorder(10, 15, 10, 15)));

        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 8));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbEntrada);
        grupo.add(rbSalida);
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelRadio.add(rbEntrada);
        panelRadio.add(rbSalida);

        panelForm.add(new JLabel("Tipo Movimiento:"));
        panelForm.add(panelRadio);
        panelForm.add(new JLabel("Repuesto:"));
        panelForm.add(comboRepuesto);
        panelForm.add(new JLabel("Cantidad:"));
        panelForm.add(campoCantidad);
        panelForm.add(new JLabel("Motivo:"));
        panelForm.add(comboMotivo);
        panelForm.add(new JLabel("Fecha:"));
        panelForm.add(campoFecha);
        panelForm.add(new JLabel());
        panelForm.add(btnRegistrar);

        modeloTabla = new DefaultTableModel(new String[]{"Fecha", "Tipo", "Repuesto", "Cantidad", "Motivo"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) { return false; }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(0, 180));

        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        add(panel);
        registrarEventos();
        cargarRepuestos();
        cargarMotivos();
        cargarMovimientos();
        setLocationRelativeTo(null);
    }

    private void registrarEventos() {
        rbEntrada.addActionListener(e -> cargarMotivos());
        rbSalida.addActionListener(e -> cargarMotivos());
        btnRegistrar.addActionListener(e -> registrarMovimiento());
    }

    private void cargarRepuestos() {
        comboRepuesto.removeAllItems();
        for (Repuesto r : repuestoDao.listar()) {
            if ("Activo".equalsIgnoreCase(r.getEstado())) comboRepuesto.addItem(r);
        }
    }

    private void cargarMotivos() {
        comboMotivo.removeAllItems();
        if (rbSalida.isSelected()) {
            comboMotivo.addItem("Vender");
            for (String codigoOrden : ordenDao.listarCodigosOrden()) comboMotivo.addItem(codigoOrden);
        } else {
            comboMotivo.addItem("Compra de mercadería");
            comboMotivo.addItem("Ajuste de inventario");
            comboMotivo.addItem("Devolución");
        }
    }

    private void cargarMovimientos() {
        modeloTabla.setRowCount(0);
        for (MovimientoInventario m : movimientoDao.listar()) {
            modeloTabla.addRow(new Object[]{
                m.getFecha().format(FORMATO_FECHA),
                m.getTipo(),
                m.getCodigoRepuesto() + " - " + m.getNombreRepuesto(),
                m.getCantidad(),
                m.getMotivo()
            });
        }
    }

    private void registrarMovimiento() {
        Repuesto repuesto = (Repuesto) comboRepuesto.getSelectedItem();
        if (repuesto == null) {
            JOptionPane.showMessageDialog(this, "Registre primero un repuesto activo en Gestionar Repuestos.");
            return;
        }

        try {
            MovimientoInventario movimiento = new MovimientoInventario();
            movimiento.setIdRepuesto(Integer.parseInt(repuesto.getId()));
            movimiento.setTipo(rbEntrada.isSelected() ? "Entrada" : "Salida");
            movimiento.setCantidad(Integer.parseInt(campoCantidad.getText().trim()));
            movimiento.setMotivo(String.valueOf(comboMotivo.getSelectedItem()));
            movimiento.setFecha(LocalDate.parse(campoFecha.getText().trim(), FORMATO_FECHA));

            if (movimiento.getCantidad() <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.");
                return;
            }

            if (movimientoDao.registrar(movimiento)) {
                cargarRepuestos();
                cargarMovimientos();
                JOptionPane.showMessageDialog(this, "Movimiento registrado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar. Verifique el stock disponible para salidas.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/MM/yyyy.");
        }
    }
}
