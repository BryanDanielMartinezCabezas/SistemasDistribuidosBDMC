/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi_tarea;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Briller
 */
public class Server   {
    
     public static void main (String[] args){
       try {
           Operaciones operaciones=new Operaciones();
           LocateRegistry.createRegistry(1099); //levantar el servidor de registro;
          Naming.bind("O",operaciones);
           
       } catch (RemoteException ex) {
           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       } catch (AlreadyBoundException ex) {
           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       } catch (MalformedURLException ex) {
           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       }
     
     
     
     
     
     }  
    
}
