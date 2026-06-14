package org.Kardex.jF.view;

import javax.swing.*;
import java.awt.*;
import org.Kardex.jF.bean.entity.Tecnico;
import org.Kardex.jF.model.TecnicoModel;

public class FormularioTecnico extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField txtCodigo       = new JTextField();
    private JTextField txtNombre       = new JTextField();
    private JTextField txtApellido     = new JTextField();
    private JTextField txtTelefono     = new JTextField();
    private JTextField txtCorreo       = new JTextField();
    private JTextField txtEspecialidad = new JTextField();

    private final TecnicoModel dao = new TecnicoModel();

    public FormularioTecnico(JFrame parent) {
        super(parent, "Registrar Técnico", true);
        setSize(400, 340);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        add(crearPanel(), BorderLayout.CENTER);
        add(crearBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanel() {
        JPanel p = new JPanel(new GridLayout(6, 2, 10, 8));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        p.add(new JLabel("Código *:")); p.add(txtCodigo);
        p.add(new JLabel("Nombres *:")); p.add(txtNombre);
        p.add(new JLabel("Apellidos *:")); p.add(txtApellido);
        p.add(new JLabel("Teléfono:")); p.add(txtTelefono);
        p.add(new JLabel("Correo:")); p.add(txtCorreo);
        p.add(new JLabel("Especialidad:")); p.add(txtEspecialidad);
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
        if (txtCodigo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Código y Nombre son obligatorios."); return;
        }
        Tecnico t = new Tecnico();
        t.setCodigo(txtCodigo.getText().trim());
        t.setNombre(txtNombre.getText().trim());
        t.setApellido(txtApellido.getText().trim());
        try {
            if (!txtTelefono.getText().trim().isEmpty())
                t.setTelefono(Long.parseLong(txtTelefono.getText().trim()));
        } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this,"Teléfono inválido."); return; }
        t.setCorreo(txtCorreo.getText().trim());
        t.setEspecialidad(txtEspecialidad.getText().trim());
        if (dao.insertar(t)) {
            JOptionPane.showMessageDialog(this, "Técnico registrado.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar. ¿Código duplicado?");
        }
    }

    private void limpiar() {
        txtCodigo.setText(""); txtNombre.setText(""); txtApellido.setText("");
        txtTelefono.setText(""); txtCorreo.setText(""); txtEspecialidad.setText("");
        txtCodigo.requestFocus();
    }
}
