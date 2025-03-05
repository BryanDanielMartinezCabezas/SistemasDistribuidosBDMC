package tarea_sockets.Operaciones;

import tarea_sockets.Operaciones.Operaciones;
import java.net.*;
import java.io.*;

// Servidor - Escucha conexiones y procesa operaciones
public class Servidor {
    public static void main(String[] args) {
        int port = 1700;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);
            while (true) {
                Socket socket = serverSocket.accept(); // Espera conexiones
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                new Thread(new ClienteHandler(socket)).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class ClienteHandler implements Runnable {
    private Socket socket;
    private Operaciones ops;
    
    public ClienteHandler(Socket socket) {
        this.socket = socket;
        this.ops = new Operaciones();
    }
    
    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                enviarMenu(out);
                String opcionStr = in.readLine();
                if (opcionStr == null) break;
                
                int opcion;
                try {
                    opcion = Integer.parseInt(opcionStr);
                } catch (NumberFormatException e) {
                    out.println("Resultado: Opcion no valida, intente de nuevo.");
                    continue;
                }
                
                if (opcion == 5) {
                    out.println("Adios!");
                    break;
                }
                
                switch (opcion) {
                    case 1:
                        out.println("Ingrese un numero:");
                        String numStr = in.readLine();
                        int num;
                        try {
                            num = Integer.parseInt(numStr);
                        } catch (NumberFormatException e) {
                            out.println("Resultado: Numero no valido, intente de nuevo.");
                            continue;
                        }
                        ops.insertar(num);
                        out.println("Resultado: Numero insertado: " + num);
                        break;
                    case 2:
                        out.println("Resultado: Fibonacci de " + ops.numero + " es: " + ops.fibonacci());
                        break;
                    case 3:
                        out.println("Resultado: Factorial de " + ops.numero + " es: " + ops.factorial());
                        break;
                    case 4:
                        out.println("Resultado: Sumatoria hasta " + ops.numero + " es: " + ops.sumatoria());
                        break;
                    default:
                        out.println("Resultado: Opcion no valida, intente de nuevo.");
                        break;
                }
                out.println("------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (IOException ex) { ex.printStackTrace(); }
        }
    }
    
    // Envio del menu
    private void enviarMenu(PrintWriter out) {
        out.println("Seleccione una opcion:");
        out.println("1. Insertar numero");
        out.println("2. Calcular Fibonacci");
        out.println("3. Calcular Factorial");
        out.println("4. Calcular Sumatoria");
        out.println("5. Salir");
        out.println("FIN_MENU");
    }
}
