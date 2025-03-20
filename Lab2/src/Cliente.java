import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {

            IUniversidad universidad = (IUniversidad) Naming.lookup("rmi://localhost/Universidad");
            
   
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa el CI: ");
            int CI = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Ingresa los Nombres: ");
            String nombres = scanner.nextLine();
            System.out.print("Ingresa el Primer Apellido: ");
            String apellido1 = scanner.nextLine();
            System.out.print("Ingresa el Segundo Apellido: ");
            String apellido2 = scanner.nextLine();
            System.out.print("Ingresa la Fecha de Nacimiento (dd-MM-yyyy): ");
            String fechaNacimiento = scanner.nextLine();
            System.out.print("Ingresa la Carrera: ");
            String carrera = scanner.nextLine();
            

            Diploma diploma = universidad.EmitirDiploma(CI, nombres, apellido1, apellido2, fechaNacimiento, carrera);


            if (diploma != null) {
                System.out.println("Diploma emitido con exito:");
                System.out.println("Nombre Completo: " + diploma.getNombrecompleto());
                System.out.println("Carrera: " + diploma.getCarrera());
                System.out.println("Fecha de Nacimiento: " + diploma.getFecha());
                System.out.println("Mensaje: " + diploma.getMensaje());
            } else {
                System.out.println("No se pudo emitir el diploma. Los datos son incorrectos.");
            }

        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            System.out.println("Error en la conexión con el servidor RMI: " + e.getMessage());
        }
        
    }
}
