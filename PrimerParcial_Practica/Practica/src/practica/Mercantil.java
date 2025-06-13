package practica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class Mercantil {
    public static final ArrayList<Cuenta> cuentas = new ArrayList<>();
    
    static {
        // Agregamos algunas cuentas de ejemplo
        cuentas.add(new Cuenta(Banco.Mercantil, "11021654", "Juan Perez", "Segovia", 2500.00, "657654"));
        cuentas.add(new Cuenta(Banco.Mercantil, "11021655", "Carlos", "Lopez", 2000.0, "67890"));
    }
    
    public static void main(String[] args) {
        int port = 9999; // Puerto en el que el servidor escuchará
        try {
            // Creamos el socket UDP
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Servidor Mercantil en ejecución en el puerto " + port);

            while (true) {
                // Buffer para recibir los mensajes
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // Recibimos el mensaje del cliente
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                
                System.out.println("Mensaje recibido: " + mensaje);

                String respuesta = ""; // Respuesta que vamos a enviar

                // Procesamos el mensaje recibido
                if (mensaje.startsWith("Buscar:")) {
                    // Si es un mensaje de búsqueda
                    String[] partes = mensaje.substring(7).split("-");
                    if (partes.length >= 3) {
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
                        respuesta = cuentasEncontradas.toString();
                    } else {
                        respuesta = "Error: Formato de búsqueda incorrecto";
                    }
                } else if (mensaje.startsWith("Congelar:")) {
                    // Si es un mensaje de congelar monto
                    // Corregido: usar substring(9) para quitar "Congelar:" (9 caracteres)
                    String[] partes = mensaje.substring(9).split("-");
                    if (partes.length >= 2) {
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
                        respuesta = "Error: Formato de congelación incorrecto";
                    }
                } else {
                    respuesta = "Error: Comando no reconocido";
                }

                // Enviamos la respuesta de vuelta al cliente
                DatagramPacket packetRespuesta = new DatagramPacket(
                        respuesta.getBytes(), respuesta.length(), packet.getAddress(), packet.getPort());
                socket.send(packetRespuesta);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
