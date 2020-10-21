package gui;

import escuela.Alumno;
import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;

public class TableroGUI extends JDialog {
    private FlujoDeTrabajo flujoDeTrabajo;
    private DefaultTableModel modelo;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonTest;
    private JButton buttonCrearFDT;
    private JButton buttonAgregarActividad;
    private JButton buttonAgregarFase;
    private JButton buttonAgregarTarea;
    private JTextField textFieldActividad;
    private JTextField textFieldFase;
    private JTextField textFieldTarea;
    private JComboBox comboBoxActividades;
    private JComboBox comboBoxFases;
    private JTable tableTablero;
    private JPanel panel;
    private JButton buttonToolBarGrabar;
    private JButton buttonToolBarEjemplo1;
    private JButton buttonToolBarEjemplo2;
    private JButton buttonToolBarRecuperar;
    private JTextField textFieldTareaActividad;
    private JTextField textFieldTareaFase;
    private JList listActividad;

    public TableroGUI() {
        flujoDeTrabajo = new FlujoDeTrabajo("Mi Flujo de Trabajo");
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
        buttonTest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonTest.setText("Ouch!");
            }
        });
        buttonTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonTest.setText("Hola!");
            }
        });
        buttonTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonTest.setText("Bye!");
            }
        });
        buttonAgregarActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = new Actividad(textFieldActividad.getText(), flujoDeTrabajo);
                flujoDeTrabajo.getActividades().add(actividad);
                actualizarTablero();
                //JOptionPane.showMessageDialog(null, flujoDeTrabajo);
            }
        });
        buttonAgregarFase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fase fase = new Fase(textFieldFase.getText(), flujoDeTrabajo);
                flujoDeTrabajo.getFases().add(fase);
                actualizarTablero();
                //JOptionPane.showMessageDialog(null, flujoDeTrabajo);
            }
        });
        buttonAgregarTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = flujoDeTrabajo.getActividades().get(comboBoxActividades.getSelectedIndex());
                Fase fase = flujoDeTrabajo.getFases().get(comboBoxFases.getSelectedIndex());

                Tarea tarea = new Tarea(textFieldTarea.getText(), actividad, fase, flujoDeTrabajo);

                actividad.getTareas().add(tarea);
                fase.getTareas().add(tarea);
                flujoDeTrabajo.getTareas().add(tarea);

                actualizarTablero();
                //JOptionPane.showMessageDialog(null, flujoDeTrabajo);
            }
        });

        buttonToolBarEjemplo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("flujodetrabajo.dat");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    //byte b = 00;
                    //fileOutputStream.write(b);
                    DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

                    dataOutputStream.writeUTF("Hola mundo!");
                    bufferedOutputStream.flush();

                    dataOutputStream.close();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                try {
                    FileInputStream fileInputStream = new FileInputStream("flujodetrabajo.dat");
                    //byte b[];
                    //final int read = fileInputStream.read(b[]);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);


                    final String s = dataInputStream.readUTF();

                    dataInputStream.close();
                    fileInputStream.close();

                    JOptionPane.showMessageDialog(null, s);

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        buttonToolBarEjemplo2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Alumno alumno = new Alumno("Pepito");

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("flujodetrabajo.dat");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

                    objectOutputStream.writeObject(alumno);
                    bufferedOutputStream.flush();

                    objectOutputStream.close();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                try {
                    FileInputStream fileInputStream = new FileInputStream("flujodetrabajo.dat");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    final Alumno a = (Alumno) objectInputStream.readObject();

                    objectInputStream.close();
                    fileInputStream.close();

                    JOptionPane.showMessageDialog(null, a);

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        buttonToolBarGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("flujodetrabajo.dat");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

                    objectOutputStream.writeObject(flujoDeTrabajo);
                    bufferedOutputStream.flush();

                    objectOutputStream.close();
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        buttonToolBarRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("flujodetrabajo.dat");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    flujoDeTrabajo = (FlujoDeTrabajo) objectInputStream.readObject();

                    objectInputStream.close();
                    fileInputStream.close();

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                actualizarTablero();
            }
        });
    }
    private void actualizarTablero(){
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TableroGUI dialog = new TableroGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
