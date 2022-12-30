package vistas;

import juego.Ficha;
import juego.Jugador;
import juego.Meme;
import org.json.JSONObject;
import trafico_datos.TraficoDatos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Falta la logica de monedas y la compra de skines

public class Juego extends JPanel implements Observer {

    private boolean esAnfitrion;
    private boolean miTurno;
    private int dado;

    private JButton btn_iniciar;
    private Jugador jugador;
    private Dimension tamano;
    private List<Jugador> jugadores;

    private JLabel lb_id;
    private JLabel lb_eventos;

    private JLabel lb_nickRojo;
    private JLabel lb_skinRojo;
    private JLabel lb_dadosRojo;

    private JLabel lb_nickAzul;
    private JLabel lb_skinAzul;
    private JLabel lb_dadosAzul;

    private JLabel lb_nickVerde;
    private JLabel lb_skinVerde;
    private JLabel lb_dadosVerde;

    private JLabel lb_nickAmarillo;
    private JLabel lb_skinAmarillo;
    private JLabel lb_dadosAmarillo;

    private JLabel lb_tablero;
    private JLabel lb_fondo;
    private JButton btn_tirarDados;

    private TraficoDatos traficoDatos;
    private PropertyChangeSupport notificador;

    private JTextArea txa_mensajes;
    private JScrollPane scroll_mensajes;
    private JTextField txt_mensaje;
    private JButton btn_enviarMensaje;
    private boolean click_mensaje;


