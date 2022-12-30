package vistas;

import juego.Jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class Tienda extends JPanel {

    private Dimension tamano;
    private Jugador jugador;

    private JButton btn_salir;

    private JLabel lb_titulo;
    private JLabel lb_iconMoneda;
    private JLabel lb_monedas;

    private JLabel lb_titulo_estandar;
    private JLabel lb_titulo_flotador;
    private JLabel lb_titulo_puzzle;
    private JLabel lb_titulo_mira;

    private JLabel lb_moneda_flotador;
    private JLabel lb_moneda_puzzle;
    private JLabel lb_moneda_mira;

    private JLabel lb_precio_flotador;
    private JLabel lb_precio_puzzle;
    private JLabel lb_precio_mira;

    private JLabel lb_skin_estandar;
    private JLabel lb_skin_flotador;
    private JLabel lb_skin_puzzle;
    private JLabel lb_skin_mira;

    private JButton btn_comprar_flotador;
    private JButton btn_comprar_puzzle;
    private JButton btn_comprar_mira;

    private JButton btn_usar_estandar;
    private JButton btn_usar_flotador;
    private JButton btn_usar_puzzle;
    private JButton btn_usar_mira;

    private JButton btn_auxiliar;

    private PropertyChangeSupport notificador;

    public Tienda(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);

        init();
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(61, 61, 61));

        //superior
        btn_salir = new JButton();
        lb_titulo = new JLabel();
        lb_iconMoneda = new JLabel();
        lb_monedas = new JLabel();

        btn_salir.setBounds(tamano.width - 60, 30, 20, 20);
        lb_titulo.setBounds(tamano.width / 2 - 70, 30, 140, 80);
        lb_iconMoneda.setBounds(20, 20, 30, 30);
        lb_monedas.setBounds(50, 20, 50, 30);

        BufferedImage bimg_volver = null;
        BufferedImage bimg_tienda = null;
        BufferedImage bimg_moneda = null;

        Image img_volver;
        Image img_tienda;
        Image img_moneda;

        ImageIcon icon_volver;
        ImageIcon icon_tienda;
        ImageIcon icon_moneda;

        try {
            bimg_volver = ImageIO.read(new File("Imagenes/x.png"));
            bimg_tienda = ImageIO.read(new File("Imagenes/titulo_tienda.png"));
            bimg_moneda = ImageIO.read(new File("Imagenes/moneda.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_volver = bimg_volver.getScaledInstance(btn_salir.getWidth(), btn_salir.getHeight(), Image.SCALE_SMOOTH);
        img_tienda = bimg_tienda.getScaledInstance(lb_titulo.getWidth(), lb_titulo.getHeight(), Image.SCALE_SMOOTH);
        img_moneda = bimg_moneda.getScaledInstance(lb_iconMoneda.getWidth(), lb_iconMoneda.getHeight(), Image.SCALE_SMOOTH);

        icon_volver = new ImageIcon(img_volver);
        icon_tienda = new ImageIcon(img_tienda);
        icon_moneda = new ImageIcon(img_moneda);

        btn_salir.setIcon(icon_volver);
        lb_titulo.setIcon(icon_tienda);
        lb_iconMoneda.setIcon(icon_moneda);

        btn_salir.setBorder(null);
        btn_salir.setContentAreaFilled(false);

        lb_monedas.setForeground(Color.lightGray);
        lb_monedas.setHorizontalAlignment(SwingConstants.CENTER);
        lb_monedas.setVerticalAlignment(SwingConstants.CENTER);

        add(btn_salir);
        add(lb_titulo);
        add(lb_iconMoneda);
        add(lb_monedas);

        //titulos & precios
        lb_titulo_estandar = new JLabel("Estandar");
        lb_titulo_flotador = new JLabel("Flotador");
        lb_titulo_puzzle = new JLabel("Puzzle");
        lb_titulo_mira = new JLabel("Mira");

        lb_moneda_flotador = new JLabel();
        lb_moneda_puzzle = new JLabel();
        lb_moneda_mira = new JLabel();

        lb_precio_flotador = new JLabel("100");
        lb_precio_puzzle = new JLabel("250");
        lb_precio_mira = new JLabel("500");

        lb_titulo_estandar.setBounds(190, 170, 60, 30);
        lb_titulo_flotador.setBounds(190, 340, 60, 30);
        lb_titulo_puzzle.setBounds(190, 510, 60, 30);
        lb_titulo_mira.setBounds(190, 680, 60, 30);

        lb_moneda_flotador.setBounds(0, 340, 120, 30);
        lb_moneda_puzzle.setBounds(0, 510, 120, 30);
        lb_moneda_mira.setBounds(0, 680, 120, 30);

        lb_precio_flotador.setBounds(20, 340, 80, 30);
        lb_precio_puzzle.setBounds(20, 510, 80, 30);
        lb_precio_mira.setBounds(20, 680, 80, 30);

        BufferedImage bimg_moneda_precios = null;
        Image img_moneda_precios;
        ImageIcon icon_moneda_precios;

        try {
            bimg_moneda_precios = ImageIO.read(new File("Imagenes/moneda_precio.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_moneda_precios = bimg_moneda_precios.getScaledInstance(lb_moneda_flotador.getWidth(), lb_moneda_flotador.getHeight(), Image.SCALE_SMOOTH);

        icon_moneda_precios = new ImageIcon(img_moneda_precios);

        lb_moneda_flotador.setIcon(icon_moneda_precios);
        lb_moneda_puzzle.setIcon(icon_moneda_precios);
        lb_moneda_mira.setIcon(icon_moneda_precios);

        lb_titulo_estandar.setForeground(Color.lightGray);
        lb_titulo_flotador.setForeground(Color.lightGray);
        lb_titulo_puzzle.setForeground(Color.lightGray);
        lb_titulo_mira.setForeground(Color.lightGray);

        lb_precio_flotador.setForeground(Color.white);
        lb_precio_puzzle.setForeground(Color.white);
        lb_precio_mira.setForeground(Color.white);

        lb_titulo_estandar.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo_flotador.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo_puzzle.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo_mira.setHorizontalAlignment(SwingConstants.CENTER);

        lb_precio_flotador.setHorizontalAlignment(SwingConstants.CENTER);
        lb_precio_puzzle.setHorizontalAlignment(SwingConstants.CENTER);
        lb_precio_mira.setHorizontalAlignment(SwingConstants.CENTER);

        lb_titulo_estandar.setVerticalAlignment(SwingConstants.CENTER);
        lb_titulo_flotador.setVerticalAlignment(SwingConstants.CENTER);
        lb_titulo_puzzle.setVerticalAlignment(SwingConstants.CENTER);
        lb_titulo_mira.setVerticalAlignment(SwingConstants.CENTER);

        lb_precio_flotador.setVerticalAlignment(SwingConstants.CENTER);
        lb_precio_puzzle.setVerticalAlignment(SwingConstants.CENTER);
        lb_precio_mira.setVerticalAlignment(SwingConstants.CENTER);

        add(lb_titulo_estandar);
        add(lb_titulo_flotador);
        add(lb_titulo_puzzle);
        add(lb_titulo_mira);

        add(lb_precio_flotador);
        add(lb_precio_puzzle);
        add(lb_precio_mira);

        add(lb_moneda_flotador);
        add(lb_moneda_puzzle);
        add(lb_moneda_mira);

        //medio
        lb_skin_estandar = new JLabel();
        lb_skin_flotador = new JLabel();
        lb_skin_puzzle = new JLabel();
        lb_skin_mira = new JLabel();

        btn_comprar_flotador = new JButton();
        btn_comprar_puzzle = new JButton();
        btn_comprar_mira = new JButton();

        btn_usar_estandar = new JButton();
        btn_usar_flotador = new JButton();
        btn_usar_puzzle = new JButton();
        btn_usar_mira = new JButton();

        lb_skin_estandar.setBounds(50, 200, 340, 85);
        lb_skin_flotador.setBounds(50, 370, 340, 85);
        lb_skin_puzzle.setBounds(50, 540, 340, 85);
        lb_skin_mira.setBounds(50, 710, 340, 85);

        btn_comprar_flotador.setBounds(450, 390, 70, 40);
        btn_comprar_puzzle.setBounds(450, 560, 70, 40);
        btn_comprar_mira.setBounds(450, 730, 70, 40);

        btn_usar_estandar.setBounds(450, 220, 70, 40);
        btn_usar_flotador.setBounds(450, 390, 70, 40);
        btn_usar_puzzle.setBounds(450, 560, 70, 40);
        btn_usar_mira.setBounds(450, 730, 70, 40);

        BufferedImage bimg_skin_estandar = null;
        BufferedImage bimg_skin_flotador = null;
        BufferedImage bimg_skin_puzzle = null;
        BufferedImage bimg_skin_mira = null;
        BufferedImage bimg_comprar = null;
        BufferedImage bimg_usar = null;

        Image img_skin_estandar;
        Image img_skin_flotador;
        Image img_skin_puzzle;
        Image img_skin_mira;
        Image img_comprar;
        Image img_usar;

        ImageIcon icon_skin_estandar;
        ImageIcon icon_skin_flotador;
        ImageIcon icon_skin_puzzle;
        ImageIcon icon_skin_mira;
        ImageIcon icon_comprar;
        ImageIcon icon_usar;

        try {
            bimg_skin_estandar = ImageIO.read(new File("Imagenes/skin_estandar.png"));
            bimg_skin_flotador = ImageIO.read(new File("Imagenes/skin_flotador.png"));
            bimg_skin_puzzle = ImageIO.read(new File("Imagenes/skin_puzzle.png"));
            bimg_skin_mira = ImageIO.read(new File("Imagenes/skin_mira.png"));
            bimg_comprar = ImageIO.read(new File("Imagenes/comprar.png"));
            bimg_usar = ImageIO.read(new File("Imagenes/usar.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_skin_estandar = bimg_skin_estandar.getScaledInstance(lb_skin_estandar.getWidth(), lb_skin_estandar.getHeight(), Image.SCALE_SMOOTH);
        img_skin_flotador = bimg_skin_flotador.getScaledInstance(lb_skin_flotador.getWidth(), lb_skin_flotador.getHeight(), Image.SCALE_SMOOTH);
        img_skin_puzzle = bimg_skin_puzzle.getScaledInstance(lb_skin_puzzle.getWidth(), lb_skin_puzzle.getHeight(), Image.SCALE_SMOOTH);
        img_skin_mira = bimg_skin_mira.getScaledInstance(lb_skin_mira.getWidth(), lb_skin_mira.getHeight(), Image.SCALE_SMOOTH);
        img_comprar = bimg_comprar.getScaledInstance(btn_comprar_flotador.getWidth(), btn_comprar_flotador.getHeight(), Image.SCALE_SMOOTH);
        img_usar = bimg_usar.getScaledInstance(btn_usar_flotador.getWidth(), btn_usar_flotador.getHeight(), Image.SCALE_SMOOTH);

        icon_skin_estandar = new ImageIcon(img_skin_estandar);
        icon_skin_flotador = new ImageIcon(img_skin_flotador);
        icon_skin_puzzle = new ImageIcon(img_skin_puzzle);
        icon_skin_mira = new ImageIcon(img_skin_mira);
        icon_comprar = new ImageIcon(img_comprar);
        icon_usar = new ImageIcon(img_usar);

        lb_skin_estandar.setIcon(icon_skin_estandar);
        lb_skin_flotador.setIcon(icon_skin_flotador);
        lb_skin_puzzle.setIcon(icon_skin_puzzle);
        lb_skin_mira.setIcon(icon_skin_mira);

        btn_comprar_flotador.setIcon(icon_comprar);
        btn_comprar_puzzle.setIcon(icon_comprar);
        btn_comprar_mira.setIcon(icon_comprar);

        btn_usar_estandar.setIcon(icon_usar);
        btn_usar_flotador.setIcon(icon_usar);
        btn_usar_puzzle.setIcon(icon_usar);
        btn_usar_mira.setIcon(icon_usar);

        btn_comprar_flotador.setBorder(null);
        btn_comprar_puzzle.setBorder(null);
        btn_comprar_mira.setBorder(null);
        btn_usar_estandar.setBorder(null);
        btn_usar_flotador.setBorder(null);
        btn_usar_puzzle.setBorder(null);
        btn_usar_mira.setBorder(null);

        btn_comprar_flotador.setContentAreaFilled(false);
        btn_comprar_puzzle.setContentAreaFilled(false);
        btn_comprar_mira.setContentAreaFilled(false);
        btn_usar_estandar.setContentAreaFilled(false);
        btn_usar_flotador.setContentAreaFilled(false);
        btn_usar_puzzle.setContentAreaFilled(false);
        btn_usar_mira.setContentAreaFilled(false);

        add(lb_skin_estandar);
        add(lb_skin_flotador);
        add(lb_skin_puzzle);
        add(lb_skin_mira);

        add(btn_comprar_flotador);
        add(btn_comprar_puzzle);
        add(btn_comprar_mira);

        add(btn_usar_estandar);
        add(btn_usar_flotador);
        add(btn_usar_puzzle);
        add(btn_usar_mira);

        btn_auxiliar = btn_usar_estandar;
        btn_usar_estandar.setEnabled(false);
        btn_usar_flotador.setVisible(false);
        btn_usar_puzzle.setVisible(false);
        btn_usar_mira.setVisible(false);

        //listeners
        btn_salir.addActionListener(e -> {
            notificador.firePropertyChange("cerrar_tienda", 0, 1);
        });

        btn_usar_estandar.addActionListener(e -> {
            jugador.setSkin("estandar");

            btn_usar_estandar.setEnabled(false);
            btn_auxiliar.setEnabled(true);
            btn_auxiliar = btn_usar_estandar;
        });

        btn_usar_flotador.addActionListener(e -> {
            jugador.setSkin("flotador");

            btn_usar_flotador.setEnabled(false);
            btn_auxiliar.setEnabled(true);
            btn_auxiliar = btn_usar_flotador;
        });
        btn_comprar_flotador.addActionListener(e -> {
            int monedas = jugador.getMonedas();
            if (monedas >= 100) {

                btn_comprar_flotador.setEnabled(false);
                btn_comprar_flotador.setVisible(false);
                btn_usar_flotador.setVisible(true);

                jugador.getSkinsObtenidas().add("flotador");
                jugador.setMonedas(monedas - 100);

                actualizarMonedas();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes las monedas suficientes");
            }
        });

        btn_usar_puzzle.addActionListener(e -> {
            jugador.setSkin("puzzle");

            btn_usar_puzzle.setEnabled(false);
            btn_auxiliar.setEnabled(true);
            btn_auxiliar = btn_usar_puzzle;
        });
        btn_comprar_puzzle.addActionListener(e -> {
            int monedas = jugador.getMonedas();
            if (monedas >= 250) {

                btn_comprar_puzzle.setEnabled(false);
                btn_comprar_puzzle.setVisible(false);
                btn_usar_puzzle.setVisible(true);

                jugador.getSkinsObtenidas().add("puzzle");
                jugador.setMonedas(monedas - 250);
                actualizarMonedas();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes las monedas suficientes");
            }
        });

        btn_usar_mira.addActionListener(e -> {
            jugador.setSkin("mira");

            btn_usar_mira.setEnabled(false);
            btn_auxiliar.setEnabled(true);
            btn_auxiliar = btn_usar_mira;
        });
        btn_comprar_mira.addActionListener(e -> {
            int monedas = jugador.getMonedas();
            if (monedas >= 500) {

                btn_comprar_mira.setEnabled(false);
                btn_comprar_mira.setVisible(false);
                btn_usar_mira.setVisible(true);

                jugador.getSkinsObtenidas().add("mira");
                jugador.setMonedas(monedas - 500);
                actualizarMonedas();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes las monedas suficientes");
            }
        });
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    /**
     * Verifica si se debe mostrar el boton de comprar o de usar
     */
    public void actualizarBotones() {

        for (String skin : jugador.getSkinsObtenidas()) {

            switch (skin) {
                case "flotador":
                    btn_comprar_flotador.setVisible(false);
                    btn_usar_flotador.setVisible(true);
                    break;
                case "puzzle":
                    btn_comprar_puzzle.setVisible(false);
                    btn_usar_puzzle.setVisible(true);
                    break;
                case "mira":
                    btn_comprar_mira.setVisible(false);
                    btn_usar_mira.setVisible(true);
                    break;
            }
        }
    }

    public void actualizarMonedas(){
        lb_monedas.setText(jugador.getMonedas() + "");
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        lb_monedas.setText(jugador.getMonedas() + "");
    }
}
