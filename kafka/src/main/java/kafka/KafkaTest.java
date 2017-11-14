package kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.*;

public class KafkaTest {

    public static void main(String[] args)  {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.143:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0; i < 1; i++)
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));

        producer.close();

    }

}
