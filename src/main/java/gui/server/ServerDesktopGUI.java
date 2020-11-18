package gui.server;

import flujodetrabajo.Actividad;
import servidor.Servidor;

import javax.swing.*;
import java.awt.event.*;

public class ServerDesktopGUI extends JDialog {
    private Servidor servidor;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonIniciarServer;
    private JTabbedPane tabbedPaneFlujoDeTrabajo;
    private JPanel panelActividad;
    private JPanel panelFase;
    private JPanel panelTarea;
    private JTextField textFieldActividad;
    private JButton addButton;
    private JPanel panelUsuario;

    public ServerDesktopGUI() {

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
        buttonIniciarServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servidor = new Servidor(666);
                servidor.start();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = new Actividad(textFieldActividad.getText(), servidor.getFlujoDeTrabajo());
                servidor.getFlujoDeTrabajo().getActividades().add(actividad);
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

    public static void main(String[] args) {
        ServerDesktopGUI dialog = new ServerDesktopGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
