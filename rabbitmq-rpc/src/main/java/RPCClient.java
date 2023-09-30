import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RPCClient implements AutoCloseable {
    private Connection connection;
    private Channel channel;
    private final static String requestQueueName = "rpc";

    public RPCClient() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] args) throws Exception {
        try(RPCClient fibonacciRpc = new RPCClient()) {
            for (int i = 0; i < 32; i++) {
                String i_str = Integer.toString(i);
                System.out.println("Requisitando fibonacci de " + i_str);
                String response = fibonacciRpc.call(i_str);
                System.out.println("Valor de fibonacci recebido: " + response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String call(String message) throws Exception {
        final String correlationId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        BasicProperties props = new BasicProperties.Builder().correlationId(correlationId).replyTo(replyQueueName).build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final CompletableFuture<String> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(correlationId)) {
                response.complete(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {});

        String result = response.get();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}
