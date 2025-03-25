import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;
import java.io.Serializable;
import java.util.*;
import java.util.Scanner;

public class Votacion {
    private JChannel canal;
    private boolean preguntaActiva = false;
    private String preguntaActual;
    private List<String> opciones;
    private Map<String, Integer> votos = new HashMap<>();
    private String nodoModerador;

    public static class MensajeVotacion implements Serializable {
        private static final long serialVersionUID = 1L;
        public String tipo;
        public String emisor;
        public String pregunta;
        public List<String> opciones;
        public String voto;

        public MensajeVotacion(String tipo, String emisor) {
            this.tipo = tipo;
            this.emisor = emisor;
        }
    }

    public void conectar(String nombreNodo) throws Exception {
        canal = new JChannel();
        canal.setReceiver(new ReceiverAdapter() {
            @Override
            public void receive(Message msg) {
                try {
                    MensajeVotacion m = (MensajeVotacion) msg.getObject();
                    switch(m.tipo) {
                        case "PREGUNTA":
                            if (!preguntaActiva) {
                                preguntaActiva = true;
                                preguntaActual = m.pregunta;
                                opciones = m.opciones;
                                nodoModerador = m.emisor;
                                votos.clear();
                                for (String opcion : opciones) {
                                    votos.put(opcion, 0);
                                }
                                System.out.println("\nNueva pregunta de " + m.emisor);
                                System.out.println("Pregunta: " + preguntaActual);
                                System.out.println("Opciones:");
                                for (int i = 0; i < opciones.size(); i++) {
                                    System.out.println((i+1) + ". " + opciones.get(i));
                                }
                                System.out.println("Para votar, usa: votar <numero>");
                            } else {
                                System.out.println("Ya hay una pregunta activa. No se puede recibir otra.");
                            }
                            break;
                        case "VOTO":
                            if (preguntaActiva) {
                                String opcionElegida = m.voto;
                                if (votos.containsKey(opcionElegida)) {
                                    votos.put(opcionElegida, votos.get(opcionElegida) + 1);
                                }
                                System.out.println(m.emisor + " voto por: " + opcionElegida);
                            }
                            break;
                        case "FIN":
                            if (preguntaActiva) {
                                System.out.println("\nVotacion finalizada. Resultados:");
                                mostrarResultados();
                                preguntaActiva = false;
                                preguntaActual = null;
                                opciones = null;
                                nodoModerador = null;
                                votos.clear();
                            }
                            break;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            
            @Override
            public void viewAccepted(View vista) {
                System.out.println("Miembros del grupo: " + vista.getMembers());
            }
        });
        canal.connect("ClusterVotacion");
        System.out.println(nombreNodo + " conectado al grupo.");
    }

    public void enviarMensaje(MensajeVotacion m) throws Exception {
        Message msg = new Message(null, m);
        canal.send(msg);
    }

    private void mostrarResultados() {
        for (Map.Entry<String, Integer> entry : votos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votos");
        }
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingresa el nombre del nodo: ");
            String nombre = sc.nextLine();
            Votacion vd = new Votacion();
            vd.conectar(nombre);
            while (true) {
                System.out.println("\nComandos disponibles:");
                System.out.println("  preguntar    - Proponer una pregunta (si no hay activa)");
                System.out.println("  votar <num>  - Votar (si hay pregunta activa)");
                System.out.println("  fin          - Finalizar votacion (solo moderador)");
                System.out.print("Ingresa un comando: ");
                String input = sc.nextLine();
                if (input.startsWith("preguntar")) {
                    if (vd.preguntaActiva) {
                        System.out.println("Ya hay una pregunta activa. No puedes hacer otra.");
                    } else {
                        System.out.print("Ingresa la pregunta: ");
                        String preg = sc.nextLine();
                        System.out.print("Ingresa las opciones separadas por coma: ");
                        String opts = sc.nextLine();
                        List<String> listaOpts = new ArrayList<>();
                        for (String s : opts.split(",")) {
                            listaOpts.add(s.trim());
                        }
                        MensajeVotacion m = new MensajeVotacion("PREGUNTA", nombre);
                        m.pregunta = preg;
                        m.opciones = listaOpts;
                        vd.enviarMensaje(m);
                    }
                } else if (input.startsWith("votar")) {
                    if (!vd.preguntaActiva) {
                        System.out.println("No hay pregunta activa para votar.");
                    } else {
                        String[] parts = input.split(" ");
                        if (parts.length < 2) {
                            System.out.println("Uso: votar <numero>");
                        } else {
                            try {
                                int num = Integer.parseInt(parts[1]);
                                if (num < 1 || num > vd.opciones.size()) {
                                    System.out.println("Numero de opcion invalido.");
                                } else {
                                    String opcionElegida = vd.opciones.get(num - 1);
                                    MensajeVotacion m = new MensajeVotacion("VOTO", nombre);
                                    m.voto = opcionElegida;
                                    vd.enviarMensaje(m);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Debe ser un numero.");
                            }
                        }
                    }
                } else if (input.equals("fin")) {
                    if (!vd.preguntaActiva) {
                        System.out.println("No hay pregunta activa.");
                    } else {
                        if (!nombre.equals(vd.nodoModerador)) {
                            System.out.println("Solo el nodo que hizo la pregunta (" + vd.nodoModerador + ") puede finalizar la votacion.");
                        } else {
                            MensajeVotacion m = new MensajeVotacion("FIN", nombre);
                            vd.enviarMensaje(m);
                        }
                    }
                } else {
                    System.out.println("Comando no reconocido.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
