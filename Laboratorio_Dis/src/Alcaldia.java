import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Alcaldia extends UnicastRemoteObject implements IAlcaldia {

    public Alcaldia() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Alcaldia alcaldia = new Alcaldia();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/Alcaldia", alcaldia);
        System.out.println("Servidor Alcaldia en funcionamiento...");
    }

    @Override
    public boolean BuscarObservaciones(String CI) throws RemoteException {
        if (CI.equals("1234567")) {
            return true;  
        } else {
            return false; 
        }
    }
}
