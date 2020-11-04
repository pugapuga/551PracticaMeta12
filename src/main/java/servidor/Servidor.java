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

public class Servidor {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    //puerto de nuestro servidor
    private int PUERTO;
    private FlujoDeTrabajo flujoDeTrabajo;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Servidor(int PUERTO) {
        this.PUERTO = PUERTO;
        this.iniciar();
    }

    public void iniciar() {
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

                System.out.println("Cliente conectado");

                this.objectInputStream = new ObjectInputStream(socket.getInputStream());
                String mensajeDeEntrada = (String) objectInputStream.readObject();
                System.out.print("El cliente envio el siguiente mensaje: " + mensajeDeEntrada + "\n");

                this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                if(mensajeDeEntrada.contains("GET FLU")){
                    objectOutputStream.writeObject(flujoDeTrabajo);
                    System.out.print("El servidor respondio el siguiente objeto: " + flujoDeTrabajo + "\n");
                } else if ( mensajeDeEntrada.contains("ADD FAS")){
                    this.flujoDeTrabajo.getFases().add(new Fase(mensajeDeEntrada.substring(8), this.flujoDeTrabajo));
                    String respuesta = "Se agrego la Fase: " + mensajeDeEntrada.substring(8);
                    objectOutputStream.writeObject(respuesta);
                } else if ( mensajeDeEntrada.contains("ADD ACT")) {
                    this.flujoDeTrabajo.getActividades().add(new Actividad(mensajeDeEntrada.substring(8), this.flujoDeTrabajo));
                    String respuesta = "Se agrego la Actividad: " + mensajeDeEntrada.substring(8);
                    objectOutputStream.writeObject(respuesta);
                }  else if ( mensajeDeEntrada.contains("ADD TAR")) {
                    Actividad actividad = flujoDeTrabajo.getActividades().get(Integer.parseInt(mensajeDeEntrada.substring(8,9)));
                    Fase fase = flujoDeTrabajo.getFases().get(Integer.parseInt(mensajeDeEntrada.substring(9,10)));
                    Tarea tarea = new Tarea(mensajeDeEntrada.substring(10), actividad, fase ,this.flujoDeTrabajo);
                    this.flujoDeTrabajo.getTareas().add(tarea);
                    actividad.getTareas().add(tarea);
                    fase.getTareas().add(tarea);
                    String respuesta = "Se agrego la Actividad: " + mensajeDeEntrada.substring(10);
                    objectOutputStream.writeObject(respuesta);
                } else {
                    String respuesta = "No entiendo";
                    objectOutputStream.writeObject(respuesta);
                    System.out.print("El servidor respondio el siguiente objeto: " + respuesta + "\n");
                }
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();

                System.out.println("Cliente desconectado");
            }

        } catch (IOException ex) {
            System.out.print(ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
