
package practica;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;

import java.util.logging.Logger;



public class ASFI extends UnicastRemoteObject implements IASFI {
    
       public ASFI() throws RemoteException {
        super();
    }

    @Override
public ArrayList<Cuenta> ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
    ArrayList<Cuenta>  cuentas=  new ArrayList<>();
    System.out.println("Ingresaste a consulta de cuentas");
    
        try {
            DatagramSocket socketMercantil = new DatagramSocket();
            String mensaje = "Buscar:"+ci+"-"+nombres+"-"+apellidos;
            InetAddress address = InetAddress.getByName("localhost");
            
            DatagramPacket packetM = new DatagramPacket(mensaje.getBytes(), mensaje.length(), address, 9999);
            socketMercantil.send(packetM);
            
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
            socketMercantil.receive(paqueteRespuesta);
            String respuesta = new String(paqueteRespuesta.getData(),0,paqueteRespuesta.getLength());
            socketMercantil.close();
            
            
            
            if (!respuesta.trim().isEmpty()){
                String[] cuentasArray = respuesta.split(":");
                for(String cuentaDatos: cuentasArray){
                    String [] datos = cuentaDatos.split("-");
                    if(datos.length==2){
                        String Ncuenta = datos[0];
                        double saldo = Double.parseDouble(datos[1]);
                        Cuenta cuenta= new Cuenta(Banco.Mercantil,ci,nombres,apellidos,saldo,Ncuenta);
                        cuentas.add(cuenta);
                    }
                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(ASFI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ASFI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try { 
            Socket SocketBCP = new Socket("localhost",1700);
            String mensajeBcp = "Buscar:"+ci+"-"+ nombres +"-"+apellidos;
            PrintStream  Salida = new PrintStream (SocketBCP.getOutputStream()); 
            Salida.println(mensajeBcp);
            
            BufferedReader  Entrada = new BufferedReader (new InputStreamReader(SocketBCP.getInputStream()));
            String RespuestaBcp= Entrada.readLine();
            
            
            if(RespuestaBcp != null && !RespuestaBcp.isEmpty()){
                String cuentaArray[] = RespuestaBcp.split(":");
                for(String CuentaDatos : cuentaArray){
                    String [] Datos = CuentaDatos.split("-");
                    if (Datos.length == 2){
                        String NumeroCuenta = Datos[0];
                        double saldo = Double.parseDouble(Datos[1]);
                        Cuenta cuentaBcp = new Cuenta (Banco.BCP,ci,nombres,apellidos,saldo,NumeroCuenta);
                        cuentas.add(cuentaBcp);
                    
                    }
                
                }
            
            }
            SocketBCP.close(); 
        } catch (IOException ex) {
            Logger.getLogger(ASFI.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    
    return cuentas;
}

    
    

    @Override
    public boolean RetenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException {
    boolean exito = false;
    
    // Si la cuenta es del Banco Mercantil
    if (cuenta.getBanco() == Banco.Mercantil) {
        try {
            DatagramSocket socketMercantil = new DatagramSocket();
            String mensaje = "Congelar:" + cuenta.getNrocuenta() + "-" + monto;
            InetAddress address = InetAddress.getByName("localhost");

            DatagramPacket packetM = new DatagramPacket(mensaje.getBytes(), mensaje.length(), address, 9999);
            socketMercantil.send(packetM);

            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
            socketMercantil.receive(paqueteRespuesta);
            String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());

            socketMercantil.close();

            // Verificamos si el banco Mercantil ha procesado la retención
            if (respuesta.startsWith("SI-")) {
                exito = true;
                System.out.println("Retención exitosa en Banco Mercantil");
            } else {
                System.out.println("No se pudo realizar la retención en Banco Mercantil");
            }
        } catch (IOException ex) {
            Logger.getLogger(ASFI.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    // Si la cuenta es del Banco BCP
    else if (cuenta.getBanco() == Banco.BCP) {
        try {
            Socket socketBcp = new Socket("localhost", 1700);
            String mensajeBcp = "Congelar:" + cuenta.getNrocuenta() + "-" + monto;
            PrintStream salida = new PrintStream(socketBcp.getOutputStream());
            salida.println(mensajeBcp);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketBcp.getInputStream()));
            String respuestaBcp = entrada.readLine();

            socketBcp.close();

            // Verificamos si el banco BCP ha procesado la retención
            if (respuestaBcp != null && respuestaBcp.startsWith("SI-")) {
                exito = true;
                System.out.println("Retención exitosa en Banco BCP");
            } else {
                System.out.println("No se pudo realizar la retención en Banco BCP");
            }
        } catch (IOException ex) {
            Logger.getLogger(ASFI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return exito;
    }
    
}
