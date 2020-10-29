package gui.cliente;

import cliente.Cliente;
import servidor.Servidor;

public class ClienteGUI {
    public static void main(String[] args){
        Cliente cliente = new Cliente("localhost",666);
        cliente.enviarMensaje("GET FLU");
        cliente.enviarMensaje("ADD FAS ToDo");
        cliente.enviarMensaje("GET FLU");
    }
}
