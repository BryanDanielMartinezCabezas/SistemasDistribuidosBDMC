import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            IBanco banco = (IBanco) Naming.lookup("rmi://localhost/Banco");
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("Ingrese el CI");
                String ci = scanner.nextLine().trim();
                
                if (ci.equalsIgnoreCase("salir")) {
                    System.out.println("Saliendo del sistema...");
                    break;
                }
                
                ArrayList<Deuda> deudas = banco.ObtenerDeuda(ci);
                
                if (deudas.isEmpty()) {
                    System.out.println("No se encontraron deudas para el CI: " + ci);
                } else {
                    System.out.println("Deudas encontradas:");
                    for (int i = 0; i < deudas.size(); i++) {
                        Deuda deuda = deudas.get(i);
                        System.out.println(i + " - CI: " + deuda.getCI() +
                                           ", Año: " + deuda.getAnio() +
                                           ", Impuesto: " + deuda.getImpuesto() +
                                           ", Monto: " + deuda.getMonto());
                    }
                    
                    System.out.print("¿Desea pagar alguna de estas deudas? (si/no): ");
                    String respuesta = scanner.nextLine().trim();
                    
                    if (respuesta.equalsIgnoreCase("si")) {
                        System.out.print("Ingrese el número de la deuda a pagar: ");
                        try {
                            int indice = Integer.parseInt(scanner.nextLine().trim());
                            
                            if (indice < 0 || indice >= deudas.size()) {
                                System.out.println("Índice fuera de rango.");
                            } else {
                                boolean pagoExitoso = banco.Pagar(deudas.get(indice));
                                if (pagoExitoso) {
                                    System.out.println("Deuda pagada exitosamente.");
                                } else {
                                    System.out.println("No se pudo realizar el pago. Es posible que el CI tenga observaciones.");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada no válida. Por favor ingrese un número entero.");
                        }
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Error en el Cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
