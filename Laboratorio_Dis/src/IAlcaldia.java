import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAlcaldia extends Remote {
    boolean BuscarObservaciones(String CI) throws RemoteException;
}
