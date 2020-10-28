package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    //puerto de nuestro servidor
    private int PUERTO;

    public Servidor(int PUERTO) {
        this.PUERTO = PUERTO;
        this.iniciar();
    }

    public void iniciar() {
        try {
            //Creamos el socket del servidor
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor del infierno iniciado en el puerto " + this.PUERTO);

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                System.out.println("Esperando un nuevo cliente");

                socket = serverSocket.accept();

                System.out.println("Cliente conectado");

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String mensajeDeEntrada = dataInputStream.readUTF();
                System.out.print("El cliente envio el siguiente mensaje: " + mensajeDeEntrada + "\n");

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String respuesta = "Saludos desde el infierno";
                dataOutputStream.writeUTF(respuesta);

                System.out.print("El servidor respondio el siguiente mensaje: " + respuesta + "\n");

                socket.close();

                System.out.println("Cliente desconectado");
            }

        } catch (IOException ex) {
            System.out.print(ex);
        }
    }
}
