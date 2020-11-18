package servidor;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Thread{
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    //puerto de nuestro servidor
    private int PUERTO;
    private FlujoDeTrabajo flujoDeTrabajo;



    public Servidor(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public FlujoDeTrabajo getFlujoDeTrabajo() {
        return flujoDeTrabajo;
    }

    public void setFlujoDeTrabajo(FlujoDeTrabajo flujoDeTrabajo) {
        this.flujoDeTrabajo = flujoDeTrabajo;
    }

    @Override
    public void run() {
        try {
            this.flujoDeTrabajo = new FlujoDeTrabajo("Mi Flujo de Trabajo");
            System.out.println("Se creo el objeto: " + this.flujoDeTrabajo);

            //Creamos el socket del servidor
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor del infierno iniciado en el puerto " + this.PUERTO);

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                System.out.println("Esperando un nuevo cliente");

                socket = serverSocket.accept();

                HiloServidor hiloServidor = new HiloServidor(socket, this.flujoDeTrabajo);
                hiloServidor.start();
            }

        } catch (IOException ex) {
            System.out.print(ex);
        }
    }
}
