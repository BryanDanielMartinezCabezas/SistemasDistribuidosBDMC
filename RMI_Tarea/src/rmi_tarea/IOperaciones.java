/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi_tarea;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Briller
 */
public interface IOperaciones extends Remote {
    
    
    
    

   
    public void SetN(int n) throws RemoteException;
    public int Factorial(int n) throws RemoteException;
    public int Fibonacci(int n) throws RemoteException;
    public int Sumatoria(int n) throws RemoteException;
    
    
    
}