    public Juego(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void init() {
        setLayout(null);
        esAnfitrion = false;
        jugadores = new ArrayList<>();

        //superior
        lb_id = new JLabel();
        lb_eventos = new JLabel();

        lb_id.setBounds(tamano.width / 2 - 50, 0, 100, 30);
        lb_eventos.setBounds(tamano.width / 2 - 150, 30, 300, 30);

        lb_id.setHorizontalAlignment(SwingConstants.CENTER);
        lb_eventos.setHorizontalAlignment(SwingConstants.CENTER);

        lb_id.setVerticalAlignment(SwingConstants.CENTER);
        lb_eventos.setVerticalAlignment(SwingConstants.CENTER);

        lb_id.setForeground(Color.white);
        lb_eventos.setForeground(Color.white);

        add(lb_id);
        add(lb_eventos);

        //rojo
        lb_skinRojo = new JLabel();
        lb_nickRojo = new JLabel();
        lb_dadosRojo = new JLabel();

        lb_skinRojo.setBounds(10, 60, 30, 30);
        lb_nickRojo.setBounds(50, 60, 120, 30);
        lb_dadosRojo.setBounds(180, 60, 30, 30);

        lb_nickRojo.setForeground(Color.white);
        lb_nickRojo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_nickRojo.setVerticalAlignment(SwingConstants.CENTER);

        add(lb_skinRojo);
        add(lb_nickRojo);
        add(lb_dadosRojo);

        //verde
        lb_skinVerde = new JLabel();
        lb_nickVerde = new JLabel();
        lb_dadosVerde = new JLabel();

        lb_skinVerde.setBounds(560, 60, 30, 30);
        lb_nickVerde.setBounds(430, 60, 120, 30);
        lb_dadosVerde.setBounds(390, 60, 30, 30);

        lb_nickVerde.setForeground(Color.white);
        lb_nickVerde.setHorizontalAlignment(SwingConstants.CENTER);
        lb_nickVerde.setVerticalAlignment(SwingConstants.CENTER);

        add(lb_skinVerde);
        add(lb_nickVerde);
        add(lb_dadosVerde);

        //azul
        lb_skinAzul = new JLabel();
        lb_nickAzul = new JLabel();
        lb_dadosAzul = new JLabel();

        lb_skinAzul.setBounds(10, 710, 30, 30);
        lb_nickAzul.setBounds(50, 710, 120, 30);
        lb_dadosAzul.setBounds(180, 710, 30, 30);

        lb_nickAzul.setForeground(Color.white);
        lb_nickAzul.setHorizontalAlignment(SwingConstants.CENTER);
        lb_nickAzul.setVerticalAlignment(SwingConstants.CENTER);

        add(lb_skinAzul);
        add(lb_nickAzul);
        add(lb_dadosAzul);

        //amarillo
        lb_dadosAmarillo = new JLabel();
        lb_nickAmarillo = new JLabel();
        lb_skinAmarillo = new JLabel();

        lb_dadosAmarillo.setBounds(390, 710, 30, 30);
        lb_skinAmarillo.setBounds(560, 710, 30, 30);
        lb_nickAmarillo.setBounds(430, 710, 120, 30);

        lb_nickAmarillo.setForeground(Color.white);
        lb_nickAmarillo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_nickAmarillo.setVerticalAlignment(SwingConstants.CENTER);

        add(lb_dadosAmarillo);
        add(lb_skinAmarillo);
        add(lb_nickAmarillo);

        //tablero
        lb_tablero = new JLabel();
        lb_tablero.setBounds(0, 100, 600, 600);

        BufferedImage bimg_tablero = null;
        Image img_tablero;
        ImageIcon icon_tablero;

        try {
            bimg_tablero = ImageIO.read(new File("Imagenes/tablero.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_tablero = bimg_tablero.getScaledInstance(lb_tablero.getWidth(), lb_tablero.getHeight(), Image.SCALE_SMOOTH);
        icon_tablero = new ImageIcon(img_tablero);

        lb_tablero.setIcon(icon_tablero);
        add(lb_tablero);

        //tirar_dados
        btn_tirarDados = new JButton();

        btn_tirarDados.setBounds(tamano.width / 2 - 45, 750, 90, 50);

        BufferedImage bimg_tirarDados = null;
        Image img_tirarDados;
        ImageIcon icon_tirarDados;

        try {
            bimg_tirarDados = ImageIO.read(new File("Imagenes/tirarDados.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_tirarDados = bimg_tirarDados.getScaledInstance(btn_tirarDados.getWidth(), btn_tirarDados.getHeight(), Image.SCALE_SMOOTH);
        icon_tirarDados = new ImageIcon(img_tirarDados);

        btn_tirarDados.setIcon(icon_tirarDados);

        btn_tirarDados.setBorder(null);
        btn_tirarDados.setContentAreaFilled(false);

        btn_tirarDados.setEnabled(false);

        add(btn_tirarDados);

        //mensajes
        txa_mensajes = new JTextArea();
        txt_mensaje = new JTextField();

        txt_mensaje.setBounds(0, 840, 230, 20);

        txa_mensajes.setEditable(false);
        txa_mensajes.setOpaque(false);
        txa_mensajes.setForeground(new Color(166, 166, 166));

        scroll_mensajes = new JScrollPane(txa_mensajes);
        scroll_mensajes.setBounds(0, 760, 230, 80);
        scroll_mensajes.getViewport().setOpaque(false);
        scroll_mensajes.setOpaque(false);
        scroll_mensajes.setBorder(null);

        txt_mensaje.setOpaque(true);
        txt_mensaje.setBackground(new Color(71, 70, 70));
        txt_mensaje.setBorder(null);
        txt_mensaje.setText("Envia un mensaje...");
        txt_mensaje.setForeground(new Color(130, 130, 130));

        add(txt_mensaje);
        add(scroll_mensajes);

        //enviarMensaje
        btn_enviarMensaje = new JButton();

        btn_enviarMensaje.setBounds(230, 840, 20, 20);

        BufferedImage bimg_enviarMensaje = null;
        Image img_enviarMensaje;
        ImageIcon icon_enviarMensaje;

        try {
            bimg_enviarMensaje = ImageIO.read(new File("Imagenes/enviar.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_enviarMensaje = bimg_enviarMensaje.getScaledInstance(btn_enviarMensaje.getWidth(), btn_enviarMensaje.getHeight(), Image.SCALE_SMOOTH);
        icon_enviarMensaje = new ImageIcon(img_enviarMensaje);

        btn_enviarMensaje.setIcon(icon_enviarMensaje);

        btn_enviarMensaje.setBorder(null);
        btn_enviarMensaje.setContentAreaFilled(false);

        add(btn_enviarMensaje);

        //fondo
        lb_fondo = new JLabel();

        lb_fondo.setBounds(0, 0, tamano.width, tamano.height);
        lb_fondo.setOpaque(true);
        lb_fondo.setBackground(new Color(61, 61, 61));

        add(lb_fondo);

        //listeners
        btn_tirarDados.addActionListener(e -> {
            JSONObject solicitud = new JSONObject();
            solicitud.put("tipo", "tirar_dados");
            solicitud.put("color", jugador.getColor());
            try {
                traficoDatos.envioDatos(solicitud);
            } catch (Exception error) {
                error.printStackTrace();
            }
        });

        txt_mensaje.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!click_mensaje) {
                    txt_mensaje.setText("");
                }
                click_mensaje = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_mensaje.getText().length() == 0) {
                    txt_mensaje.setText("Envia un mensaje...");
                    click_mensaje = false;
                }
            }
        });

        btn_enviarMensaje.addActionListener(e -> {
            String mensaje = txt_mensaje.getText();
            mensaje = mensaje.trim();
            if (mensaje.length() > 0) {

                JSONObject notificacion = new JSONObject();
                notificacion.put("tipo", "mensaje");
                notificacion.put("contenido", mensaje);
                try {
                    traficoDatos.envioDatos(notificacion);
                } catch (Exception error) {
                    System.out.println("Error al enviar el mensaje");
                }
                txa_mensajes.append("Yo: " + mensaje + " \n");
                txt_mensaje.setText("Envia un mensaje...");
                click_mensaje = false;
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

        if (tipo.equals("asignacion_de_color")) {

            String color = respuesta.getString("color");
            jugador.setColor(color);

        } else if (tipo.equals("actualizar_jugadores")) {

            // el servidor manda el nombre y color de cada jugador que pertenece a la partida
            // (incluyendo los datos del nuevo jugador)

            int cantidadJugadores = respuesta.getInt("cantidad_jugadores");
            List<Jugador> jugadores = new ArrayList<>();

            for (int i = 0; i < cantidadJugadores; i++) {

                String color_jugador = respuesta.getString("color_" + i);
                String nombre = respuesta.getString("jugador_" + i);
                String skin = respuesta.getString("skin_" + i);

                if (color_jugador.equals(this.jugador.getColor())) {
                    jugadores.add(jugador);
                } else {
                    Jugador jugador = new Jugador(nombre);
                    jugador.setColor(color_jugador);
                    jugador.setSkin(skin);
                    jugadores.add(jugador);
                }
            }

            // se obtienen solo los datos del nuevo jugador, esto se hace para solo se agregue una vez las Fichas
            // (JButton) de cada jugador al JPanel

            for (Jugador jugador : jugadores) {
                boolean esNuevo = false;
                for (int i = 0; i < this.jugadores.size(); i++) {
                    if (jugador.getColor().equals(this.jugadores.get(i).getColor())) {
                        esNuevo = true;
                    }
                }
                if (esNuevo == false) {
                    this.jugadores.add(jugador);
                    jugador.initFichas();
                    actualizarFichas(jugador);
                }
            }
            actualizarJugadores();
            actualizarSkins();

        } else if (tipo.equals("solicitud_denegada")) {

            String mensaje_error = respuesta.getString("mensaje");
            JOptionPane.showMessageDialog(null, mensaje_error);

        } else if (tipo.equals("partida_iniciada")) {

            String turno = respuesta.getString("turno");
            if (turno.equals(jugador.getColor())) {
                btn_tirarDados.setEnabled(true);
            }
            if (esAnfitrion) {
                btn_iniciar.setVisible(false);
                btn_tirarDados.setVisible(true);
            }
            String mensaje = respuesta.getString("mensaje");
            actualizarEvento(mensaje);

        } else if (tipo.equals("respuesta_tirar_dados")) {

            int dado = respuesta.getInt("dado");
            String color_jugador = respuesta.getString("color");
            if (color_jugador.equals(jugador.getColor())) {
                this.dado = dado;
                miTurno = true;
                btn_tirarDados.setEnabled(false);
            }

            actualizarDado(color_jugador, dado);
            String mensaje = respuesta.getString("mensaje");
            actualizarEvento(mensaje);

        } else if (tipo.equals("cambio_de_turno")) {

            String jugador = respuesta.getString("jugador_actual");
            if (jugador.equals(this.jugador.getColor())) {
                miTurno = true;
                btn_tirarDados.setEnabled(true);
                dado = 0;
            } else {
                miTurno = false;
                btn_tirarDados.setEnabled(false);
            }
            String mensaje = respuesta.getString("mensaje");
            actualizarEvento(mensaje);

        } else if (tipo.equals("respuesta_salir_de_casa")) {

            String color_jugador = respuesta.getString("color");
            Jugador jugador = new Jugador();
            int ficha_id = respuesta.getInt("ficha");

            int x = respuesta.getInt("x");
            int y = respuesta.getInt("y");
            Point nuevaPosicion = new Point(x, y);

            for (Jugador jugador_aux : this.jugadores) {
                if (jugador_aux.getColor().equals(color_jugador)) {
                    jugador = jugador_aux;
                }
            }

            for (Ficha ficha : jugador.getFichas()) {
                if (ficha.getId() == ficha_id) {
                    ficha.setLocation(nuevaPosicion);
                    ficha.setEnCasa(false);
                }
            }

            if (color_jugador.equals(this.jugador.getColor())) {
                miTurno = false;
            }
            repaint();

        } else if (tipo.equals("avanzar")) {

            String color_jugador = respuesta.getString("color");
            int ficha_id = respuesta.getInt("ficha");
            Jugador jugador = new Jugador();

            int x = respuesta.getInt("x");
            int y = respuesta.getInt("y");
            Point nuevaPosicion = new Point(x, y);

            for (Jugador jugador_aux : this.jugadores) {
                if (jugador_aux.getColor().equals(color_jugador)) {
                    jugador = jugador_aux;
                }
            }

            for (Ficha ficha : jugador.getFichas()) {
                if (ficha.getId() == ficha_id) {
                    ficha.setLocation(nuevaPosicion);
                }
            }
            repaint();

        } else if (tipo.equals("ficha_fin_recorrido")) {

            String color_jugador = respuesta.getString("color");
            int ficha_id = respuesta.getInt("ficha");
            Jugador jugador = new Jugador();

            int x = respuesta.getInt("x");
            int y = respuesta.getInt("y");
            Point nuevaPosicion = new Point(x, y);

            for (Jugador jugador_aux : this.jugadores) {
                if (jugador_aux.getColor().equals(color_jugador)) {
                    jugador = jugador_aux;
                }
            }

            for (Ficha ficha : jugador.getFichas()) {
                if (ficha.getId() == ficha_id) {
                    ficha.setLocation(nuevaPosicion);
                    if (jugador.getColor().equals(this.jugador.getColor())) {
                        // Se deshabilita la Ficha (JButton) para que no pueda ser seleccionada
                        ficha.setEnabled(false);
                        btn_tirarDados.setEnabled(true);
                        dado = 0;
                    }
                }
            }
            repaint();
            String mensaje = respuesta.getString("mensaje");
            actualizarEvento(mensaje);

        } else if (tipo.equals("ficha_eliminada")) {

            // datos de la ficha eliminada
            int ficha_id_1 = respuesta.getInt("ficha_1");
            String color_jugador_1 = respuesta.getString("color_1");
            Jugador jugador_1 = new Jugador();

            for (Jugador jugador : this.jugadores) {
                if (jugador.getColor().equals(color_jugador_1)) {
                    jugador_1 = jugador;
                }
            }

            for (Ficha ficha : jugador_1.getFichas()) {
                if (ficha.getId() == ficha_id_1) {
                    ficha.irCasa();
                    ficha.setEnCasa(true);
                }
            }

            // datos de la ficha que acaba de eliminar a la otra ficha
            int ficha_id_2 = respuesta.getInt("ficha_2");
            String color_jugador_2 = respuesta.getString("color_2");
            Jugador jugador_2 = new Jugador();

            int x = respuesta.getInt("x");
            int y = respuesta.getInt("y");

            Point nuevaPosicion = new Point(x, y);

            for (Jugador jugador : this.jugadores) {
                if (jugador.getColor().equals(color_jugador_2)) {
                    jugador_2 = jugador;
                }
            }

            if (jugador_2.getColor().equals(this.jugador.getColor())) {
                btn_tirarDados.setEnabled(true);
                dado = 0;
            }

            for (Ficha ficha : jugador_2.getFichas()) {
                if (ficha.getId() == ficha_id_2) {
                    ficha.setLocation(nuevaPosicion);
                    ficha.setEnCasa(false);
                }
            }
            repaint();
            String mensaje = respuesta.getString("mensaje");
            actualizarEvento(mensaje);

        } else if (tipo.equals("partida_finalizada")) {

            String color_ganador = respuesta.getString("color");
            Jugador jugador_ganador = new Jugador();

            for (Jugador jugador : jugadores) {
                if (jugador.getColor().equals(color_ganador)) {
                    jugador_ganador = jugador;
                }
            }

            if (jugador_ganador.getColor().equals(this.jugador.getColor())) {
                jugador_ganador.setMonedas(jugador_ganador.getMonedas() + 100);
            }

            String mensaje = respuesta.getString("mensaje");
            actualizarEvento(mensaje);

            JOptionPane.showMessageDialog(null, jugador_ganador.getNombre() + " acaba de ganar la partida!");
            notificador.firePropertyChange(tipo, 0, 1);
        } else if (tipo.equals("nuevo_mensaje")) {
            String mensaje = respuesta.getString("contenido");
            txa_mensajes.append(mensaje + " \n");
        } else if (tipo.equals("meme")){

            String color_jugador = respuesta.getString("color");
            String tipo_meme = respuesta.getString("meme");

            Meme meme = new Meme(this, color_jugador, tipo_meme);
            Thread hilo = new Thread(meme);
            hilo.start();
        }
    }

    private void actualizarJugadores() {
        for (Jugador jugador : this.jugadores) {
            switch (jugador.getColor()) {
                case "azul" -> lb_nickAzul.setText(jugador.getNombre());
                case "amarillo" -> lb_nickAmarillo.setText(jugador.getNombre());
                case "verde" -> lb_nickVerde.setText(jugador.getNombre());
                case "rojo" -> lb_nickRojo.setText(jugador.getNombre());
            }
        }
    }

    private void actualizarSkins() {
        for (Jugador jugador : this.jugadores) {
            StringBuilder ubicacion = new StringBuilder();
            ubicacion.append("Imagenes/");

            ubicacion.append(jugador.getColor());

            switch (jugador.getSkin()) {
                case "estandar":
                    ubicacion.append("_estandar.png");
                    break;
                case "flotador":
                    ubicacion.append("_flotador.png");
                    break;
                case "puzzle":
                    ubicacion.append("_puzzle.png");
                    break;
                case "mira":
                    ubicacion.append("_mira.png");
                    break;
            }

            new JLabel();
            JLabel lb_skin = switch (jugador.getColor()) {
                case "verde" -> lb_skinVerde;
                case "amarillo" -> lb_skinAmarillo;
                case "azul" -> lb_skinAzul;
                case "rojo" -> lb_skinRojo;
                default -> new JLabel();
            };

            BufferedImage bimg_skin = null;
            Image img_skin;
            ImageIcon icon_skin;

            try {
                bimg_skin = ImageIO.read(new File(ubicacion.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            img_skin = bimg_skin.getScaledInstance(lb_skin.getWidth(), lb_skin.getHeight(), Image.SCALE_SMOOTH);
            icon_skin = new ImageIcon(img_skin);

            lb_skin.setIcon(icon_skin);
        }
    }

    private void actualizarFichas(Jugador jugador) {
        List<Ficha> fichas = jugador.getFichas();

        for (Ficha ficha : fichas) {
            add(ficha, 1);

            if (jugador.getColor().equals(this.jugador.getColor())) {
                ficha.addActionListener(e -> {
                    if (miTurno && dado != 0) {
                        JSONObject solicitud = new JSONObject();
                        if (ficha.enCasa()) {
                            solicitud.put("tipo", "salir_casa");
                        } else {
                            solicitud.put("tipo", "avanzar");
                        }
                        solicitud.put("color", jugador.getColor());
                        solicitud.put("ficha", ficha.getId());
                        solicitud.put("dado", dado);

                        try {
                            traficoDatos.envioDatos(solicitud);
                        } catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                });
            }
            repaint();
        }
    }

    private void actualizarDado(String color_jugador_actual, int cantidad) {

        JLabel lb_dado = new JLabel();
        switch (color_jugador_actual) {
            case "verde":
                lb_dado = lb_dadosVerde;

                lb_dadosAmarillo.setIcon(null);
                lb_dadosAzul.setIcon(null);
                lb_dadosRojo.setIcon(null);
                break;
            case "amarillo":
                lb_dado = lb_dadosAmarillo;

                lb_dadosVerde.setIcon(null);
                lb_dadosAzul.setIcon(null);
                lb_dadosRojo.setIcon(null);
                break;
            case "azul":
                lb_dado = lb_dadosAzul;

                lb_dadosVerde.setIcon(null);
                lb_dadosAmarillo.setIcon(null);
                lb_dadosRojo.setIcon(null);
                break;
            case "rojo":
                lb_dado = lb_dadosRojo;

                lb_dadosVerde.setIcon(null);
                lb_dadosAmarillo.setIcon(null);
                lb_dadosAzul.setIcon(null);
                break;
        }

        StringBuilder ubicacion = new StringBuilder();
        ubicacion.append("Imagenes/dado_" + cantidad + ".png");

        BufferedImage bimg_dado = null;
        Image img_dado;
        ImageIcon icon_dado;

        try {
            bimg_dado = ImageIO.read(new File(ubicacion.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_dado = bimg_dado.getScaledInstance(lb_dado.getWidth(), lb_dado.getHeight(), Image.SCALE_SMOOTH);
        icon_dado = new ImageIcon(img_dado);

        lb_dado.setIcon(icon_dado);
    }

    public void esAnfitrion() {
        btn_tirarDados.setVisible(false);

        btn_iniciar = new JButton();
        btn_iniciar.setBounds(tamano.width / 2 - 45, 750, 90, 50);

        BufferedImage bimg_iniciar = null;
        Image img_iniciar;
        ImageIcon icon_iniciar;

        try {
            bimg_iniciar = ImageIO.read(new File("Imagenes/iniciar.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_iniciar = bimg_iniciar.getScaledInstance(btn_iniciar.getWidth(), btn_iniciar.getHeight(), Image.SCALE_SMOOTH);
        icon_iniciar = new ImageIcon(img_iniciar);
        btn_iniciar.setIcon(icon_iniciar);

        btn_iniciar.setBorder(null);
        btn_iniciar.setContentAreaFilled(false);

        add(btn_iniciar, 1);

        btn_iniciar.addActionListener(e -> {

            JSONObject datos = new JSONObject();
            datos.put("tipo", "iniciar_partida");
            datos.put("partida_id", lb_id.getText());
            esAnfitrion = true;
            try {
                traficoDatos.envioDatos(datos);
            } catch (Exception error) {
                error.printStackTrace();
            }

        });
    }

    public TraficoDatos getTraficoDatos() {
        return traficoDatos;
    }

    public void setTraficoDatos(TraficoDatos traficoDatos) {
        this.traficoDatos = traficoDatos;
    }

    public void setPartidaId(String id) {
        lb_id.setText(id);
    }

    private void actualizarEvento(String evento) {
        lb_eventos.setText(evento);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}