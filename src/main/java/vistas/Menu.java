package vistas;

import juego.Jugador;
import trafico_datos.TraficoDatos;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;


//falta acabar la interfaz de las Reglas, Tienda y Menu
// falta hacer que se eliminen las partidas del servidor y que los sockets se cierren automaticamente cuando el cliente cierre su pestana
// falta agregar el chat y los emojis o memes
public class Menu extends JPanel implements PropertyChangeListener, Observer {

    private Dimension tamano;
    private TraficoDatos traficoDatos;
    private Jugador jugador;

    //superior
    private JButton btn_perfil;
    private JLabel lb_titulo;
    private JLabel lb_conectados;
    private JLabel lb_cantidadConectados;

    //medio
    private JLabel lb_importante;
    private JButton btn_reglas;
    private JLabel lb_sombraReglas;
    private JLabel lb_descubre;
    private JButton btn_nuevasFichas;
    private JLabel lb_sombraNuevasFichas;

    //inferior
    private JButton btn_tienda;
    private JLabel lb_tienda;
    private JButton btn_unirse;
    private JLabel lb_unirse;
    private JButton btn_crear;
    private JLabel lb_crear;

    //fondos
    private JLabel lb_fondoSuperior;
    private JLabel lb_fondoMedio;
    private JLabel lb_fondoInferior;

    //observer
    private PropertyChangeSupport notificador;

    public Menu(Dimension tamano) {
        this.tamano = tamano;
        this.notificador = new PropertyChangeSupport(this);
        init();
    }

