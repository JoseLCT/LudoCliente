package vistas;

import juego.Jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class Perfil extends JPanel {

    private Dimension tamano;
    private Jugador jugador;

    private JLabel lb_titulo;
    private JButton btn_salir;

    private JLabel lb_nombre;
    private JTextField txt_nombre;
    private JButton btn_editar;

    private JLabel lb_monedas;
    private JLabel lb_iconMoneda;
    private JButton btn_guardar;

    private PropertyChangeSupport notificador;
    private boolean click_nombre;

    public Perfil(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(61, 61, 61));
        click_nombre = true;

        //superior
        lb_titulo = new JLabel();
        btn_salir = new JButton();

        lb_titulo.setBounds(tamano.width / 2 - 70, 100, 140, 80);
        btn_salir.setBounds(tamano.width - 60, 30, 20, 20);

        BufferedImage bimg_titulo = null;
        BufferedImage bimg_salir = null;

        Image img_titulo;
        Image img_salir;

        ImageIcon icon_titulo;
        ImageIcon icon_salir;

        try {
            bimg_titulo = ImageIO.read(new File("Imagenes/titulo_perfil.png"));
            bimg_salir = ImageIO.read(new File("Imagenes/x.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_titulo = bimg_titulo.getScaledInstance(lb_titulo.getWidth(), lb_titulo.getHeight(), Image.SCALE_SMOOTH);
        img_salir = bimg_salir.getScaledInstance(btn_salir.getWidth(), btn_salir.getHeight(), Image.SCALE_SMOOTH);

        icon_titulo = new ImageIcon(img_titulo);
        icon_salir = new ImageIcon(img_salir);

        lb_titulo.setIcon(icon_titulo);
        btn_salir.setIcon(icon_salir);

        btn_salir.setBorder(null);
        btn_salir.setContentAreaFilled(false);

        add(lb_titulo);
        add(btn_salir);

        //medio
        lb_nombre = new JLabel("Nombre:");
        txt_nombre = new JTextField();
        lb_iconMoneda = new JLabel();
        lb_monedas = new JLabel();

        lb_nombre.setBounds(tamano.width / 2 - 100, 350, 60, 30);
        txt_nombre.setBounds(tamano.width / 2 + 40, 350, 120, 30);
        lb_iconMoneda.setBounds(tamano.width / 2 - 70, 400, 30, 30);
        lb_monedas.setBounds(tamano.width / 2 + 40, 400, 50, 30);

        BufferedImage bimg_moneda = null;
        Image img_moneda;
        ImageIcon icon_moneda;

        try {
            bimg_moneda = ImageIO.read(new File("Imagenes/moneda.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_moneda = bimg_moneda.getScaledInstance(lb_iconMoneda.getWidth(), lb_iconMoneda.getHeight(), Image.SCALE_SMOOTH);
        icon_moneda = new ImageIcon(img_moneda);

        lb_nombre.setForeground(Color.lightGray);
        txt_nombre.setForeground(Color.lightGray);
        lb_monedas.setForeground(Color.lightGray);

        lb_iconMoneda.setIcon(icon_moneda);

        txt_nombre.setEnabled(false);
        txt_nombre.setOpaque(false);
        txt_nombre.setBorder(null);

        add(lb_nombre);
        add(txt_nombre);
        add(lb_iconMoneda);
        add(lb_monedas);

        //inferior
        btn_editar = new JButton();
        btn_guardar = new JButton();

        btn_editar.setBounds(tamano.width / 2 - 45, 600, 90, 50);
        btn_guardar.setBounds(tamano.width / 2 - 45, 600, 90, 50);

        BufferedImage bimg_editar = null;
        BufferedImage bimg_guardar = null;

        Image img_editar;
        Image img_guardar;

        ImageIcon icon_editar;
        ImageIcon icon_guardar;

        try {
            bimg_editar = ImageIO.read(new File("Imagenes/editar.png"));
            bimg_guardar = ImageIO.read(new File("Imagenes/guardar.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_editar = bimg_editar.getScaledInstance(btn_editar.getWidth(), btn_editar.getHeight(), Image.SCALE_SMOOTH);
        img_guardar = bimg_guardar.getScaledInstance(btn_guardar.getWidth(), btn_guardar.getHeight(), Image.SCALE_SMOOTH);

        icon_editar = new ImageIcon(img_editar);
        icon_guardar = new ImageIcon(img_guardar);

        btn_editar.setIcon(icon_editar);
        btn_guardar.setIcon(icon_guardar);

        btn_editar.setBorder(null);
        btn_guardar.setBorder(null);

        btn_editar.setContentAreaFilled(false);
        btn_guardar.setContentAreaFilled(false);

        btn_guardar.setVisible(false);

        add(btn_editar);
        add(btn_guardar);

        //listeners
        btn_salir.addActionListener(e -> {
            notificador.firePropertyChange("cerrar_perfil", 0, 1);
        });

        btn_editar.addActionListener(e -> {
            btn_editar.setVisible(false);
            txt_nombre.setEnabled(true);
            btn_guardar.setVisible(true);
        });

        btn_guardar.addActionListener(e -> {
            String nuevoNombre = txt_nombre.getText();
            nuevoNombre = nuevoNombre.trim();
            if (nuevoNombre.length() > 0 && nuevoNombre.length() < 10) {
                btn_guardar.setVisible(false);
                txt_nombre.setEnabled(false);
                btn_editar.setVisible(true);

                jugador.setNombre(txt_nombre.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre valido");
            }
        });

        txt_nombre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!click_nombre) {
                    txt_nombre.setText("");
                }
                click_nombre = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_nombre.getText().length() == 0) {
                    txt_nombre.setText("Ingrese un nombre...");
                    click_nombre = false;
                }
            }
        });
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    public void actualizarMonedas() {
        lb_monedas.setText(jugador.getMonedas() + "");
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        txt_nombre.setText(jugador.getNombre());
        actualizarMonedas();
    }
}
