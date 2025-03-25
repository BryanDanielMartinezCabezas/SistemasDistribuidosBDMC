import java.io.*;
import java.util.Scanner;
import org.jgroups.*;
import org.jgroups.util.Util;

public class StateSyncCluster {
    private JChannel channel;
    private int state = 0; 
    private String nodeName;

    public StateSyncCluster() {
   
        this.nodeName = "EquipoDesconocido";
    }

    public void start() throws Exception {
        
        channel = new JChannel();
        channel.setReceiver(new ReceiverAdapter() {
            @Override
            public void receive(Message msg) {
                
                System.out.println("Mensaje recibido: " + msg.getObject());
            }
            
            @Override
            public void viewAccepted(View view) {
                
                System.out.println("Miembros del grupo: " + view.getMembers());
      
                int index = view.getMembers().indexOf(channel.getAddress());
                nodeName = "Equipo" + (index + 1);
                System.out.println("Soy " + nodeName); 
            }
            
            @Override
            public void getState(OutputStream output) throws Exception {
   
                synchronized (this) {
                    Util.objectToStream(state, new DataOutputStream(output));
                }
                System.out.println("Enviando estado: " + state);
            }
            
            @Override
            public void setState(InputStream input) throws Exception {
           
                int receivedState = (Integer) Util.objectFromStream(new DataInputStream(input));
                System.out.println("Estado recibido: " + receivedState);
                synchronized (this) {
                    state = receivedState;
                }
                System.out.println("Estado actualizado a: " + state);
            }
        });
        channel.connect("StateSyncCluster"); 
        System.out.println("Conectado al grupo 'StateSyncCluster'");

        channel.getState(null, 10000); 
    }


    public void updateState(int newState) throws Exception {
        state = newState;
        String updateMessage = nodeName + " ha actualizado el estado a: " + state;
        Message msg = new Message(null, updateMessage);
        channel.send(msg);
        System.out.println("Estado actualizado a: " + state);
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce un nuevo valor para el estado (o presiona Enter para incrementarlo):");
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                updateState(state + 1);
            } else {
                try {
                    int newVal = Integer.parseInt(line.trim());
                    updateState(newVal);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, por favor ingresa un número.");
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            StateSyncCluster cluster = new StateSyncCluster();
            cluster.start();
            cluster.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
