package examen_practico_sis258;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;

public class Banco {
    public static void main(String[] args) {
        int port = 1700;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor BANCO iniciado en el puerto " + port);

            IRuat ruat = (IRuat) Naming.lookup("//localhost/RUAT");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                try (BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true)) {

                    String mensaje;
                    while ((mensaje = fromClient.readLine()) != null) {
                        System.out.println("Mensaje recibido: " + mensaje);

                        if (mensaje.startsWith("Deuda:")) {
                            String ci = mensaje.split(":")[1].trim();
                            Deuda[] deudas = ruat.buscar(ci);
                            StringBuilder respuesta = new StringBuilder("Deudas:");

                            for (Deuda d : deudas) {
                                respuesta.append(d.getAnio()).append(",")
                                        .append(d.getImpuesto()).append(",")
                                        .append(d.getMonto()).append(";");
                            }
                            toClient.println(respuesta.toString());
                        } else if (mensaje.startsWith("Pagar:")) {
                            String[] pagos = mensaje.split(":")[1].split(";");
                            boolean resultado = true;

                            for (String pago : pagos) {
                                String[] info = pago.split(",");
                                if (info.length != 3) {
                                    toClient.println("Error: Formato de pago incorrecto");
                                    resultado = false;
                                    break;
                                }

                                int anio = Integer.parseInt(info[0].trim());
                                Impuesto impuesto = Impuesto.valueOf(info[1].toUpperCase().trim());
                                double monto = Double.parseDouble(info[2].trim());

                                String ci = "1234567";
                                //String ci = "555587";

                                Deuda deuda = new Deuda(ci, anio, impuesto, monto);
                                if (!ruat.pagar(deuda)) {
                                    resultado = false;
                                    break;
                                }
                            }
                            toClient.println("transaccion:" + resultado);
                        } else {
                            toClient.println("Error: Comando no valido");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    socket.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
