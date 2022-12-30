package juego;

import vistas.Juego;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Meme extends JLabel implements Runnable {

    private Juego juego;
    private String jugador;

    private String meme;
    private int tiempo;

    private AudioInputStream audio;
    private Clip clip;

    public Meme(Juego juego, String jugador, String meme) {
        this.juego = juego;
        this.jugador = jugador;
        this.meme = meme;
        initMeme();
    }

    @Override
    public void run() {

        try {
            clip.start();
            juego.add(this, 1);
            repaint();
            Thread.sleep(tiempo);
            remove(this);
            setVisible(false);
            clip.stop();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initMeme() {
        switch (jugador) {
            case "azul":
                setBounds(140,460,100,100);
                break;
            case "amarillo":
                setBounds(360,460,100,100);
                break;
            case "verde":
                setBounds(360,240,100,100);
                break;
            case "rojo":
                setBounds(140,240,100,100);
                break;
        }

        ImageIcon img_meme;
        Icon icon_meme;

        String ubicacion_gif = "";
        String ubicacion_audio = "";

        File archivo_audio;

        try {

            switch (meme) {
                case "tengo_miedo":
                    ubicacion_gif = "Memes/tengo_miedo.gif";
                    ubicacion_audio = "Memes/tengo_miedo.wav";
                    tiempo = 7800;
                    break;
                case "coincidencia_no_lo_creo":
                    ubicacion_gif = "Memes/coincidencia_no_lo_creo.gif";
                    ubicacion_audio = "Memes/coincidencia_no_lo_creo.wav";
                    tiempo = 3500;
                    break;
                case "ciudate_wazowski":
                    ubicacion_gif = "Memes/cuidate_wazowski.gif";
                    ubicacion_audio = "Memes/cuidate_wazowski.wav";
                    tiempo = 5500;
                    break;
                case "mission_complete":
                    ubicacion_gif = "Memes/mission_complete.gif";
                    ubicacion_audio = "Memes/mission_complete.wav";
                    tiempo = 6400;
                    break;
                case "mohana":
                    ubicacion_gif = "Memes/mohana.gif";
                    ubicacion_audio = "Memes/mohana.wav";
                    tiempo = 8000;
                    break;
                case "perros_mirandose":
                    ubicacion_gif = "Memes/perros_mirandose.gif";
                    ubicacion_audio = "Memes/perros_mirandose.wav";
                    tiempo = 8000;
                    break;
                case "aaaaa":
                    ubicacion_gif = "Memes/aaaaa.gif";
                    ubicacion_audio = "Memes/aaaaa.wav";
                    tiempo = 3800;
                    break;
                case "pez_bailando":
                    ubicacion_gif = "Memes/pez_bailando.gif";
                    ubicacion_audio = "Memes/pez_bailando.wav";
                    tiempo = 9000;
                    break;
                case "bro":
                    ubicacion_gif = "Memes/bro.gif";
                    ubicacion_audio = "Memes/bro.wav";
                    tiempo = 7000;
                    break;
            }

            archivo_audio = new File(ubicacion_audio);
            audio = AudioSystem.getAudioInputStream(archivo_audio);
            clip = AudioSystem.getClip();
            clip.open(audio);

        } catch (Exception e) {
            e.printStackTrace();
        }

        img_meme = new ImageIcon(ubicacion_gif);
        icon_meme = new ImageIcon(img_meme.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT));
        setIcon(icon_meme);
    }
}
