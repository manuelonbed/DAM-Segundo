package com.edades;

import java.io.BufferedReader;
import java.io.FileReader;     
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExtraeEdades {

    /**
     * Si es true, además del rango 0..120 exigimos "contexto" lingüístico:
     * - Palabra anterior igual a "edad" o "tiene", o
     * - Palabra siguiente igual a "años".
     * Útil para reducir falsos positivos cuando el texto contiene muchos números (ids, precios, años).
     */
    private static final boolean FILTRAR_POR_CONTEXTO = false;

    public static void main(String[] args) {
        // Construcción de la ruta de entrada:
        // - Si se pasa un argumento (args[0]), se usa ese path.
        // - Si no, por defecto se busca en: <carpeta del proyecto>/data/usuarios.txt
        //   (Projects de Eclipse: mismo nivel que /src)
        String ruta = (args.length > 0)
                ? args[0]
                : Paths.get(System.getProperty("user.dir"), "data", "usuarios.txt").toString();

        System.out.println("Leyendo: " + ruta);

        try {
            List<Integer> edades = leerEdades(ruta);
            System.out.println("Edades encontradas: " + edades);
            // Punto de extensión: calcular estadísticas, guardar en BD, exportar CSV, etc.
        } catch (IOException e) {
            // Mensaje claro si la ruta no existe o no hay permisos de lectura.
            System.err.println("No se pudo leer el fichero: " + e.getMessage());
        }
    }

    /**
     * Lee el fichero de texto indicado y extrae números
     * que "parecen" edades humanas.
     *
     * Criterios por defecto:
     *  1) El token debe ser numérico (StreamTokenizer.TT_NUMBER).
     *  2) Debe estar en el rango [0, 120].
     *  3) (Opcional) Debe aparecer cerca de señales lingüísticas (edad/tiene/años)
     *     si FILTRAR_POR_CONTEXTO = true.
     *
     * @param rutaFichero Ruta absoluta o relativa al fichero.
     * @return lista de edades en el orden en que aparecen.
     * @throws IOException si hay problemas de E/S.
     *
     * NOTA sobre codificación: FileReader usa el "default charset" del SO.
     * Si tu fichero lleva acentos/ñ y ves símbolos raros,
     * usa InputStreamReader con UTF-8 :
     *   new BufferedReader(new InputStreamReader(new FileInputStream(ruta), StandardCharsets.UTF_8))
     */
    public static List<Integer> leerEdades(String rutaFichero) throws IOException {
        List<Integer> edades = new ArrayList<>();

        // try-with-resources: cierra el reader automáticamente.
        try (Reader reader = new BufferedReader(new FileReader(rutaFichero))) {
            StreamTokenizer st = new StreamTokenizer(reader);

            // ===== Configuración fina del analizador de tokens =====
            st.resetSyntax();                 // Partimos de "tabla" vacía (no heredamos reglas).
            // Definimos qué cuenta como "palabra":
            st.wordChars('a', 'z');           // letras minúsculas
            st.wordChars('A', 'Z');           // letras mayúsculas
            st.wordChars(128 + 32, 255);      // caracteres extendidos (acentos, ñ)
            // Definimos "blancos"/separadores (evita que queden pegados a palabras):
            st.whitespaceChars(0, ' ');       // espacios, tabuladores, saltos de línea y control
            // Activamos reconocimiento de números (se leen en st.nval como double):
            st.parseNumbers();
            // Evitamos tratar el guion como parte de números (no queremos "-23"):
            st.ordinaryChar('-');
            // Puntuación a tratar como separador para cortar "34," "29." "(23)" "Edad: 29"
            st.whitespaceChars(',', ',');
            st.whitespaceChars('.', '.');
            st.whitespaceChars(';', ';');
            st.whitespaceChars(':', ':');

            // Memoria mínima de contexto: última palabra vista.
            // Sirve para comprobar patrones "Edad: 23" o "Carlos tiene 41".
            String ultimaPalabra = null;

            // ===== Bucle principal: consume token a token hasta EOF =====
            while (st.nextToken() != StreamTokenizer.TT_EOF) {

                if (st.ttype == StreamTokenizer.TT_WORD) {
                    // Guardamos la palabra por si el PRÓXIMO token es un número
                    // y activamos el filtro por contexto.
                    ultimaPalabra = st.sval;
                    continue; // Siguiente token
                }

                if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    // nval es double; las edades son enteras → truncamos (23.0 → 23)
                    int posibleEdad = (int) st.nval;

                    // 1) Filtro de plausibilidad por rango (rápido y barato).
                    if (posibleEdad >= 0 && posibleEdad <= 120) {

                        if (!FILTRAR_POR_CONTEXTO) {
                            // Aceptamos cualquier número dentro del rango.
                            edades.add(posibleEdad);

                        } else {
                            // 2) Filtro semántico: exige "pistas" alrededor del número.
                            boolean aceptar = false;

                            // (a) Mirar palabra ANTERIOR (si existe).
                            //    Ej: "Edad: 23"  o  "Carlos tiene 41"
                            if (ultimaPalabra != null) {
                                String w = ultimaPalabra.toLowerCase();
                                if (w.equals("edad") || w.equals("tiene")) {
                                    aceptar = true;
                                }
                            }

                            // (b) Mirar palabra SIGUIENTE: ¿es "años"?
                            //    Ej: "Ana (23) años"
                            int ttypeSiguiente = st.nextToken(); // adelantamos una posición
                            if (ttypeSiguiente == StreamTokenizer.TT_WORD
                                    && st.sval != null
                                    && st.sval.equalsIgnoreCase("años")) {
                                aceptar = true;
                            } else {
                                // Si NO era "años", devolvemos el token para que el bucle lo procese más tarde.
                                st.pushBack();
                            }

                            if (aceptar) {
                                edades.add(posibleEdad);
                            }
                        }
                    }

                    // Tras procesar un número, invalidamos la última palabra,
                    // para no aplicar el mismo contexto a tokens futuros.
                    ultimaPalabra = null;
                }
            }
        }

        return edades;
    }
}
