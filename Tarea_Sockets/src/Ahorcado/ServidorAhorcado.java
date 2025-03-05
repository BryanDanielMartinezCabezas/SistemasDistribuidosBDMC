package Ahorcado;

import java.net.*;
import java.io.*;
import java.util.*;

// ServidorAhorcado - Maneja las conexiones y el juego
public class ServidorAhorcado {
    // Conjunto de flujos de salida de jugadores conectados
    private static Set<PrintWriter> jugadores = Collections.synchronizedSet(new HashSet<>());
    // Instancia compartida del juego
    private static JuegoAhorcado juego;
    
    public static void main(String[] args) {
        int port = 12345; // Puerto del servidor
        // Se define la palabra secreta (se puede ampliar para seleccionar aleatoriamente)
        juego = new JuegoAhorcado("PROGRAMACION");
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor Ahorcado iniciado en el puerto " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Espera conexion
                System.out.println("Jugador conectado: " + clientSocket.getInetAddress());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                jugadores.add(out);
                new Thread(new JugadorHandler(clientSocket)).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // broadcast - Difunde un mensaje a todos los jugadores
    public static void broadcast(String mensaje) {
        synchronized(jugadores) {
            for (PrintWriter writer : jugadores) {
                writer.println(mensaje);
            }
        }
    }
    
    // JugadorHandler - Maneja la comunicacion con cada jugador
    static class JugadorHandler implements Runnable {
        private Socket socket;
        
        public JugadorHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ) {
                out.println("Bienvenido al juego del Ahorcado!");
                out.println(juego.getEstado());
                out.println("Ingresa una letra:");
                
                String input;
                while ((input = in.readLine()) != null) {
                    if (input.length() == 0) continue;
                    char letra = input.charAt(0);
                    // Actualiza el juego con la letra ingresada
                    String estado = juego.jugarLetra(letra);
                    // Difunde el nuevo estado a todos los jugadores
                    broadcast("Nuevo estado del juego:\n" + estado);
                    if (juego.juegoTerminado()) {
                        if (juego.esGanado()) {
                            broadcast("Juego ganado! La palabra era: " + juego.getEstado());
                        } else {
                            broadcast("Juego perdido! Se alcanzaron 7 errores. La palabra era: " + juego.getEstado());
                        }
                        break;
                    }
                    out.println("Ingresa una letra:");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try { socket.close(); } catch (IOException ex) { ex.printStackTrace(); }
            }
        }
    }
}
