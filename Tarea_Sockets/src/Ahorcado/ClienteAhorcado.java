package Ahorcado;

import java.net.*;
import java.io.*;
import java.util.Scanner;

// ClienteAhorcado - Cliente para el juego del Ahorcado
public class ClienteAhorcado {
    public static void main(String[] args) {
        String serverIP = "localhost"; // Cambiar segun sea necesario
        int port = 12345;
        
        try (
            Socket socket = new Socket(serverIP, port);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {
            // Hilo para leer mensajes del servidor
            Thread lectura = new Thread(new Runnable() {
                public void run() {
                    String mensaje;
                    try {
                        while ((mensaje = in.readLine()) != null) {
                            System.out.println(mensaje);
                        }
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            lectura.start();
            
            // Enviar jugadas al servidor
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                out.println(input);
            }
        } catch (IOException ex) {
            System.out.println("Error en el cliente: " + ex.getMessage());
        }
    }
}
