package org.Kardex.jF.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.Kardex.jF.bean.entity.Usuario;
import org.Kardex.jF.model.UsuarioModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginView extends JFrame {
	 
    private static final long serialVersionUID = 1L;
 
    // Paleta de colores
    private static final Color COLOR_FONDO = new Color(245, 247, 250);
    private static final Color COLOR_PRIMARIO = new Color(41, 98, 255);
    private static final Color COLOR_PRIMARIO_HOVER = new Color(30, 78, 216);
    private static final Color COLOR_TEXTO = new Color(33, 37, 41);
    private static final Color COLOR_BORDE = new Color(206, 212, 218);
 
    private JTextField userField;
    private JTextField passField;
 
    public LoginView() {
        setTitle("Iniciar Sesión");
        setSize(380, 420);
        setMinimumSize(new Dimension(340, 380));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new GridBagLayout());
 
        JPanel panelCentral = construirPanelCentral();
        add(panelCentral);
        UiStyle.applyTo(this);
    }
 
    private JPanel construirPanelCentral() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(35, 35, 35, 35)
        ));
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 8, 0);
 
        // Título
        JLabel lblTitulo = new JLabel("Bienvenido");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(COLOR_TEXTO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);
        panel.add(lblTitulo, gbc);
 
        JLabel lblSubtitulo = new JLabel("Ingresa tus credenciales para continuar");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(108, 117, 125));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 25, 0);
        panel.add(lblSubtitulo, gbc);
 
        // Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUsuario.setForeground(COLOR_TEXTO);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(lblUsuario, gbc);
 
        userField = new JTextField();
        estilizarCampo(userField);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 18, 0);
        panel.add(userField, gbc);
 
        // Contraseña
        JLabel lblClave = new JLabel("Contraseña");
        lblClave.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblClave.setForeground(COLOR_TEXTO);
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(lblClave, gbc);
 
        passField = new JPasswordField();
        estilizarCampo(passField);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 28, 0);
        panel.add(passField, gbc);
 
        // Botón
        JButton btnIngresar = construirBotonIngresar();
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(btnIngresar, gbc);
 
        return panel;
    }
 
    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
        campo.setPreferredSize(new Dimension(260, 38));
    }
 
    private JButton construirBotonIngresar() {
        JButton btn = new JButton("Ingresar");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(COLOR_PRIMARIO);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        btn.setPreferredSize(new Dimension(260, 42));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(COLOR_PRIMARIO_HOVER);
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(COLOR_PRIMARIO);
            }
        });
 
        btn.addActionListener(e -> validarCredenciales());
        return btn;
    }

    private void validarCredenciales() {
    	
        String usuario = userField.getText();
        String contraseña =
                String.valueOf(passField.getText());

        UsuarioModel dao = new UsuarioModel();

        Usuario u =
                dao.verificarContraseña(usuario, contraseña);

        if (u != null) {

            MarcoPrincipalView frm = new MarcoPrincipalView();
            frm.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos");
        }
}
}