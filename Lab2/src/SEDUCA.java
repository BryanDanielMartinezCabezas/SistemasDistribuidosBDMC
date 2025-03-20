import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SEDUCA {

    public static void main(String[] args) {
        int port = 1700;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor SEDUCA iniciado en el puerto " + port);
            while (true) {
                Socket socket = serverSocket.accept(); 
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String mensaje;
                
                while((mensaje = fromClient.readLine()) != null) {
                    // Verificar si el RUDE coincide con uno válido
                    if ("WaSeAr11021996".equals(mensaje)) {
                        System.out.println("Verificado con Exito");
                        socket.getOutputStream().write("Verificado con Exito\n".getBytes());
                    } else {
                        System.out.println("No se encontro el titulo de Bachiller");
                        socket.getOutputStream().write("No se encontro el titulo de Bachiller\n".getBytes());
                    }
                }

                fromClient.close();
                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
