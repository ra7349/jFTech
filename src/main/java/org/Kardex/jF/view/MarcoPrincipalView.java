package org.Kardex.jF.view;

import javax.swing.*;
import java.awt.*;

public class MarcoPrincipalView extends JFrame {

    private static final long serialVersionUID = 1L;

    public MarcoPrincipalView() {
        setTitle("JF Technology & Services — Sistema de Soporte Técnico");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ── Menú bar ──────────────────────────────────────────────────
        JMenuBar barra = new JMenuBar();

        // Clientes
        JMenu menuClientes = new JMenu("Clientes");
        JMenuItem itemRegistrarCliente  = new JMenuItem("Registrar Cliente");
        JMenuItem itemModificarCliente  = new JMenuItem("Modificar Cliente");
        JMenuItem itemListarClientes    = new JMenuItem("Listar Clientes");
        menuClientes.add(itemRegistrarCliente);
        menuClientes.add(itemModificarCliente);
        menuClientes.add(itemListarClientes);

        // Técnicos
        JMenu menuTecnicos = new JMenu("Técnicos");
        JMenuItem itemRegistrarTecnico  = new JMenuItem("Registrar Técnico");
        JMenuItem itemListarTecnicos    = new JMenuItem("Listar Técnicos");
        menuTecnicos.add(itemRegistrarTecnico);
        menuTecnicos.add(itemListarTecnicos);

        // Equipos
        JMenu menuEquipos = new JMenu("Equipos");
        JMenuItem itemRegistrarEquipo   = new JMenuItem("Registrar Equipo");
        JMenuItem itemListarEquipos     = new JMenuItem("Listar Equipos");
        menuEquipos.add(itemRegistrarEquipo);
        menuEquipos.add(itemListarEquipos);

        // Órdenes
        JMenu menuOrdenes = new JMenu("Órdenes de Servicio");
        JMenuItem itemNuevaOrden        = new JMenuItem("Nueva Orden");
        JMenuItem itemListarOrdenes     = new JMenuItem("Listar Órdenes");
        menuOrdenes.add(itemNuevaOrden);
        menuOrdenes.add(itemListarOrdenes);

        // Inventario
        JMenu menuInventario = new JMenu("Inventario");
        JMenuItem itemRegistrarProducto = new JMenuItem("Registrar Producto");
        JMenuItem itemListarProductos   = new JMenuItem("Listar Productos");
        menuInventario.add(itemRegistrarProducto);
        menuInventario.add(itemListarProductos);

        // Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        JMenuItem itemCerrar   = new JMenuItem("Cerrar");
        menuAyuda.add(itemAcercaDe);
        menuAyuda.addSeparator();
        menuAyuda.add(itemCerrar);

        barra.add(menuClientes);
        barra.add(menuTecnicos);
        barra.add(menuEquipos);
        barra.add(menuOrdenes);
        barra.add(menuInventario);
        barra.add(menuAyuda);
        setJMenuBar(barra);

        // ── Panel central con logo / bienvenida ───────────────────────
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(15, 23, 42));

        JLabel lblTitulo = new JLabel("JF Technology & Services", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(80, 0, 10, 0));

        JLabel lblSub = new JLabel("Sistema de Gestión para Soporte Técnico", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSub.setForeground(new Color(59, 130, 246));

        JLabel lblVersion = new JLabel("v1.0  |  2026", SwingConstants.CENTER);
        lblVersion.setFont(new Font("Arial", Font.ITALIC, 12));
        lblVersion.setForeground(new Color(156, 163, 175));
        lblVersion.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JPanel panelTexto = new JPanel(new GridLayout(3, 1, 0, 5));
        panelTexto.setOpaque(false);
        panelTexto.add(lblTitulo);
        panelTexto.add(lblSub);
        panelTexto.add(lblVersion);

        panelCentro.add(panelTexto, BorderLayout.NORTH);

        // Tarjetas de módulos
        JPanel panelCards = new JPanel(new GridLayout(2, 3, 15, 15));
        panelCards.setOpaque(false);
        panelCards.setBorder(BorderFactory.createEmptyBorder(40, 60, 60, 60));
        String[][] modulos = {
            {"👥  Clientes",    "Registro y gestión de clientes"},
            {"🔧  Técnicos",    "Control de técnicos asignados"},
            {"💻  Equipos",     "Equipos ingresados a reparación"},
            {"📋  Órdenes",     "Apertura y seguimiento de tickets"},
            {"📦  Inventario",  "Repuestos y materiales"},
            {"📊  Reportes",    "KPIs y estadísticas"}
        };
        for (String[] mod : modulos) {
            JPanel card = new JPanel(new BorderLayout(0, 4));
            card.setBackground(new Color(30, 41, 59));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(59, 130, 246), 1),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));
            JLabel lTit = new JLabel(mod[0]);
            lTit.setFont(new Font("Arial", Font.BOLD, 13));
            lTit.setForeground(Color.WHITE);
            JLabel lDesc = new JLabel("<html><small>" + mod[1] + "</small></html>");
            lDesc.setForeground(new Color(156, 163, 175));
            card.add(lTit,  BorderLayout.NORTH);
            card.add(lDesc, BorderLayout.CENTER);
            panelCards.add(card);
        }
        panelCentro.add(panelCards, BorderLayout.CENTER);
        add(panelCentro);

        // ── Acciones ──────────────────────────────────────────────────
        itemRegistrarCliente.addActionListener(e -> new FormularioCliente(this).setVisible(true));
        itemModificarCliente.addActionListener(e -> new ModificarClienteView(this).setVisible(true));
        itemListarClientes  .addActionListener(e -> new ClienteListarView().setVisible(true));

        itemRegistrarTecnico.addActionListener(e -> new FormularioTecnico(this).setVisible(true));
        itemListarTecnicos  .addActionListener(e -> new TecnicoListarView().setVisible(true));

        itemRegistrarEquipo .addActionListener(e -> new FormularioEquipo(this).setVisible(true));
        itemListarEquipos   .addActionListener(e -> new EquipoListarView().setVisible(true));

        itemNuevaOrden      .addActionListener(e -> new FormularioOrden(this).setVisible(true));
        itemListarOrdenes   .addActionListener(e -> new OrdenListarView().setVisible(true));

        itemRegistrarProducto.addActionListener(e -> new FormularioProducto(this).setVisible(true));
        itemListarProductos  .addActionListener(e -> new ProductoListarView().setVisible(true));

        itemAcercaDe.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "JF Technology & Services\nSistema de Soporte Técnico v1.0\n2026",
            "Acerca de", JOptionPane.INFORMATION_MESSAGE));
        itemCerrar.addActionListener(e -> System.exit(0));
    }
}
