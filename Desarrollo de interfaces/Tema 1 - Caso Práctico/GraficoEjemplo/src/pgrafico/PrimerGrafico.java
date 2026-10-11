package pgrafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class PrimerGrafico {

    public PrimerGrafico() {
        // Constructor
    }

    public static void main(String[] args) {
        // Crear una ventana JFrame
        JFrame f = new JFrame("Mi primera ventana");

        // Establecer tamaño de la ventana
        f.setSize(800, 400);

        // Acción al cerrar la ventana
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        f.setVisible(true);
    }
}

