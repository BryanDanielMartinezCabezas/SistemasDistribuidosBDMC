package tarea_sockets.Operaciones;

import java.net.*;
import java.io.*;
import java.util.Scanner;

// Cliente - Envia opciones y recibe resultados del servidor
public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1700;
        try (
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            leerMenu(in);
            boolean salir = false;
            while (!salir) {
                System.out.print("Ingrese una opcion: ");
                String opcion = scanner.nextLine();
                out.println(opcion);
                
                if (opcion.equals("5")) {
                    System.out.println("Adios!");
                    salir = true;
                    break;
                }
                
                if(opcion.equals("1")) {
                    // Solicitar numero para insertar
                    String solicitud = in.readLine();
                    System.out.println(solicitud);
                    System.out.print("Numero: ");
                    String num = scanner.nextLine();
                    out.println(num);
                }
                
                String resultado = in.readLine();
                System.out.println(resultado);
                
                String separador = in.readLine();
                if (separador != null && !separador.equals("FIN_MENU")) {
                    System.out.println(separador);
                }
                leerMenu(in);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // Lectura del menu
    private static void leerMenu(BufferedReader in) throws IOException {
        String linea;
        while ((linea = in.readLine()) != null) {
            if (linea.equals("FIN_MENU"))
                break;
            System.out.println(linea);
        }
    }
}
