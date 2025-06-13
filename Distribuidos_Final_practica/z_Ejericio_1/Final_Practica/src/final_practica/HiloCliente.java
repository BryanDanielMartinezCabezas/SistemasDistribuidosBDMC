/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package final_practica;

import java.io.*;
import java.net.Socket;

public class HiloCliente extends Thread {
    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            int acumulado = 0;
            int contador = 0;
            String linea;

            while ((linea = entrada.readLine()) != null) {
                int numero = Integer.parseInt(linea);
                if (numero == 0) {
                    salida.println("Cantidad de veces: " + contador + ", Acumulado: " + acumulado);
                    break;
                }
                acumulado += numero;
                contador++;
                salida.println("Acumulado: " + acumulado);
            }
        } catch (IOException e) {
            System.out.println("Error con cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("Cliente desconectado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
