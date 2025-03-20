
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;




public class SEGIP extends UnicastRemoteObject implements ISEGIP{
    
    
   public SEGIP() throws RemoteException {
        super();
    }
    
    
   
    
        public static void main(String[] args) throws RemoteException, MalformedURLException {
        SEGIP segip = new SEGIP();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://localhost/SEGIP", segip);
        System.out.println("Servidor SEGIP en funcionamiento...");
    }
    
    
    
    

    @Override
    public Respuesta ValidarCI(int CI, String nombre, String Apellido1, String Apellido2) throws RemoteException {
        int CIValido = 1140506;
        String nombreValido = "Walter Jhamil";
        String apellido1Valido = "Segovia";
        String apellido2Valido = "Arellano";
        
        
        if (CI == CIValido && nombre.equals(nombreValido) && Apellido1.equals(apellido1Valido) && Apellido2.equals(apellido2Valido)) {
            
            return new Respuesta(true, "Los Datos son correctos");
        } else {
            
            return new Respuesta(false, "Los Datos del CI no son correctos");
        }
    }
    
}
