
import java.rmi.Remote;
import java.rmi.RemoteException;







public interface IUniversidad extends Remote {
    
    
    Diploma EmitirDiploma(int CI, String Nombres , String Apellido1, String Apellido2, String fechaNacimiento, String carrera  ) throws RemoteException;
    
    
    
    
}
