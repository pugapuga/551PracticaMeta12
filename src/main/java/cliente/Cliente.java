package cliente;

import flujodetrabajo.FlujoDeTrabajo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    //Host del servidor
    private String HOST = "localhost";
    //Puerto del servidor
    private int PUERTO = 666;
    private FlujoDeTrabajo flujoDeTrabajo;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Cliente(String HOST, int PUERTO) {
        this.HOST = HOST;
        this.PUERTO = PUERTO;
        this.flujoDeTrabajo = new FlujoDeTrabajo("Default");
    }

    public FlujoDeTrabajo getFlujoDeTrabajo() {
        try {
            //Creo el socket para conectarme con el cliente
            Socket socket = new Socket(HOST, PUERTO);
            //Envio un mensaje al cliente
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectOutputStream.writeObject("GET FLU");
            //Recibo el mensaje del servidor
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.flujoDeTrabajo = (FlujoDeTrabajo) objectInputStream.readObject();
            this.objectOutputStream.close();
            this.objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.flujoDeTrabajo;
    }

    public void setFlujoDeTrabajo(FlujoDeTrabajo flujoDeTrabajo) {
        this.flujoDeTrabajo = flujoDeTrabajo;
    }

    public void enviarMensaje(String mensaje){
        try {
            //Creo el socket para conectarme con el cliente
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente conectado al servidor");

            //Envio un mensaje al cliente
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectOutputStream.writeObject(mensaje);
            System.out.println("El cliente envio el siguiente objeto: " + mensaje);

            //Recibo el mensaje del servidor
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();


            if (object.getClass().getCanonicalName().equals("flujodetrabajo.FlujoDeTrabajo")){
                this.flujoDeTrabajo = (FlujoDeTrabajo) object;
                System.out.println("El servidor envio el flujo de trabajo: " + flujoDeTrabajo);
            } else{
                System.out.println("El servidor envio el objeto: " + object);
            }
            this.objectOutputStream.close();
            this.objectInputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
