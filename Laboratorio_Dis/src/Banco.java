import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Banco extends UnicastRemoteObject implements IBanco {
    private IRuat ruat;
    
    
        public static void main(String[] args) {
        try {
            Banco banco = new Banco();
            Naming.rebind("Banco", banco);
            System.out.println("Servidor Banco en funcionamiento...");
        } catch (Exception e) {
            System.out.println("Error en el servidor Banco: " + e);
        }
    }

    public Banco() throws RemoteException {
        super();
        try {
            ruat = (IRuat) Naming.lookup("rmi://localhost/Ruat");
        } catch (Exception e) {
            System.out.println("Error al conectar con el RUAT: " + e);
        }
    }

    @Override
public ArrayList<Deuda> ObtenerDeuda(String CI) throws RemoteException {
    ArrayList<Deuda> deudas = new ArrayList<>();
    try {
        if (ruat != null) {
            Deuda[] deudasRuat = ruat.buscar(CI);
            for (Deuda deuda : deudasRuat) {
                deudas.add(deuda);
            }
        } else {
            System.out.println("El servicio Ruat no está disponible.");
        }
    } catch (RemoteException e) {
        System.out.println("Error de RMI al obtener deudas: " + e);
    } catch (Exception e) {
        System.out.println("Error inesperado al obtener deudas: " + e);
    }
    return deudas;
}

    @Override
    public boolean Pagar(Deuda deuda) throws RemoteException {
        try {
            return ruat.Pagar(deuda);
        } catch (Exception e) {
            System.out.println("Error al procesar el pago: " + e);
            return false;
        }
    }


}

