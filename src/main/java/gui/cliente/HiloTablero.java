package gui.cliente;

import cliente.Cliente;
import flujodetrabajo.FlujoDeTrabajo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HiloTablero extends Thread{
    private Cliente cliente;
    private FlujoDeTrabajo flujoDeTrabajo;
    private DefaultTableModel modelo;
    private JTable tableTablero;
    private JComboBox comboBoxActividades;
    private JComboBox comboBoxFases;

    public HiloTablero(Cliente cliente, FlujoDeTrabajo flujoDeTrabajo, DefaultTableModel modelo, JTable tableTablero, JComboBox comboBoxActividades, JComboBox comboBoxFases) {
        this.cliente = cliente;
        this.flujoDeTrabajo = flujoDeTrabajo;
        this.modelo = modelo;
        this.tableTablero = tableTablero;
        this.comboBoxActividades = comboBoxActividades;
        this.comboBoxFases = comboBoxFases;
    }

    @Override
    public void run() {
        System.out.println("Se inicio thread");
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flujoDeTrabajo = cliente.getFlujoDeTrabajo();
            modelo = new DefaultTableModel();
            tableTablero.setModel(modelo);

            for (int i = 0; i < flujoDeTrabajo.getFases().size(); i++) {
                modelo.addColumn(flujoDeTrabajo.getFases().get(i).getNombre(), flujoDeTrabajo.getFases().get(i).getTareas());
            }

            comboBoxActividades.removeAllItems();
            for (int j = 0; j < flujoDeTrabajo.getActividades().size(); j++)
                comboBoxActividades.addItem(flujoDeTrabajo.getActividades().get(j).getNombre());

            comboBoxFases.removeAllItems();
            for (int k = 0; k < flujoDeTrabajo.getFases().size(); k++)
                comboBoxFases.addItem(flujoDeTrabajo.getFases().get(k).getNombre());

        }
    }
}
