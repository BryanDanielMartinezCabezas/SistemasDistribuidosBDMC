import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SERECI {

    public static void main(String[] args) {
        int port = 9999;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Servidor SERECI en ejecucion en el puerto " + port);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                // Recibir solicitud del cliente
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());
                
                System.out.println("Mensaje recibido: " + mensaje);
                
                // Extraer la fecha y los nombres
                String[] parts = mensaje.split(":");
                String fechaNacimiento = parts[0].substring(4); // "Ver-" es el prefijo, por lo que empezamos desde la fecha
                String[] nombres = parts[1].split(",");
                String nombre = nombres[0];
                String apellido1 = nombres[1];
                String apellido2 = nombres[2];
                
                // Responder
                String respuesta;
                if ("11-02-1996".equals(fechaNacimiento)) { // Si la fecha es válida, por ejemplo
                    respuesta = "Fecha: 17 de marzo de 2025\nSemestre 1/2025\nNombre y Apellidos: " + nombre + " " + apellido1 + " " + apellido2 + "\nNro en Lista: 1";
                } else {
                    respuesta = "error fecha nacimiento no correcta";
                }
                
                // Enviar la respuesta
                DatagramPacket responsePacket = new DatagramPacket(respuesta.getBytes(), respuesta.length(), packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
