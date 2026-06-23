package org.Kardex.jF.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.Kardex.jF.bean.entity.Cliente;
import org.Kardex.jF.bean.entity.OrdenServicio;
import org.Kardex.jF.bean.entity.Servicio;
import org.Kardex.jF.model.ClienteModel;
import org.Kardex.jF.model.OrdenServicioModel;
import org.Kardex.jF.model.ServiciosModel;

public class FormularioOrden extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtCodigo         = new JTextField();
    private JTextArea  txtFalla          = new JTextArea(3, 20);
    private JTextField txtEquipo         = new JTextField();
    private JTextField txtCostoEstimado  = new JTextField("0.00");
    private JComboBox<String> cbEstado   = new JComboBox<>(new String[]{
        "RECIBIDO","EN_DIAGNOSTICO","EN_REPARACION","ESPERANDO_REPUESTO","LISTO","ENTREGADO","CANCELADO"});
    private JComboBox<String> cbCliente;
    private JComboBox<String> cbServicio;

    private final OrdenServicioModel daoO = new OrdenServicioModel();
    private final ClienteModel       daoC = new ClienteModel();
    private final ServiciosModel     daoS = new ServiciosModel();
    private List<Cliente> clientes;
    private List<Servicio> servicios;

    public FormularioOrden(JFrame parent) {
        super(parent, "Nueva Orden de Servicio", true);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        clientes  = daoC.listar();
        servicios = daoS.listar();

        String[] itemsCliente = clientes.stream()
            .map(c -> c.getId() + " - " + c.getNombre() + " " + c.getApellido())
            .toArray(String[]::new);
        String[] itemsServicio = servicios.stream()
            .map(s -> s.getIdServicio() + " - " + s.getDescripcion())
            .toArray(String[]::new);
        cbCliente  = new JComboBox<>(itemsCliente);
        cbServicio = new JComboBox<>(itemsServicio);

        add(crearPanel(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
        UiStyle.applyTo(this);
    }

    private JPanel crearPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill   = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addRow(p, g, row++, "Código Orden *:", txtCodigo);
        addRow(p, g, row++, "Cliente *:", cbCliente);
        addRow(p, g, row++, "Servicio *:", cbServicio);
        addRow(p, g, row++, "Equipo:", txtEquipo);
        addRow(p, g, row++, "Estado:", cbEstado);
        addRow(p, g, row++, "Costo Estimado:", txtCostoEstimado);

        // Textareas
        g.gridx = 0; g.gridy = row; g.weightx = 0.3;
        p.add(new JLabel("Descripción falla:"), g);
        g.gridx = 1; g.weightx = 0.7;
        p.add(new JScrollPane(txtFalla), g);
        row++;

        return p;
    }

    private void addRow(JPanel p, GridBagConstraints g, int row, String label, JComponent comp) {
        g.gridx = 0; g.gridy = row; g.weightx = 0.3;
        p.add(new JLabel(label), g);
        g.gridx = 1; g.weightx = 0.7;
        p.add(comp, g);
    }

    private JPanel crearBotones() {
        JPanel p = new JPanel(new GridLayout(1, 2, 8, 0));
        p.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        JButton btnGuardar  = new JButton("Guardar");
        JButton btnCancelar = new JButton("Limpiar");
        btnGuardar .addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> limpiar());
        p.add(btnCancelar);
        p.add(btnGuardar);
        return p;
    }

    private void guardar() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El código es obligatorio."); return;
        }
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes registrados."); return;
        }
        if (servicios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay servicios registrados."); return;
        }
        OrdenServicio o = new OrdenServicio();
        o.setCodigo(txtCodigo.getText().trim());
        int idxCliente = cbCliente.getSelectedIndex();
        int idxServicio = cbServicio.getSelectedIndex();
        Servicio servicio = servicios.get(idxServicio);
        o.setIdCliente(Integer.parseInt(clientes.get(idxCliente).getId()));
        o.setIdServicio(servicio.getIdServicio());
        o.setCodigoEquipo(txtEquipo.getText().trim());
        o.setEstado((String) cbEstado.getSelectedItem());
        o.setDescripcionFalla(txtFalla.getText().trim());
        o.setDiagnostico("");
        try {
            o.setCostoEstimado(Double.parseDouble(txtCostoEstimado.getText().trim()));
        } catch (NumberFormatException ex) { o.setCostoEstimado(0); }
        if (txtCostoEstimado.getText().trim().equals("0.00") && servicio.getPrecio() != null) {
            o.setCostoEstimado(servicio.getPrecio());
        }
        o.setFechaApertura(java.time.LocalDate.now());

        if (daoO.insertar(o)) {
            JOptionPane.showMessageDialog(this, "Orden creada exitosamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear orden. ¿Código duplicado?");
        }
    }

    private void limpiar() {
        txtCodigo.setText(""); txtFalla.setText(""); txtEquipo.setText("");
        txtCostoEstimado.setText("0.00"); cbEstado.setSelectedIndex(0);
        if (cbCliente.getItemCount()  > 0) cbCliente.setSelectedIndex(0);
        if (cbServicio.getItemCount() > 0) cbServicio.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }
}
