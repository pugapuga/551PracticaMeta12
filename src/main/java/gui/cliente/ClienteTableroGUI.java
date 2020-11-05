package gui.cliente;

import cliente.Cliente;
import flujodetrabajo.FlujoDeTrabajo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ClienteTableroGUI extends JDialog {
    private Cliente cliente;
    private FlujoDeTrabajo flujoDeTrabajo;
    private DefaultTableModel modelo;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tableTablero;
    private JButton buttonAgregarActividad;
    private JTextField textFieldActividad;
    private JButton buttonAgregarFase;
    private JTextField textFieldFase;
    private JButton buttonAgregarTarea;
    private JTextField textFieldTarea;
    private JComboBox comboBoxActividades;
    private JComboBox comboBoxFases;
    private JButton buttonActualizarCombos;

    public ClienteTableroGUI() {
        flujoDeTrabajo = new FlujoDeTrabajo("Mi Flujo de Trabajo");
        cliente = new Cliente("localhost", 666);

        HiloTablero hiloTablero = new HiloTablero(cliente,flujoDeTrabajo,modelo,tableTablero,comboBoxActividades,comboBoxFases);
        hiloTablero.start();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonAgregarActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("ADD ACT " + textFieldActividad.getText());
                //actualizarTablero();
            }
        });
        buttonAgregarFase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("ADD FAS " + textFieldFase.getText());

                //actualizarTablero();
            }
        });
        buttonAgregarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.enviarMensaje("ADD TAR " + comboBoxActividades.getSelectedIndex() + comboBoxFases.getSelectedIndex() + textFieldTarea.getText());
                //actualizarTablero();
            }
        });
        buttonActualizarCombos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flujoDeTrabajo = cliente.getFlujoDeTrabajo();
                comboBoxActividades.removeAllItems();
                for(int i = 0; i < flujoDeTrabajo.getActividades().size(); i++)
                    comboBoxActividades.addItem(flujoDeTrabajo.getActividades().get(i).getNombre());

                flujoDeTrabajo = cliente.getFlujoDeTrabajo();
                comboBoxFases.removeAllItems();
                for(int j = 0; j < flujoDeTrabajo.getFases().size(); j++)
                    comboBoxFases.addItem(flujoDeTrabajo.getFases().get(j).getNombre());
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void actualizarTablero(){
        flujoDeTrabajo = cliente.getFlujoDeTrabajo();

        comboBoxActividades.removeAllItems();
        for(int i = 0; i < flujoDeTrabajo.getActividades().size(); i++)
            comboBoxActividades.addItem(flujoDeTrabajo.getActividades().get(i).getNombre());

        comboBoxFases.removeAllItems();
        for(int i = 0; i < flujoDeTrabajo.getFases().size(); i++)
            comboBoxFases.addItem(flujoDeTrabajo.getFases().get(i).getNombre());

        modelo = new DefaultTableModel();
        tableTablero.setModel(modelo);
        for (int i = 0; i < flujoDeTrabajo.getFases().size(); i++) {
            modelo.addColumn(flujoDeTrabajo.getFases().get(i).getNombre(), flujoDeTrabajo.getFases().get(i).getTareas());
        }
    }


    public static void main(String[] args) {
        ClienteTableroGUI dialog = new ClienteTableroGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
