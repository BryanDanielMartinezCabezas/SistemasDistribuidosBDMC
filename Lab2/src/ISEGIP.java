import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISEGIP extends Remote {
    Respuesta ValidarCI(int CI, String nombre, String apellido1, String apellido2) throws RemoteException;
}
