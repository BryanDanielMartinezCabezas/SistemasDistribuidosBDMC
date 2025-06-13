
package practica;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IASFI  extends Remote {
    
    
    ArrayList<Cuenta> ConsultarCuentas (String ci, String nombres, String apellidos) throws RemoteException;
    boolean RetenerMonto(Cuenta cuenta,double monto,String glosa) throws RemoteException;

}
