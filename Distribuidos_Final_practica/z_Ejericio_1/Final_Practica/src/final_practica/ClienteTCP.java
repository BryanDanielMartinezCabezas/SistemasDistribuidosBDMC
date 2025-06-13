
package final_practica;





import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (
            Socket socket = new Socket(host, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado al servidor. Ingresa numeros (0 para salir):");

            while (true) {
                System.out.print("Numero: ");
                int numero = scanner.nextInt();
                salida.println(numero);

                String respuesta = entrada.readLine();
                System.out.println("Servidor: " + respuesta);

                if (numero == 0) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
