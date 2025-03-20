import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorU extends UnicastRemoteObject implements IUniversidad {

    private ISEGIP segip;

    public ServidorU(ISEGIP segip) throws RemoteException {
        this.segip = segip;
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, IOException {
        // Obtener la referencia del SEGIP a través de RMI
        ISEGIP segip = (ISEGIP) Naming.lookup("rmi://localhost/SEGIP");
        // Crear el servidor de la Universidad con la referencia de SEGIP
        IUniversidad universidad = new ServidorU(segip);
        // Registrar el servidor para que los clientes puedan acceder
        Naming.rebind("Universidad", universidad);
        System.out.println("Servidor de la Universidad en funcionamiento...");
    }

    // Calcular el RUDE a partir de los datos
    private String calcularRude(String nombre, String apellido1, String apellido2, String fechaNacimiento) {
        return nombre.substring(0, 2)+
               apellido1.substring(0, 2)+
               apellido2.substring(0, 2) +
               fechaNacimiento.replaceAll("-", "").substring(0, 8); // Formato ddMMyyyy
    }

    // Verificar el RUDE con SEDUCA usando TCP
    private boolean verificarConSeduca(String rude) {
        try (Socket socket = new Socket("localhost", 1700)) {
            PrintStream toServer = new PrintStream(socket.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviar RUDE a SEDUCA para verificación
            toServer.println(rude);
            String respuesta = fromServer.readLine();
            
            // Verificar si la respuesta de SEDUCA es positiva
            return "Verificado con Exito".equals(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar el RUDE con SERECI usando UDP
    private String verificarConSereci(String rude, String fechaNacimiento, String nombre, String apellido1, String apellido2) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");

            // Enviar la solicitud al servidor SERECI
            String mensaje = "Ver-" + fechaNacimiento + ":" + nombre + "," + apellido1 + "," + apellido2;
            DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.length(), address, 9999); // Puerto 9999 para SERECI
            socket.send(packet);

            // Recibir la respuesta
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);
            String respuesta = new String(responsePacket.getData(), 0, responsePacket.getLength());

            socket.close();
            return respuesta; // La respuesta puede ser la verificación correcta o un error
        } catch (Exception e) {
            e.printStackTrace();
            return "error fecha nacimiento no correcta";
        }
    }

    @Override
    public Diploma EmitirDiploma(int CI, String Nombres, String Apellido1, String Apellido2, String fechaNacimiento, String carrera) throws RemoteException {
        // Verificar los datos del CI con SEGIP
        System.out.println("SOLICITANDO SEGIP");
        Respuesta respuestaSegip = segip.ValidarCI(CI, Nombres, Apellido1, Apellido2);

        if (!respuestaSegip.isEstado()) {
            System.out.println("Los Datos del CI no son correctos");
            return null;
        } else {
            System.out.println("Los Datos del CI son correctos");
        }

        // Calcular el RUDE
        String rude = calcularRude(Nombres, Apellido1, Apellido2, fechaNacimiento);
        System.out.println("RUDE calculado: " + rude);

        // Verificar el RUDE con SEDUCA
        if (!verificarConSeduca(rude)) {
            System.out.println("El RUDE no está registrado en SEDUCA.");
            return null;
        }

        // Verificar el RUDE con SERECI
        String respuestaSereci = verificarConSereci(rude, fechaNacimiento, Nombres, Apellido1, Apellido2);
        if (respuestaSereci.contains("error fecha nacimiento no correcta")) {
            System.out.println("La fecha de nacimiento no es correcta en SERECI.");
            return null;
        }

        // Si todo está correcto, emitir el diploma
        String nombreCompleto = Nombres + " " + Apellido1 + " " + Apellido2;
        String mensaje = "Felicidades " + nombreCompleto + ", has completado la carrera de " + carrera;
        return new Diploma(nombreCompleto, carrera, fechaNacimiento, mensaje);
    }
}
