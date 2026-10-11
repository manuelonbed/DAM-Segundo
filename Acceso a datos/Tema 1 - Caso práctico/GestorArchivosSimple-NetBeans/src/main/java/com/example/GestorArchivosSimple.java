package com.example;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Gestor de archivos simple usando java.io.File:
 * - Crear directorios (mkdirs)
 * - Crear ficheros (createNewFile)
 * - Mover ficheros (renameTo)
 * - Obtener fecha de última modificación (lastModified)
 *
 * Ejecuta con: Run Project en NetBeans (proyecto Maven).
 */
public class GestorArchivosSimple {

    /** Crea un directorio (y padres si no existen). */
    public static boolean crearDirectorio(String rutaDirectorio) {
        File dir = new File(rutaDirectorio);
        if (dir.exists()) return true;
        return dir.mkdirs();
    }

    /** Crea un fichero vacío. Si la carpeta padre no existe, se crea. */
    public static boolean crearFichero(String rutaFichero) {
        File f = new File(rutaFichero);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        try {
            return f.createNewFile(); // true si se creó; false si ya existía
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Devuelve la fecha de última modificación formateada del fichero. */
    public static String fechaUltimaModificacion(String rutaFichero) {
        File f = new File(rutaFichero);
        if (!f.exists()) return "El fichero no existe.";
        long millis = f.lastModified();
        Instant instant = Instant.ofEpochMilli(millis);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                                 .withZone(ZoneId.systemDefault());
        return fmt.format(instant);
    }

    /**
     * Mueve un fichero a un directorio destino usando renameTo.
     * Devuelve true si se movió, false si falló.
     */
    public static boolean moverFichero(String rutaOrigen, String rutaDirectorioDestino) {
        File origen = new File(rutaOrigen);
        File destinoDir = new File(rutaDirectorioDestino);
        if (!destinoDir.exists()) destinoDir.mkdirs();
        File destino = new File(destinoDir, origen.getName());
        return origen.renameTo(destino);
    }

    /** Ejemplo de uso desde main. */
    public static void main(String[] args) {
        // Base de pruebas dentro del HOME del usuario
        String base = System.getProperty("user.home") + File.separator + "demo_gestor_io";
        String dirA = base + File.separator + "carpetaA";
        String dirB = base + File.separator + "carpetaB";
        String rutaFichero = dirA + File.separator + "ejemplo.txt";

        System.out.println("Directorio base: " + base);

        // 1) Crear directorios
        System.out.println("Crear carpeta A: " + crearDirectorio(dirA));
        System.out.println("Crear carpeta B: " + crearDirectorio(dirB));

        // 2) Crear fichero
        System.out.println("Crear fichero: " + crearFichero(rutaFichero));
        System.out.println("Fecha última modificación: " + fechaUltimaModificacion(rutaFichero));

        // 3) Mover fichero de A a B
        boolean movido = moverFichero(rutaFichero, dirB);
        System.out.println("Mover fichero a carpeta B: " + movido);

        // 4) Comprobar nueva ubicación y su fecha de modificación
        String nuevaRuta = dirB + File.separator + "ejemplo.txt";
        System.out.println("Existe en nueva ruta: " + new File(nuevaRuta).exists());
        System.out.println("Fecha última modificación (nueva ruta): " + fechaUltimaModificacion(nuevaRuta));

        System.out.println("FIN.");
    }
}
