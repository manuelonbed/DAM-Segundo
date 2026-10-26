import java.io.File;
import java.io.IOException;

public class ProgramaPrincipal {

    public static void main(String[] args)
            throws IOException, InterruptedException {

        System.out.println("Voy a iniciar otro proceso Java.");

        int codigoSalida = ejecutarClaseProceso(Suma.class, 5, 3);

        System.out.println("El proceso ha terminado.");
        System.out.println("Código de salida: " + codigoSalida);
    }

    public static int ejecutarClaseProceso(
            Class<?> clase, int n1, int n2)
            throws IOException, InterruptedException {

        // 1. Obtener la ruta donde está instalado Java
        String javaHome = System.getProperty("java.home");

        String javaBin = javaHome
                + File.separator + "bin"
                + File.separator + "java";

        // 2. Obtener la ruta de las clases del proyecto
        String classpath = System.getProperty("java.class.path");

        // 3. Obtener el nombre de la clase que ejecutaremos
        String className = clase.getCanonicalName();

        // 4. Preparar el comando para iniciar el proceso
        ProcessBuilder builder = new ProcessBuilder(
                javaBin,
                "-cp",
                classpath,
                className,
                String.valueOf(n1),
                String.valueOf(n2)
        );

        // Mostrar la salida del nuevo proceso en la consola
        builder.inheritIO();

        // 5. Iniciar el proceso independiente
        Process process = builder.start();

        // 6. Esperar a que el proceso termine
        process.waitFor();

        // 7. Devolver el código de salida
        return process.exitValue();
    }
}