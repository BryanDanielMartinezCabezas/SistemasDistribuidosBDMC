import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRuat extends Remote {
    Deuda[] buscar(String CI) throws RemoteException;
    boolean Pagar(Deuda deuda) throws RemoteException;
}
