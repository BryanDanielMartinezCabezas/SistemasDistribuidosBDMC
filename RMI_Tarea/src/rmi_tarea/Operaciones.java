package rmi_tarea;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class Operaciones extends UnicastRemoteObject implements IOperaciones {
    
    int n;
    
    public int getN(){
    return n;
    }
    
    public Operaciones() throws RemoteException{
        super();
    
    }
    

    @Override
    public int Factorial(int n) throws RemoteException {
        if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    @Override
    public int Fibonacci(int n) throws RemoteException {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1, res = 0;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    @Override
    public int Sumatoria(int n) throws RemoteException {
        return (n * (n + 1)) / 2;
    }

    @Override
    public void SetN(int n) throws RemoteException {
        this.n = n;
        
        
    }
    
    
    
    
}
