package servidor;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidor extends Thread{
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket socket;
    private FlujoDeTrabajo flujoDeTrabajo;

    public HiloServidor(Socket socket, FlujoDeTrabajo flujoDeTrabajo) {
        this.socket = socket;
        this.flujoDeTrabajo = flujoDeTrabajo;
    }

    @Override
    public void run() {
        System.out.println("Cliente conectado");

        try {
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            String mensajeDeEntrada = null;
            try {
                mensajeDeEntrada = (String) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.print("El cliente envio el siguiente mensaje: " + mensajeDeEntrada + "\n");

        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        if(mensajeDeEntrada.contains("GET FLU")){
            objectOutputStream.writeObject(flujoDeTrabajo);
            System.out.print("El servidor respondio el siguiente objeto: " + flujoDeTrabajo + "\n");
        } else if ( mensajeDeEntrada.contains("ADD FAS")){
            this.flujoDeTrabajo.getFases().add(new Fase(mensajeDeEntrada.substring(8), this.flujoDeTrabajo));
            String respuesta = "Se agrego la Fase: " + mensajeDeEntrada.substring(8);
            objectOutputStream.writeObject(flujoDeTrabajo);
        } else if ( mensajeDeEntrada.contains("ADD ACT")) {
            this.flujoDeTrabajo.getActividades().add(new Actividad(mensajeDeEntrada.substring(8), this.flujoDeTrabajo));
            String respuesta = "Se agrego la Actividad: " + mensajeDeEntrada.substring(8);
            objectOutputStream.writeObject(flujoDeTrabajo);
        }  else if ( mensajeDeEntrada.contains("ADD TAR")) {
            Actividad actividad = flujoDeTrabajo.getActividades().get(Integer.parseInt(mensajeDeEntrada.substring(8,9)));
            Fase fase = flujoDeTrabajo.getFases().get(Integer.parseInt(mensajeDeEntrada.substring(9,10)));
            Tarea tarea = new Tarea(mensajeDeEntrada.substring(10), actividad, fase ,this.flujoDeTrabajo);
            this.flujoDeTrabajo.getTareas().add(tarea);
            actividad.getTareas().add(tarea);
            fase.getTareas().add(tarea);
            String respuesta = "Se agrego la Actividad: " + mensajeDeEntrada.substring(10);
            objectOutputStream.writeObject(flujoDeTrabajo);
        } else {
            String respuesta = "No entiendo";
            objectOutputStream.writeObject(respuesta);
            System.out.print("El servidor respondio el siguiente objeto: " + respuesta + "\n");
        }
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Cliente desconectado");
    }
}
