
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 6789;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Cliente UDP iniciado...");

            while (true) {
                System.out.println("\nSeleccione una opcion:");
                System.out.println("1. Establecer numero (setN)");
                System.out.println("2. Calcular Fibonacci");
                System.out.println("3. Calcular Factorial");
                System.out.println("4. Calcular Sumatoria");
                System.out.println("5. Salir");
                System.out.print("Opcion: ");
                int option = sc.nextInt();
                sc.nextLine();
                String message = "";

                if (option == 1) {
                    System.out.print("Ingrese un numero: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    message = "setN-" + num;
                } else if (option == 2) {
                    message = "fibonacci";
                } else if (option == 3) {
                    message = "factorial";
                } else if (option == 4) {
                    message = "sumatoria";
                } else if (option == 5) {
                    System.out.println("Saliendo...");
                    break;
                } else {
                    System.out.println("Opción invalida. Intente nuevamente.");
                    continue;
                }

                byte[] msgBytes = message.getBytes();
                InetAddress serverAddr = InetAddress.getByName(SERVER_HOST);
                DatagramPacket req = new DatagramPacket(msgBytes, msgBytes.length, serverAddr, SERVER_PORT);
                socket.send(req);

                byte[] respBuffer = new byte[1000];
                DatagramPacket resp = new DatagramPacket(respBuffer, respBuffer.length);
                socket.receive(resp);
                String serverResponse = new String(resp.getData(), 0, resp.getLength());
                System.out.println("Respuesta del servidor: " + serverResponse);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
