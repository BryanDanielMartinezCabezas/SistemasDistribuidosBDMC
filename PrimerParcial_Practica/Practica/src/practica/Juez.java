
package practica;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Juez {
    public static void main(String[] args) {
        try {
            // Conectarse al servidor RMI ASFI
            IASFI asfi = (IASFI) Naming.lookup("rmi://localhost/ASFI");

            // Pedir datos del usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa el CI: ");
            String ci = scanner.nextLine();
            System.out.print("Ingresa los Nombres: ");
            String nombres = scanner.nextLine();
            System.out.print("Ingresa apellidos: ");
            String apellidos = scanner.nextLine();

            // Consultar cuentas
            ArrayList<Cuenta> cuentas = asfi.ConsultarCuentas(ci, nombres, apellidos);
            
            if (cuentas.isEmpty()) {
                System.out.println("No se encontraron cuentas para este usuario.");
                return;
            }

            // Mostrar las cuentas
            System.out.println("\nCuentas encontradas:");
            for (int i = 0; i < cuentas.size(); i++) {
                Cuenta cuenta = cuentas.get(i);
                System.out.println((i + 1) + ". Banco: " + cuenta.getBanco() +
                        ", Nro Cuenta: " + cuenta.getNrocuenta() +
                        ", Saldo: " + cuenta.getSaldo());
            }

            // Permitir al usuario elegir una cuenta
            System.out.print("\nElige una cuenta (número): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir salto de línea

            if (opcion < 1 || opcion > cuentas.size()) {
                System.out.println("Selección inválida.");
                return;
            }

            Cuenta cuentaSeleccionada = cuentas.get(opcion - 1);

            
            System.out.print("Ingrese el monto a retener: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();  // Consumir salto de línea

            
            System.out.print("Ingrese una glosa para la transacción: ");
            String glosa = scanner.nextLine();

            
            boolean exito = asfi.RetenerMonto(cuentaSeleccionada, monto, glosa);

            
            if (exito) {
                System.out.println("✅ Transacción realizada con éxito.");
            } else {
                System.out.println("❌ No se pudo realizar la retención.");
            }

        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            System.out.println("Error en la conexión con el servidor RMI: " + e.getMessage());
        }
    }
}
