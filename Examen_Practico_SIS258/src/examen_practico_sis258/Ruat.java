
package examen_practico_sis258;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Ruat extends UnicastRemoteObject implements IRuat {
    private final List<Deuda> deudas;
    

    public Ruat() throws RemoteException {
        super();
        deudas = new ArrayList<>();
        cargarDeudas();
    }

    private void cargarDeudas() {
        deudas.add(new Deuda("1234567", 2022, Impuesto.VEHICULO, 2451));
        deudas.add(new Deuda("1234567", 2022, Impuesto.INMUEBLE, 2500));
        deudas.add(new Deuda("555587", 2021, Impuesto.VEHICULO, 5000));
        deudas.add(new Deuda("333357", 2023, Impuesto.INMUEBLE, 24547));
    }

    @Override
    public Deuda[] buscar(String ci) throws RemoteException {
        return deudas.stream().filter(d -> d.getCI().equals(ci)).toArray(Deuda[]::new);
    }

    @Override
    public boolean pagar(Deuda deuda) throws RemoteException {
        if (!verificarObservaciones(deuda.getCI())) {
            return false;
        }
        return deudas.removeIf(d -> d.getCI().equals(deuda.getCI()) &&
                                    d.getAnio() == deuda.getAnio() &&
                                    d.getImpuesto() == deuda.getImpuesto() &&
                                    d.getMonto() == deuda.getMonto());
    }

    private boolean verificarObservaciones(String ci) {
        try (DatagramSocket socket = new DatagramSocket()) {
            String mensaje = "consulta:" + ci;
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.length(), address, 5000);
            socket.send(packet);

            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);
            String respuesta = new String(responsePacket.getData(), 0, responsePacket.getLength());

            return respuesta.equals("respuesta:true");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

