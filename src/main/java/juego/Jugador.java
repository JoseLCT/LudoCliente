package juego;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private String color;
    private String skin;
    private ArrayList<String> skinsObtenidas;
    private int monedas;

    private List<Ficha> fichas;

    public Jugador(String nombre) {
        skin = "estandar";
        skinsObtenidas = new ArrayList<>();
        skinsObtenidas.add("estandar");
        monedas = 1000;
        this.nombre = nombre;
    }

    public Jugador() {
        skin = "estandar";
        skinsObtenidas = new ArrayList<>();
        skinsObtenidas.add("estandar");
        monedas = 0;
    }


    public void initFichas() {
        fichas = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Ficha ficha = new Ficha(i, color, skin);
            fichas.add(ficha);
        }
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public ArrayList<String> getSkinsObtenidas() {
        return skinsObtenidas;
    }

    public void setSkinsObtenidas(ArrayList<String> skinsObtenidas) {
        this.skinsObtenidas = skinsObtenidas;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }
}
