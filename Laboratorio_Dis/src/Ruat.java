import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Ruat extends UnicastRemoteObject implements IRuat {

    private IAlcaldia alcaldia;

    public Ruat(IAlcaldia alcaldia) throws RemoteException {
        super();
        this.alcaldia = alcaldia;
    }
    
     public static void main(String[] args) {
        try {
            IAlcaldia alcaldia = (IAlcaldia) Naming.lookup("rmi://localhost/Alcaldia");
            Ruat ruat = new Ruat(alcaldia);
            Naming.rebind("Ruat", ruat);
            System.out.println("Servidor Ruat en funcionamiento...");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Ingrese el CI para consultar deudas o pagar");
                String CI = sc.nextLine();
                if (CI.equals("salir")) break;
                Deuda[] deudas = ruat.buscar(CI);
                if (deudas.length > 0) {
                    System.out.println("Deudas encontradas:");
                    for (int i = 0; i < deudas.length; i++) {
                        System.out.println(i + " - CI: " + deudas[i].getCI() + ", Año: " + deudas[i].getAnio() +
                                           ", Impuesto: " + deudas[i].getImpuesto() + ", Monto: " + deudas[i].getMonto());
                    }
                    System.out.println("¿Desea pagar alguna de estas deudas? (si/no)");
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("si")) {
                        System.out.println("Ingrese el indice de la deuda a pagar:");
                        int indice = Integer.parseInt(sc.nextLine());
                        if (indice >= 0 && indice < deudas.length) {
                            boolean pagoExitoso = ruat.Pagar(deudas[indice]);
                            if (pagoExitoso) {
                                System.out.println("Deuda pagada exitosamente.");
                            } else {
                                System.out.println("No se pudo pagar la deuda.");
                            }
                        }
                    }
                } else {
                    System.out.println("No se encontraron deudas para el CI " + CI);
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error en el servidor Ruat: " + e);
        }
    }


    private static Deuda[] deudasEstaticas = {
        new Deuda("1234567", 2022, Impuesto.VEHICULO, 2451),
        new Deuda("1234567", 2022, Impuesto.INMUEBLE, 2500),
        new Deuda("555587", 2021, Impuesto.VEHICULO, 5000),
        new Deuda("333357", 2023, Impuesto.INMUEBLE, 24547)
    };

    @Override
    public Deuda[] buscar(String CI) throws RemoteException {
        ArrayList<Deuda> deudas = new ArrayList<>();
        for (Deuda deuda : deudasEstaticas) {
            if (deuda.getCI().equals(CI)) {
                deudas.add(deuda);
            }
        }
        return deudas.toArray(new Deuda[0]);
    }

    @Override
    public boolean Pagar(Deuda deuda) throws RemoteException {
        boolean tieneObservaciones = alcaldia.BuscarObservaciones(deuda.getCI());
        if (tieneObservaciones) {
            System.out.println("No se puede pagar la deuda debido a observaciones pendientes.");
            return false;
        }
        System.out.println("Se ha pagado la deuda de: " + deuda.getCI() + ", Monto: " + deuda.getMonto());
        return true;
    }

   }
