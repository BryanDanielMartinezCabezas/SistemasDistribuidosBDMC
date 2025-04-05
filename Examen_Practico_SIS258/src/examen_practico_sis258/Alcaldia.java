package examen_practico_sis258;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Alcaldia {
    public static void main(String[] args) {
        final int PUERTO = 5000;

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Alcaldia UDP en ejecucion...");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Consulta recibida: " + mensaje);

                String respuesta = procesarConsulta(mensaje);
                byte[] respuestaBytes = respuesta.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(
                        respuestaBytes, respuestaBytes.length, 
                        packet.getAddress(), packet.getPort()
                );
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String procesarConsulta(String mensaje) {
        if (mensaje.startsWith("consulta:")) {
            String ci = mensaje.split(":")[1].trim();
            if (ci.equals("1234567")) {
                return "respuesta:false"; 
            } else {
                return "respuesta:true"; 
            }
        }
        return "respuesta:false";
    }
}
