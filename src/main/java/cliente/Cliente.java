package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    //Host del servidor
    private String HOST = "localhost";
    //Puerto del servidor
    private int PUERTO = 666;

    public Cliente(String HOST, int PUERTO) {
        this.HOST = HOST;
        this.PUERTO = PUERTO;
    }

    public void enviarMensaje(String mensaje){
        try {
            //Creo el socket para conectarme con el cliente
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Cliente conectado al servidor");

            //Envio un mensaje al cliente
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(mensaje);
            System.out.println("El cliente envio el siguiente mensaje: " + mensaje);

            //Recibo el mensaje del servidor
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String respuesta = dataInputStream.readUTF();

            System.out.println("El servidor responio el siguiente mensaje: "+ respuesta);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
