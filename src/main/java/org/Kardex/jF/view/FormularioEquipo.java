package org.Kardex.jF.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.Kardex.jF.bean.entity.Cliente;
import org.Kardex.jF.bean.entity.Equipo;
import org.Kardex.jF.model.ClienteModel;
import org.Kardex.jF.model.EquipoModel;

public class FormularioEquipo extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtCodigo      = new JTextField();
    private JTextField txtMarca       = new JTextField();
    private JTextField txtModelo      = new JTextField();
    private JTextField txtNumeroSerie = new JTextField();
    private JComboBox<String> cbTipo  = new JComboBox<>(
        new String[]{"Laptop","PC Escritorio","Impresora","Tablet","Monitor","Otro"});
    private JComboBox<String> cbEstado = new JComboBox<>(new String[]{"Activo","Inactivo"});
    private JComboBox<String> cbCliente;

    private final EquipoModel  daoE = new EquipoModel();
    private final ClienteModel daoC = new ClienteModel();
    private List<Cliente> clientes;

    public FormularioEquipo(JFrame parent) {
        super(parent, "Registrar Equipo", true);
        setSize(420, 390);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        clientes = daoC.listar();
        String[] items = clientes.stream()
            .map(c -> c.getId() + " - " + c.getNombre() + " " + c.getApellido())
            .toArray(String[]::new);
        cbCliente = new JComboBox<>(items);

        add(crearPanel(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanel() {
        JPanel p = new JPanel(new GridLayout(7, 2, 10, 8));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        p.add(new JLabel("Código *:")); p.add(txtCodigo);
        p.add(new JLabel("Marca:")); p.add(txtMarca);
        p.add(new JLabel("Modelo:")); p.add(txtModelo);
        p.add(new JLabel("N° Serie:")); p.add(txtNumeroSerie);
        p.add(new JLabel("Tipo equipo:")); p.add(cbTipo);
        p.add(new JLabel("Estado:")); p.add(cbEstado);
        p.add(new JLabel("Cliente *:")); p.add(cbCliente);
        return p;
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
        Equipo eq = new Equipo();
        eq.setCodigo(txtCodigo.getText().trim());
        eq.setMarca(txtMarca.getText().trim());
        eq.setModelo(txtModelo.getText().trim());
        eq.setNumeroSerie(txtNumeroSerie.getText().trim());
        eq.setTipoEquipo((String) cbTipo.getSelectedItem());
        eq.setEstado(cbEstado.getSelectedIndex() == 0);
        eq.setFechaIngreso(java.time.LocalDate.now());
        int idx = cbCliente.getSelectedIndex();
        eq.setIdCliente(Integer.parseInt(clientes.get(idx).getId()));
        if (daoE.insertar(eq)) {
            JOptionPane.showMessageDialog(this, "Equipo registrado.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar. ¿Código duplicado?");
        }
    }

    private void limpiar() {
        txtCodigo.setText(""); txtMarca.setText(""); txtModelo.setText("");
        txtNumeroSerie.setText(""); cbTipo.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0); cbCliente.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }
}
