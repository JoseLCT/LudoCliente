package vistas;

import juego.Jugador;
import org.json.JSONObject;
import trafico_datos.TraficoDatos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class Ventana extends JFrame implements PropertyChangeListener {

    private Jugador jugador;

    private Menu menu;
    private Perfil perfil;
    private Reglas reglas;
    private Tienda tienda;
    private Juego juego;

    private TraficoDatos traficoDatos;

    private AudioInputStream audio;
    private Clip clip;

    private JMenuBar menuBar;

    private JMenu menu_memes;
    private JMenuItem meme_tengoMiedo;
    private JMenuItem meme_coincidenciaNoLoCreo;
    private JMenuItem meme_cuidateWazowski;
    private JMenuItem meme_missionComplete;
    private JMenuItem meme_mohana;
    private JMenuItem meme_perrosMirandose;
    private JMenuItem meme_aaaaa;
    private JMenuItem meme_pezBailando;
    private JMenuItem meme_bro;

    //Ventana donde se irán agregando los paneles del cliente
    public Ventana() {
        init();
        initAudio();
        setResizable(false);
    }

    private void init() {
        setLayout(new BorderLayout());
        setSize(600, 915);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jugador = new Jugador("NUR");

        menu = new Menu(getSize());
        perfil = new Perfil(getSize());
        reglas = new Reglas(getSize());
        tienda = new Tienda(getSize());
        juego = new Juego(getSize());

        menu.addObserver(this);
        perfil.addObserver(this);
        reglas.addObserver(this);
        tienda.addObserver(this);
        juego.addObserver(this);

        menu.setJugador(jugador);
        perfil.setJugador(jugador);
        tienda.setJugador(jugador);
        juego.setJugador(jugador);

        try {
            traficoDatos = new TraficoDatos();
            Thread hilo = new Thread(traficoDatos);
            hilo.start();
            menu.setTraficoDatos(traficoDatos);
            traficoDatos.addObserver(menu);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede conectar al servidor");
        }

        add(menu);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String tipo = evt.getPropertyName();
        JSONObject respuesta;
        String partida_id;

        switch (tipo) {
            case "perfil":
                menu.setVisible(false);
                add(perfil);
                perfil.setVisible(true);
                perfil.actualizarMonedas();
                repaint();
                break;
            case "tienda":
                menu.setVisible(false);
                add(tienda);
                tienda.setVisible(true);
                tienda.actualizarBotones();
                tienda.actualizarMonedas();
                repaint();
                break;
            case "reglas":
                menu.setVisible(false);
                add(reglas);
                reglas.setVisible(true);
                repaint();
                break;
            case "cerrar_perfil":
                perfil.setVisible(false);
                menu.setVisible(true);
                remove(perfil);
                repaint();
                break;
            case "cerrar_reglas":
                reglas.setVisible(false);
                menu.setVisible(true);
                remove(reglas);
                repaint();
                break;
            case "cerrar_tienda":
                tienda.setVisible(false);
                menu.setVisible(true);
                remove(tienda);
                repaint();
                break;
            case "respuesta_crear":
                respuesta = new JSONObject(evt.getNewValue().toString());
                partida_id = respuesta.getString("partida_id");

                menu.setVisible(false);
                add(juego);
                juego.setVisible(true);

                juego.setTraficoDatos(traficoDatos);
                juego.setPartidaId(partida_id);
                juego.esAnfitrion();

                traficoDatos.removeObserver(menu);
                traficoDatos.addObserver(juego);

                cambiarMusica("Audios/audio_partida.wav");
                initNotificaciones();
                repaint();
                break;
            case "respuesta_unirse":
                respuesta = new JSONObject(evt.getNewValue().toString());
                partida_id = respuesta.getString("partida_id");

                menu.setVisible(false);
                add(juego);
                juego.setVisible(true);

                juego.setTraficoDatos(traficoDatos);
                juego.setPartidaId(partida_id);

                traficoDatos.removeObserver(menu);
                traficoDatos.addObserver(juego);

                cambiarMusica("Audios/audio_partida.wav");
                initNotificaciones();
                repaint();
                break;
            case "partida_finalizada":

                traficoDatos.removeObserver(juego);

                juego.setVisible(false);
                menu.setVisible(true);
                remove(juego);

                juego = new Juego(getSize());
                juego.addObserver(this);

                traficoDatos.addObserver(menu);
                juego.setJugador(perfil.getJugador());

                setJMenuBar(null);

                cambiarMusica("Audios/audio_menu.wav");
                repaint();
                break;
        }
    }

    private void initAudio() {
        try {
            File archivo_audio = new File("Audios/audio_menu.wav");

            audio = AudioSystem.getAudioInputStream(archivo_audio);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("Error al reproducir la musica");
        }
    }

    private void cambiarMusica(String ubicacion) {

        clip.stop();

        try {
            File archivo_audio = new File(ubicacion);

            audio = AudioSystem.getAudioInputStream(archivo_audio);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("Error al reproducir la musica");
        }
    }

    private void initNotificaciones() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //memes
        menu_memes = new JMenu("Memes");
        menuBar.add(menu_memes);

        meme_tengoMiedo = new JMenuItem("Tengo miedo");
        meme_coincidenciaNoLoCreo = new JMenuItem("Coincidencia? No lo creo");
        meme_cuidateWazowski = new JMenuItem("Ciudate Wazowski");
        meme_missionComplete = new JMenuItem("Mission complete");
        meme_mohana = new JMenuItem("Mohana");
        meme_perrosMirandose = new JMenuItem("Perros mirandose");
        meme_aaaaa = new JMenuItem("AAAAAA");
        meme_pezBailando = new JMenuItem("Pez bailando");
        meme_bro = new JMenuItem("Bro");

        menu_memes.add(meme_tengoMiedo);
        menu_memes.add(meme_coincidenciaNoLoCreo);
        menu_memes.add(meme_cuidateWazowski);
        menu_memes.add(meme_missionComplete);
        menu_memes.add(meme_mohana);
        menu_memes.add(meme_perrosMirandose);
        menu_memes.add(meme_aaaaa);
        menu_memes.add(meme_pezBailando);
        menu_memes.add(meme_bro);

        meme_tengoMiedo.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "tengo_miedo");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_coincidenciaNoLoCreo.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "coincidencia_no_lo_creo");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_cuidateWazowski.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "ciudate_wazowski");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_missionComplete.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "mission_complete");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_mohana.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "mohana");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_perrosMirandose.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "perros_mirandose");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_aaaaa.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "aaaaa");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_pezBailando.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "pez_bailando");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });

        meme_bro.addActionListener(e -> {
            JSONObject notificacion = new JSONObject();
            notificacion.put("tipo", "meme");
            notificacion.put("meme", "bro");
            try {
                traficoDatos.envioDatos(notificacion);
            } catch (Exception error) {
                System.out.println("Error al enviar el meme");
            }
        });
    }
}
