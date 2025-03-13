package rmi_tarea;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_tarea.IOperaciones;

public class Cliente {
    public static void main(String[] args) {
        IOperaciones operaciones;
        Scanner sc = new Scanner(System.in);
        try {
            operaciones = (IOperaciones) Naming.lookup("rmi://localhost/O");

            int n = 0;
            int o = 0;
            
            while (true) {
                System.out.println("Seleccione una opcion:");
                System.out.println("1. Establecer numero");
                System.out.println("2. Calcular Fibonacci");
                System.out.println("3. Calcular Factorial");
                System.out.println("4. Calcular Sumatoria");
                System.out.println("5. Salir");
                
                n = sc.nextInt();

                if (n == 5) {
                    System.out.println("Saliendo del programa...");
                    break;
                }

                switch (n) {
                    case 1:
                        System.out.println("Introduce un número para establecer:");
                        o = sc.nextInt();
                        operaciones.SetN(o);
                        System.out.println("Número establecido en " + o);
                        break;
                    case 2:
                        System.out.println("Fibonacci: " + operaciones.Fibonacci(o));
                        break;
                    case 3:
                        System.out.println("Factorial: " + operaciones.Factorial(o));
                        break;
                    case 4:
                        System.out.println("Sumatoria: " + operaciones.Sumatoria(o));
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }

        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
