package examen_practico_sis258;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String servidorBanco = "localhost"; 
        int puertoBanco = 1700; 

        try (Socket socket = new Socket(servidorBanco, puertoBanco);
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al Banco.");
            System.out.print("Ingrese su CI para consultar deudas: ");
            String ci = scanner.nextLine();
            toServer.println("Deuda:" + ci);

            String respuesta = fromServer.readLine();
            System.out.println("Respuesta del Banco: " + respuesta);

            if (respuesta.startsWith("Deudas:") && respuesta.length() > 7) {
                System.out.print("Desea pagar alguna deuda? (si/no): ");
                if (scanner.nextLine().equalsIgnoreCase("si")) {
                    System.out.println("Ingrese los pagos en formato anio,impuesto,monto separados por ';'");
                    String pagos = scanner.nextLine();
                    toServer.println("Pagar:" + pagos);

                    String respuestaPago = fromServer.readLine();
                    System.out.println("Respuesta del Banco: " + respuestaPago);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
