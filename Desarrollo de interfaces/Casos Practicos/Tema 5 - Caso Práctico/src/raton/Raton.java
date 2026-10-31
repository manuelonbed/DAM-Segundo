package raton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Raton extends JFrame {

    // Panel principal donde se agregan los componentes
    private JPanel contentPane;
    // ComboBox para seleccionar el botón del ratón
    private JComboBox combo1;
    // Spinner para ajustar la velocidad del ratón
    private JSpinner spinner1;

    public static void main(String[] args) {
        // Ejecuta la interfaz gráfica en el hilo de eventos
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Crea una instancia de Raton y la muestra
                    Raton frame = new Raton();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(); // Maneja posibles excepciones
                }
            }
        });
    }

    public Raton() {
        // Configura la operación de cierre de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establece el tamaño y la posición de la ventana
        setBounds(100, 100, 450, 300);
        
        // Inicializa el panel de contenido y establece sus bordes
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Usar diseño absoluto
        
        // Crea y configura la etiqueta para la velocidad del ratón
        JLabel label1 = new JLabel("Velocidad del ratón");
        label1.setBounds(35, 6, 130, 16);
        contentPane.add(label1); // Agrega la etiqueta al panel
        
        // Crea y configura la etiqueta para el botón seleccionado
        JLabel label2 = new JLabel("Botón seleccionado");
        label2.setBounds(221, 6, 152, 16);
        contentPane.add(label2); // Agrega la etiqueta al panel
        
        // Inicializa el comboBox para seleccionar el botón del ratón
        combo1 = new JComboBox();
        combo1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                repaint(); // Redibuja la ventana al cambiar el botón seleccionado
            }
        });
        // Establece los elementos del comboBox
        combo1.setModel(new DefaultComboBoxModel(new String[] {"Izquierdo", "Derecho"}));
        combo1.setBounds(208, 34, 142, 27);
        contentPane.add(combo1); // Agrega el comboBox al panel
        
        // Inicializa el spinner para ajustar la velocidad del ratón
        spinner1 = new JSpinner();
        spinner1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                repaint(); // Redibuja la ventana al cambiar el valor del spinner
            }
        });
        // Establece el modelo del spinner con un rango de 0 a 100 y un paso de 25
        spinner1.setModel(new SpinnerNumberModel(0, 0, 100, 25));
        spinner1.setBounds(45, 36, 109, 26);
        contentPane.add(spinner1); // Agrega el spinner al panel
    }

    // Método que se encarga de dibujar en la ventana
    public void paint(Graphics g) {
        super.paint(g); // Llama al método padre para limpiar el área de dibujo
        g.setColor(Color.blue);
        g.drawRect(215, 120, 120, 150); // Dibuja un rectángulo azul
        g.drawRect(50, 120, 80, 20); // Dibuja un rectángulo más pequeño

        // Verifica el botón seleccionado y dibuja un rectángulo y un óvalo
        if (combo1.getSelectedItem().toString().equals("Izquierdo")) {
            g.fillRect(215, 120, 60, 60); // Dibuja un rectángulo para el botón izquierdo
            g.setColor(Color.gray);
            g.fillOval(215, 120, 60, 60); // Dibuja un óvalo gris
        } else {
            g.fillRect(275, 120, 60, 60); // Dibuja un rectángulo para el botón derecho
            g.setColor(Color.gray);
            g.fillOval(275, 120, 60, 60); // Dibuja un óvalo gris
        }

        // Cambia el color del rectángulo según el valor del spinner
        if (spinner1.getValue().equals(0)) {
            g.setColor(Color.red);
            g.fillRect(50, 120, 20, 20); // Dibuja un rectángulo rojo
        } else if (spinner1.getValue().equals(25)) {
            g.setColor(Color.orange);
            g.fillRect(50, 120, 40, 20); // Dibuja un rectángulo naranja
        } else if (spinner1.getValue().equals(50)) {
            g.setColor(Color.green);
            g.fillRect(50, 120, 60, 20); // Dibuja un rectángulo verde
        }
    }
}
