public class Suma {

    public static void main(String[] args) {

        // Recibir los números enviados por el otro proceso
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);

        // Realizar la operación
        int resultado = n1 + n2;

        // Mostrar el resultado
        System.out.println(
                "La suma de " + n1 + " + " + n2
                        + " = " + resultado
        );
    }
}