package org.Kardex.jF.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.Kardex.jF.bean.entity.Usuario;
import org.Kardex.jF.model.UsuarioModel;

import java.awt.*;
import java.awt.event.ActionEvent;


public class ActualizarcontraseñaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JTextField campoActual = new JTextField();
    private final JTextField campoNueva = new JTextField();
    private final JTextField campoConfirmar = new JTextField(); 


    public ActualizarcontraseñaView() {
        setTitle("Cambiar Contraseña");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("image.png").getImage());
        setLayout(new GridLayout(4, 2, 15, 20));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
 
        add(new JLabel("Contraseña Actual:"));
        add(campoActual);
 
        add(new JLabel("Nueva Contraseña:"));
        add(campoNueva);
 
        add(new JLabel("Confirmar Contraseña:"));
        add(campoConfirmar);
 
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnGuardar);
        add(btnCancelar);
 
        UiStyle.applyTo(this);
        setLocationRelativeTo(null);
        setVisible(true);
 
        btnGuardar.addActionListener(this::guardar);
        btnCancelar.addActionListener(this::cancelar);
    }

    private void guardar(ActionEvent e) {

        String actual = campoActual.getText();
        String nueva = campoNueva.getText();
        String confirmar = campoConfirmar.getText();

        UsuarioModel dao = new UsuarioModel();

        Usuario usuario =
                dao.verificarContraseña("admin", actual);

        if (usuario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "La contraseña actual es incorrecta");

            return;
        }

        if (!nueva.equals(confirmar)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Las contraseñas no coinciden");

            return;
        }

        if (dao.cambiarContraseña("admin", nueva)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Contraseña actualizada");

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar");
        }
    }

    private void cancelar(ActionEvent e) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        campoActual.setText("");
        campoNueva.setText("");
        campoConfirmar.setText("");
    }

}