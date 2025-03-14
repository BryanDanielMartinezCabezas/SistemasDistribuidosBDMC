import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;






public interface IBanco extends Remote {
    ArrayList<Deuda> ObtenerDeuda(String CI) throws RemoteException;
    boolean Pagar(Deuda deuda) throws RemoteException;
}
