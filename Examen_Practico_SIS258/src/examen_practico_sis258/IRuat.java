package examen_practico_sis258;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRuat extends Remote {
    Deuda[] buscar(String ci) throws RemoteException; 

    boolean pagar(Deuda deuda) throws RemoteException; 
}
