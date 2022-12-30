package vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class Reglas extends JPanel {

    private JLabel lb_reglas;
    private JButton btn_salir;

    private Dimension tamano;

    private PropertyChangeSupport notificador;

    public Reglas(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(41, 41, 41));

        lb_reglas = new JLabel();
        btn_salir = new JButton();

        btn_salir.setBounds(tamano.width - 60, 80, 20, 20);
        lb_reglas.setBounds(0, 0, 600, 895);

        BufferedImage bimg_salir = null;
        BufferedImage bimg_reglas = null;

        Image img_salir;
        Image img_reglas;

        ImageIcon icon_salir;
        ImageIcon icon_reglas;

        try {
            bimg_salir = ImageIO.read(new File("Imagenes/x.png"));
            bimg_reglas = ImageIO.read(new File("Imagenes/panel_reglas.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_salir = bimg_salir.getScaledInstance(btn_salir.getWidth(), btn_salir.getHeight(), Image.SCALE_SMOOTH);
        img_reglas = bimg_reglas.getScaledInstance(lb_reglas.getWidth(), lb_reglas.getHeight(), Image.SCALE_SMOOTH);

        icon_salir = new ImageIcon(img_salir);
        icon_reglas = new ImageIcon(img_reglas);

        btn_salir.setIcon(icon_salir);
        lb_reglas.setIcon(icon_reglas);

        btn_salir.setBorder(null);
        btn_salir.setContentAreaFilled(false);

        add(btn_salir);
        add(lb_reglas);

        btn_salir.addActionListener(e -> {
            notificador.firePropertyChange("cerrar_reglas", 0, 1);
        });
    }
}
