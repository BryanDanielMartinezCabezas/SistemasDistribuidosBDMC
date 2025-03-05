package tarea_sockets.Operaciones;

// Clase Operaciones - Manejo de operaciones matematicas
public class Operaciones {
    int numero;
    
    public void insertar(int n) {
        this.numero = n;
    }
    
    private int FibonacciRec(int n) {
        if(n <= 0)
            return 1;
        if(n == 1)
            return 1;
        return FibonacciRec(n - 1) + FibonacciRec(n - 2);
    }
    
    public int fibonacci() {
        return FibonacciRec(this.numero);
    }
    
    private int factorialRec(int n) {
        if(n == 0 || n == 1)
            return 1;
        return n * factorialRec(n - 1);
    }
    
    public int factorial() {
        return factorialRec(this.numero);
    }
    
    private int SumatoriaRec(int n) {
        int suma = 0;
        for(int i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }
    
    public int sumatoria() {
        return SumatoriaRec(this.numero);
    }
}
