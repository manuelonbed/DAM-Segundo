package pgrafico;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Ejemplo mínimo de una ventana Swing (JFrame) creada solo con código.
 * Demuestra los pasos imprescindibles:
 *  1) Establecer tamaño/posición (setBounds)
 *  2) Hacerla visible (setVisible)
 *  3) Definir la acción de cierre (setDefaultCloseOperation)
 */
public class Jframe extends JFrame {

    // Buenas prácticas en clases serializables (JFrame lo es): id de versión.
    private static final long serialVersionUID = 1L;

    // Panel raíz que contendrá todos los componentes de la ventana.
    private JPanel contentPane;

    /**
     * Punto de entrada de la aplicación.
     * Usamos EventQueue.invokeLater para crear y mostrar la GUI
     * en el hilo de despacho de eventos (EDT), como recomienda Swing.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Instanciamos nuestra ventana y la hacemos visible.
                    Jframe frame = new Jframe();
                    frame.setVisible(true); // Paso imprescindible: mostrar la ventana
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor: configura la ventana y su panel de contenido.
     */
    public Jframe() {
        // Acción de cierre: terminar la app al cerrar la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Posición y tamaño de la ventana en pantalla:
        // setBounds(x, y, width, height)
        setBounds(100, 100, 450, 300);

        // Panel principal (contentPane) donde añadiremos componentes.
        contentPane = new JPanel();

        // Borde interno (márgenes) de 5px en cada lado para separar del borde de la ventana.
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Establecemos nuestro panel como panel de contenido del JFrame.
        setContentPane(contentPane);
        
        JButton btnNewButton = new JButton("Hola");
        btnNewButton.setForeground(new Color(55, 255, 62));
        contentPane.add(btnNewButton);

    }
}

