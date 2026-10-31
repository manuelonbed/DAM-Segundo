/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usuario
 */
import javax.swing.*;
import java.awt.*;

public class VentanaBasica extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaBasica() {
        initUI();
    }

    private void initUI() {
        // Título
        setTitle("Mi primera ventana");

        // Acción de cierre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout y componentes
        setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Hola Swing", SwingConstants.CENTER);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        add(etiqueta, BorderLayout.NORTH);
        add(btnCerrar, BorderLayout.SOUTH);

        // Tamaño, centrado y visibilidad
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaBasica::new);
    }
}
