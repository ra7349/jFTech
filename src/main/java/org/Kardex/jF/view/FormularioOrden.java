package org.Kardex.jF.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.Kardex.jF.bean.entity.Equipo;
import org.Kardex.jF.bean.entity.OrdenServicio;
import org.Kardex.jF.bean.entity.Tecnico;
import org.Kardex.jF.model.EquipoModel;
import org.Kardex.jF.model.OrdenServicioModel;
import org.Kardex.jF.model.TecnicoModel;

public class FormularioOrden extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtCodigo         = new JTextField();
    private JTextArea  txtFalla          = new JTextArea(3, 20);
    private JTextArea  txtDiagnostico    = new JTextArea(3, 20);
    private JTextField txtCostoEstimado  = new JTextField("0.00");
    private JComboBox<String> cbEstado   = new JComboBox<>(new String[]{
        "RECIBIDO","EN_DIAGNOSTICO","EN_REPARACION","ESPERANDO_REPUESTO","LISTO","ENTREGADO","CANCELADO"});
    private JComboBox<String> cbEquipo;
    private JComboBox<String> cbTecnico;

    private final OrdenServicioModel daoO = new OrdenServicioModel();
    private final EquipoModel        daoE = new EquipoModel();
    private final TecnicoModel       daoT = new TecnicoModel();
    private List<Equipo>  equipos;
    private List<Tecnico> tecnicos;

    public FormularioOrden(JFrame parent) {
        super(parent, "Nueva Orden de Servicio", true);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        equipos  = daoE.listar();
        tecnicos = daoT.listar();

        String[] itemsEq = equipos.stream()
            .map(e -> e.getId() + " - " + e.getCodigo() + " (" + e.getNombreCliente() + ")")
            .toArray(String[]::new);
        String[] itemsTec = tecnicos.stream()
            .map(t -> t.getId() + " - " + t.getNombre() + " " + t.getApellido())
            .toArray(String[]::new);
        cbEquipo  = new JComboBox<>(itemsEq);
        cbTecnico = new JComboBox<>(itemsTec);

        add(crearPanel(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill   = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addRow(p, g, row++, "Código Orden *:", txtCodigo);
        addRow(p, g, row++, "Equipo *:", cbEquipo);
        addRow(p, g, row++, "Técnico:", cbTecnico);
        addRow(p, g, row++, "Estado:", cbEstado);
        addRow(p, g, row++, "Costo Estimado:", txtCostoEstimado);

        // Textareas
        g.gridx = 0; g.gridy = row; g.weightx = 0.3;
        p.add(new JLabel("Descripción falla:"), g);
        g.gridx = 1; g.weightx = 0.7;
        p.add(new JScrollPane(txtFalla), g);
        row++;

        g.gridx = 0; g.gridy = row; g.weightx = 0.3;
        p.add(new JLabel("Diagnóstico:"), g);
        g.gridx = 1; g.weightx = 0.7;
        p.add(new JScrollPane(txtDiagnostico), g);

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
        if (equipos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay equipos registrados."); return;
        }
        OrdenServicio o = new OrdenServicio();
        o.setCodigo(txtCodigo.getText().trim());
        int idxEq = cbEquipo.getSelectedIndex();
        o.setIdEquipo(Integer.parseInt(equipos.get(idxEq).getId()));
        if (!tecnicos.isEmpty()) {
            int idxTec = cbTecnico.getSelectedIndex();
            o.setIdTecnico(Integer.parseInt(tecnicos.get(idxTec).getId()));
        }
        o.setEstado((String) cbEstado.getSelectedItem());
        o.setDescripcionFalla(txtFalla.getText().trim());
        o.setDiagnostico(txtDiagnostico.getText().trim());
        try {
            o.setCostoEstimado(Double.parseDouble(txtCostoEstimado.getText().trim()));
        } catch (NumberFormatException ex) { o.setCostoEstimado(0); }
        o.setFechaApertura(java.time.LocalDate.now());

        if (daoO.insertar(o)) {
            JOptionPane.showMessageDialog(this, "Orden creada exitosamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear orden. ¿Código duplicado?");
        }
    }

    private void limpiar() {
        txtCodigo.setText(""); txtFalla.setText(""); txtDiagnostico.setText("");
        txtCostoEstimado.setText("0.00"); cbEstado.setSelectedIndex(0);
        if (cbEquipo.getItemCount()  > 0) cbEquipo.setSelectedIndex(0);
        if (cbTecnico.getItemCount() > 0) cbTecnico.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }
}
