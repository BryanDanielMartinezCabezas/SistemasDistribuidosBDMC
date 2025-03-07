
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        int port = 6789;
        byte[] buffer = new byte[1000];
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(port);
            System.out.println("Servidor UDP iniciado en el puerto " + port);
            Operaciones ops = new Operaciones();

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String data = new String(request.getData(), 0, request.getLength());
                String[] parts = data.split("-");
                String cmd = parts[0].trim().toLowerCase();
                String result = "";

                switch (cmd) {
                    case "setn":
                        int num = Integer.parseInt(parts[1].trim());
                        ops.setN(num);
                        result = "Número establecido en " + num;
                        break;
                    case "fibonacci":
                        result = "Fibonacci(" + ops.getN() + ") = " + ops.fibonacci();
                        break;
                    case "factorial":
                        result = "Factorial(" + ops.getN() + ") = " + ops.factorial();
                        break;
                    case "sumatoria":
                        result = "Sumatoria(" + ops.getN() + ") = " + ops.sumatoria();
                        break;
                    default:
                        result = "Opción inválida";
                }

                byte[] replyData = result.getBytes();
                DatagramPacket reply = new DatagramPacket(replyData, replyData.length,
                        request.getAddress(), request.getPort());
                socket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
