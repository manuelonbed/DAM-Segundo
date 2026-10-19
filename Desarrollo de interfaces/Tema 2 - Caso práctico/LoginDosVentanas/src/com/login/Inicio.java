package com.login;                                         // Debe coincidir con el paquete del proyecto

import javax.swing.*;                                      // Swing components
import java.awt.*;                                         // BorderLayout y Font

/**
 * Ventana simple que muestra un mensaje de bienvenida.
 * Se abre cuando el login es correcto.
 */
public class Inicio extends JFrame {                        // Nueva ventana independiente

    public Inicio(String usuario) {                        // Recibe el nombre del usuario logueado
        super("Bienvenida");                               // Título de la ventana

        JLabel lbl = new JLabel(                           // Etiqueta para el mensaje
                "¡Bienvenido/a, " + usuario + "!",         // Texto a mostrar
                SwingConstants.CENTER                      // Alineado al centro
        );
        lbl.setFont(lbl.getFont().deriveFont(Font.PLAIN, 18f)); // Aumenta el tamaño de letra

        add(lbl, BorderLayout.CENTER);                     // Añade la etiqueta al centro del frame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Cerrar la app al cerrar esta ventana
        setSize(420, 160);                                 // Tamaño de la ventana
        setLocationRelativeTo(null);                       // Centrar en pantalla
    }
}
