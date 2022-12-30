package trafico_datos;

import org.json.JSONObject;
import vistas.Observer;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TraficoDatos implements Runnable {

    private Socket socket;
    private BufferedReader entrada;
    private BufferedWriter salida;

    private PropertyChangeSupport notificador;

    public TraficoDatos() throws Exception {
        this.socket = new Socket("localhost", 12345);
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        notificador = new PropertyChangeSupport(this);
    }

    public void addObserver(Observer observador) {
        notificador.addPropertyChangeListener(observador);
    }

    public void removeObserver(Observer observer){
        notificador.removePropertyChangeListener(observer);
    }

    public void envioDatos(JSONObject datos) throws Exception {
        salida.write(datos.toString());
        salida.newLine();
        salida.flush();
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                //se convierte los datos en String para poder convertirlos a JSON
                String txtDatos = entrada.readLine();
                JSONObject datos = new JSONObject(txtDatos);

                String tipo = datos.getString("tipo");
                notificador.firePropertyChange(tipo, null, datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
