package notificador_pago;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Notificador_Pago {

    private final static String QUEUE_NAME = "colaPagos";

    public static void main(String[] args) {
        // Configuración del consumidor RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // Declarar la cola desde la cual leeremos
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Esperando mensajes de pago desde la plataforma...");

            // Callback que se ejecuta cuando llega un mensaje
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mensaje = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Pago recibido:\n" + mensaje);
            };

            // Comenzar a consumir mensajes de la cola
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
