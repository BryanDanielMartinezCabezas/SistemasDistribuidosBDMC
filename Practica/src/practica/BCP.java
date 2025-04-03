package practica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BCP {
    public static final ArrayList<Cuenta> cuentas = new ArrayList<>();
    
    static {
        // Agregamos algunas cuentas de ejemplo
        cuentas.add(new Cuenta(Banco.BCP, "110216553", "Bryan", "Oropeza", 6000.0, "678947"));
        cuentas.add(new Cuenta(Banco.Mercantil, "11021654", "Juan Perez", "Segovia", 2500.00, "657654"));
    }
    
    public static void main(String[] args) {
        int port = 1700; // Puerto en el que el servidor escuchará
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor BCP iniciado en el puerto " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Espera una conexión de cliente
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Flujos para recibir y enviar mensajes al cliente
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);

                String mensaje;
                
                while ((mensaje = fromClient.readLine()) != null) {
                    String respuesta = ""; // Respuesta que vamos a enviar
                    
                    if (mensaje.startsWith("Buscar:")) {
                        // Si es un mensaje de búsqueda
                        String[] partes = mensaje.substring(7).split("-");
                        String ci = partes[0];
                        String nombres = partes[1];
                        String apellidos = partes[2];

                        // Buscar cuentas con los datos recibidos
                        StringBuilder cuentasEncontradas = new StringBuilder();
                        for (Cuenta cuenta : cuentas) {
                            if (cuenta.getCi().equals(ci) &&
                                cuenta.getNombres().equals(nombres) &&
                                cuenta.getApellidos().equals(apellidos)) {
                                cuentasEncontradas.append(cuenta.getNrocuenta())
                                                  .append("-")
                                                  .append(cuenta.getSaldo())
                                                  .append(":");
                            }
                        }

                        // Si no se encontraron cuentas, dejamos la respuesta vacía
                        respuesta = cuentasEncontradas.length() > 0 ? cuentasEncontradas.toString() : "";
                        
                    } else if (mensaje.startsWith("Congelar:")) {
                        // Si es un mensaje de congelar monto
                        // Usamos substring(9) para quitar "Congelar:" (9 caracteres)
                        String[] partes = mensaje.substring(9).split("-");
                        String nroCuenta = partes[0];
                        double monto = Double.parseDouble(partes[1]);

                        // Buscar la cuenta y verificar si tiene saldo suficiente
                        boolean cuentaEncontrada = false;
                        for (Cuenta cuenta : cuentas) {
                            if (cuenta.getNrocuenta().equals(nroCuenta)) {
                                cuentaEncontrada = true;
                                if (cuenta.getSaldo() >= monto) {
                                    cuenta.setSaldo(cuenta.getSaldo() - monto); // Retenemos el monto
                                    respuesta = "SI-" + nroCuenta;
                                } else {
                                    respuesta = "NO-No suficiente saldo";
                                }
                                break;
                            }
                        }

                        if (!cuentaEncontrada) {
                            respuesta = "NO-No encontrado";
                        }
                    } else {
                        respuesta = "Error: Comando no reconocido";
                    }

                    // Enviamos la respuesta al cliente
                    toClient.println(respuesta);
                }
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
