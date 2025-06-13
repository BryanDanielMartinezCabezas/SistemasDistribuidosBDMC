package practica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorASFI {
    public static void main(String[] args) {
        try {
            // Iniciar el RMI Registry en el puerto 1099 programáticamente
            LocateRegistry.createRegistry(1099);
            System.out.println(" Registro RMI iniciado en el puerto 1099...");

            // Crear la instancia del objeto ASFI
            ASFI asfi = new ASFI();

            // Registrar el objeto RMI en el RMI Registry
            Naming.rebind("rmi://localhost/ASFI", asfi);

            System.out.println("Servidor Universidad esta listo...");
        } catch (Exception e) {
            System.err.println("Error en el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
