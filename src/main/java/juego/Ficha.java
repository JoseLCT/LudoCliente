package juego;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Ficha extends JButton {

    private int id;
    private Point casa;
    private boolean enCasa;

    public Ficha(int id, String color, String skin) {
        this.id = id;
        initPosicion(id, color);
        initSkin(color, skin);
        enCasa = true;
    }

    /**
     * Se verifica la posicion que debe tener la ficha al momento de estar en casa y se asignan los valores (X, Y) a la
     * variable casa.
     */
    private void initPosicion(int id, String color){
        this.setSize(40,40);

        if (color.equals("azul")){
            switch (id){
                case 1:
                    casa = new Point(80, 540);
                    break;
                case 2:
                    casa = new Point(120, 540);
                    break;
                case 3:
                    casa = new Point(80, 580);
                    break;
                case 4:
                    casa = new Point(120, 580);
                    break;
            }
        } else if (color.equals("amarillo")){
            switch (id){
                case 1:
                    casa = new Point(440, 540);
                    break;
                case 2:
                    casa = new Point(480, 540);
                    break;
                case 3:
                    casa = new Point(440, 580);
                    break;
                case 4:
                    casa = new Point(480, 580);
                    break;
            }
        } else if (color.equals("verde")){
            switch (id){
                case 1:
                    casa = new Point(440, 180);
                    break;
                case 2:
                    casa = new Point(480, 180);
                    break;
                case 3:
                    casa = new Point(440, 220);
                    break;
                case 4:
                    casa = new Point(480, 220);
                    break;
            }
        } else if (color.equals("rojo")){
            switch (id){
                case 1:
                    casa = new Point(80, 180);
                    break;
                case 2:
                    casa = new Point(120, 180);
                    break;
                case 3:
                    casa = new Point(80, 220);
                    break;
                case 4:
                    casa = new Point(120, 220);
                    break;
            }
        }
        setLocation(casa);
    }

    /**
     * Se busca la imagen que corresponde a la ficha dentro de la carpeta Imagenes, una vez encontrada se la agrega
     * como icono con el setIcon.
     */
    private void initSkin(String color, String skin) {
        StringBuilder ubicacion = new StringBuilder();
        ubicacion.append("Imagenes/");

        ubicacion.append(color);

        switch (skin) {
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

        BufferedImage bimg_skin = null;
        Image img_skin;
        ImageIcon icon_skin;

        try {
            bimg_skin = ImageIO.read(new File(ubicacion.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_skin = bimg_skin.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        icon_skin = new ImageIcon(img_skin);

        this.setIcon(icon_skin);
        this.setBorder(null);
        this.setContentAreaFilled(false);
    }

    public void irCasa(){
        this.setLocation(casa);
    }

    public boolean enCasa() {
        return enCasa;
    }

    public void setEnCasa(boolean enCasa) {
        this.enCasa = enCasa;
    }

    public int getId() {
        return id;
    }
}
