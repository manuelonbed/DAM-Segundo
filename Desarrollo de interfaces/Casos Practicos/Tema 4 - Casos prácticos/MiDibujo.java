package paint;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiDibujo extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Establece el color de dibujo a verde.
        g.setColor(Color.green);
        g.fillOval(150, 70, 50, 70);

        // Cambia el color de dibujo a amarillo.
        g.setColor(Color.yellow);
        g.fillRect(350, 70, 50, 70);

        // Cambia el color de dibujo a naranja.
        g.setColor(Color.orange);

        // Define las coordenadas x del polígono (triángulo en este caso).
        int[] vx1 = {600, 650, 550};

        // Define las coordenadas y del polígono.
        int[] vy1 = {70, 120, 120};

        // Dibuja un triángulo naranja usando las coordenadas especificadas.
        g.fillPolygon(vx1, vy1, 3);
    }

    public static void main(String[] args) {
        // Crea un marco (JFrame) para mostrar el dibujo.
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Añade una instancia de MiDibujo al marco.
        frame.add(new MiDibujo());
        frame.setVisible(true);
    }
}
