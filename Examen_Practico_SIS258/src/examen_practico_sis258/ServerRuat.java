package examen_practico_sis258;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRuat {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registro RMI iniciado en el puerto 1099...");

            Ruat ruat = new Ruat();
            Naming.rebind("rmi://localhost/RUAT", ruat);

            System.out.println("Servidor RUAT listo...");
        } catch (Exception e) {
            System.err.println("Error en el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