    private void init() {
        setLayout(null);
        int mitadAncho = tamano.width / 2;

        //superior
        btn_perfil = new JButton();
        lb_titulo = new JLabel();
        lb_conectados = new JLabel();
        lb_cantidadConectados = new JLabel("1");

        btn_perfil.setBounds(25, 20, 40, 40);
        lb_titulo.setBounds(mitadAncho - 50, 18, 100, 55);
        lb_conectados.setBounds(tamano.width - 70, 20, 30, 30);
        lb_cantidadConectados.setBounds(tamano.width - 70, 55, 30, 10);

        BufferedImage bimg_perfil = null;
        BufferedImage bimg_titulo = null;
        BufferedImage bimg_conectados = null;

        Image img_perfil;
        Image img_titulo;
        Image img_conectados;

        ImageIcon icon_perfil;
        ImageIcon icon_titulo;
        ImageIcon icon_conectados;

        try {
            bimg_perfil = ImageIO.read(new File("Imagenes/perfil.png"));
            bimg_titulo = ImageIO.read(new File("Imagenes/titulo.png"));
            bimg_conectados = ImageIO.read(new File("Imagenes/conectados.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_perfil = bimg_perfil.getScaledInstance(btn_perfil.getWidth(), btn_perfil.getHeight(), Image.SCALE_SMOOTH);
        img_titulo = bimg_titulo.getScaledInstance(lb_titulo.getWidth(), lb_titulo.getHeight(), Image.SCALE_SMOOTH);
        img_conectados = bimg_conectados.getScaledInstance(lb_conectados.getWidth(), lb_conectados.getHeight(), Image.SCALE_SMOOTH);

        icon_perfil = new ImageIcon(img_perfil);
        icon_titulo = new ImageIcon(img_titulo);
        icon_conectados = new ImageIcon(img_conectados);

        btn_perfil.setIcon(icon_perfil);
        lb_titulo.setIcon(icon_titulo);
        lb_conectados.setIcon(icon_conectados);

        btn_perfil.setBorder(null);
        btn_perfil.setContentAreaFilled(false);

        lb_cantidadConectados.setHorizontalAlignment(SwingConstants.CENTER);
        lb_cantidadConectados.setVerticalAlignment(SwingConstants.CENTER);

        lb_cantidadConectados.setForeground(Color.white);

        add(btn_perfil);
        add(lb_titulo);
        add(lb_conectados);
        add(lb_cantidadConectados);

        //medio
        lb_importante = new JLabel();
        btn_reglas = new JButton();
        lb_sombraReglas = new JLabel();
        lb_descubre = new JLabel();
        btn_nuevasFichas = new JButton();
        lb_sombraNuevasFichas = new JLabel();

        lb_importante.setBounds(100, 120, 100, 60);
        btn_reglas.setBounds(100, 170, 400, 225);
        lb_sombraReglas.setBounds(105, 175, 400, 225);
        lb_descubre.setBounds(100, 420, 100, 60);
        btn_nuevasFichas.setBounds(100, 470, 400, 225);
        lb_sombraNuevasFichas.setBounds(105, 475, 400, 225);

        BufferedImage bimg_importante = null;
        BufferedImage bimg_reglas = null;
        BufferedImage bimg_descubre = null;
        BufferedImage bimg_nuevasFichas = null;

        Image img_importante;
        Image img_reglas;
        Image img_descubre;
        Image img_nuevasFichas;

        ImageIcon icon_importante;
        ImageIcon icon_reglas;
        ImageIcon icon_descubre;
        ImageIcon icon_nuevasFichas;

        try {
            bimg_importante = ImageIO.read(new File("Imagenes/importante.png"));
            bimg_reglas = ImageIO.read(new File("Imagenes/reglas.png"));
            bimg_descubre = ImageIO.read(new File("Imagenes/descubre.png"));
            bimg_nuevasFichas = ImageIO.read(new File("Imagenes/nuevas_fichas.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }

        img_importante = bimg_importante.getScaledInstance(lb_importante.getWidth(), lb_importante.getHeight(), Image.SCALE_SMOOTH);
        img_reglas = bimg_reglas.getScaledInstance(btn_reglas.getWidth(), btn_reglas.getHeight(), Image.SCALE_SMOOTH);
        img_descubre = bimg_descubre.getScaledInstance(lb_descubre.getWidth(), lb_descubre.getHeight(), Image.SCALE_SMOOTH);
        img_nuevasFichas = bimg_nuevasFichas.getScaledInstance(btn_nuevasFichas.getWidth(), btn_nuevasFichas.getHeight(), Image.SCALE_SMOOTH);

        icon_importante = new ImageIcon(img_importante);
        icon_reglas = new ImageIcon(img_reglas);
        icon_descubre = new ImageIcon(img_descubre);
        icon_nuevasFichas = new ImageIcon(img_nuevasFichas);

        lb_importante.setIcon(icon_importante);
        btn_reglas.setIcon(icon_reglas);
        lb_descubre.setIcon(icon_descubre);
        btn_nuevasFichas.setIcon(icon_nuevasFichas);

        btn_reglas.setBorder(null);
        btn_nuevasFichas.setBorder(null);
        btn_reglas.setContentAreaFilled(false);
        btn_nuevasFichas.setContentAreaFilled(false);

        lb_sombraReglas.setOpaque(true);
        lb_sombraNuevasFichas.setOpaque(true);

        lb_sombraReglas.setBackground(new Color(61, 61, 61));
        lb_sombraNuevasFichas.setBackground(new Color(61, 61, 61));

        add(lb_importante);
        add(btn_reglas);
        add(lb_sombraReglas);
        add(lb_descubre);
        add(btn_nuevasFichas);
        add(lb_sombraNuevasFichas);

        //inferior
        btn_tienda = new JButton();
        lb_tienda = new JLabel();
        btn_unirse = new JButton();
        lb_unirse = new JLabel();
        btn_crear = new JButton();
        lb_crear = new JLabel();

        btn_tienda.setBounds(mitadAncho - 160, 815, 35, 35);
        lb_tienda.setBounds(mitadAncho - 163, 850, 40, 25);
        btn_unirse.setBounds(mitadAncho - 35, 780, 70, 70);
        lb_unirse.setBounds(mitadAncho - 22, 850, 40, 25);
        btn_crear.setBounds(mitadAncho + 125, 815, 35, 35);
        lb_crear.setBounds(mitadAncho + 126, 852, 34, 20);

        BufferedImage bimg_tienda = null;
        BufferedImage bimg_txtTienda = null;
        BufferedImage bimg_unirse = null;
        BufferedImage bimg_txtUnirse = null;
        BufferedImage bimg_crear = null;
        BufferedImage bimg_txtCrear = null;

        Image img_tienda;
        Image img_txtTienda;
        Image img_unirse;
        Image img_txtUnirse;
        Image img_crear;
        Image img_txtCrear;

        ImageIcon icon_tienda;
        ImageIcon icon_txtTienda;
        ImageIcon icon_unirse;
        ImageIcon icon_txtUnirse;
        ImageIcon icon_crear;
        ImageIcon icon_txtCrear;

        try {
            bimg_tienda = ImageIO.read(new File("Imagenes/tienda.png"));
            bimg_txtTienda = ImageIO.read(new File("Imagenes/txtTienda.png"));
            bimg_unirse = ImageIO.read(new File("Imagenes/unirse.png"));
            bimg_txtUnirse = ImageIO.read(new File("Imagenes/txtUnirse.png"));
            bimg_crear = ImageIO.read(new File("Imagenes/crear.png"));
            bimg_txtCrear = ImageIO.read(new File("Imagenes/txtCrear.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }

        img_tienda = bimg_tienda.getScaledInstance(btn_tienda.getWidth(), btn_tienda.getHeight(), Image.SCALE_SMOOTH);
        img_txtTienda = bimg_txtTienda.getScaledInstance(lb_tienda.getWidth(), lb_tienda.getHeight(), Image.SCALE_SMOOTH);
        img_unirse = bimg_unirse.getScaledInstance(btn_unirse.getWidth(), btn_unirse.getHeight(), Image.SCALE_SMOOTH);
        img_txtUnirse = bimg_txtUnirse.getScaledInstance(lb_unirse.getWidth(), lb_unirse.getHeight(), Image.SCALE_SMOOTH);
        img_crear = bimg_crear.getScaledInstance(btn_crear.getWidth(), btn_crear.getHeight(), Image.SCALE_SMOOTH);
        img_txtCrear = bimg_txtCrear.getScaledInstance(lb_crear.getWidth(), lb_crear.getHeight(), Image.SCALE_SMOOTH);

        icon_tienda = new ImageIcon(img_tienda);
        icon_txtTienda = new ImageIcon(img_txtTienda);
        icon_unirse = new ImageIcon(img_unirse);
        icon_txtUnirse = new ImageIcon(img_txtUnirse);
        icon_crear = new ImageIcon(img_crear);
        icon_txtCrear = new ImageIcon(img_txtCrear);

        btn_tienda.setIcon(icon_tienda);
        lb_tienda.setIcon(icon_txtTienda);
        btn_unirse.setIcon(icon_unirse);
        lb_unirse.setIcon(icon_txtUnirse);
        btn_crear.setIcon(icon_crear);
        lb_crear.setIcon(icon_txtCrear);

        btn_tienda.setBorder(null);
        btn_unirse.setBorder(null);
        btn_crear.setBorder(null);
        btn_tienda.setContentAreaFilled(false);
        btn_unirse.setContentAreaFilled(false);
        btn_crear.setContentAreaFilled(false);

        add(btn_tienda);
        add(lb_tienda);
        add(btn_unirse);
        add(lb_unirse);
        add(btn_crear);
        add(lb_crear);

        //fondos
        lb_fondoSuperior = new JLabel();
        lb_fondoMedio = new JLabel();
        lb_fondoInferior = new JLabel();

        lb_fondoSuperior.setBounds(0, 0, tamano.width, 80);
        lb_fondoMedio.setBounds(0, 80, tamano.width, tamano.height - 200);
        lb_fondoInferior.setBounds(0, tamano.height - 120, tamano.width, 160);

        lb_fondoSuperior.setOpaque(true);
        lb_fondoMedio.setOpaque(true);
        lb_fondoInferior.setOpaque(true);

        lb_fondoSuperior.setBackground(new Color(61, 61, 61));
        lb_fondoMedio.setBackground(new Color(41, 41, 41));
        lb_fondoInferior.setBackground(new Color(61, 61, 61));

        add(lb_fondoSuperior);
        add(lb_fondoMedio);
        add(lb_fondoInferior);

        //superior
        btn_perfil.addActionListener(e -> {
            notificador.firePropertyChange("perfil", 0, 1);
        });

        //medio
        btn_reglas.addActionListener(e -> {
            notificador.firePropertyChange("reglas", 0, 1);
        });
        btn_nuevasFichas.addActionListener(e -> {
            notificador.firePropertyChange("tienda", 0, 1);
        });

        //inferior
        btn_tienda.addActionListener(e -> {
            notificador.firePropertyChange("tienda", 0, 1);
        });
        btn_unirse.addActionListener(e -> {
            String partida_id = JOptionPane.showInputDialog(null, "Ingrese el ID de la partida:");
            if (partida_id != null) {
                try {
                    JSONObject datos = new JSONObject();

                    datos.put("tipo", "unirse");
                    datos.put("partida_id", partida_id);
                    datos.put("jugador", jugador.getNombre());
                    datos.put("skin", jugador.getSkin());

                    traficoDatos.envioDatos(datos);

                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Error, no existe ninguna partida con ese ID");
                }
            }
        });
        btn_crear.addActionListener(e -> {
            try {
                JSONObject datos = new JSONObject();

                datos.put("tipo", "crear");
                datos.put("jugador", jugador.getNombre());
                datos.put("skin", jugador.getSkin());

                traficoDatos.envioDatos(datos);
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Error, no existe ninguna partida con ese ID");
            }
        });
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JSONObject respuesta = new JSONObject(evt.getNewValue().toString());

        String tipo = respuesta.getString("tipo");

        switch (tipo) {
            case "solicitud_denegada":
                JOptionPane.showMessageDialog(null, respuesta.getString("mensaje"));
                break;
            case "respuesta_crear":
                notificador.firePropertyChange(tipo, 0, evt.getNewValue());
                traficoDatos.removeObserver(this);
                break;
            case "respuesta_unirse":
                notificador.firePropertyChange(tipo, 0, evt.getNewValue());
                traficoDatos.removeObserver(this);
                break;
        }
    }

    public TraficoDatos getTraficoDatos() {
        return traficoDatos;
    }

    public void setTraficoDatos(TraficoDatos traficoDatos) {
        this.traficoDatos = traficoDatos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
