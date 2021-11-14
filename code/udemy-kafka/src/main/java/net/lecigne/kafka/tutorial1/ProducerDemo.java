package net.lecigne.kafka.tutorial1;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {

  public static void main(String[] args) {
    // Create producer properties
    var properties = new Properties();
    properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // WSL: use the correct IP
    properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    // Create the producer
    var producer = new KafkaProducer<String, String>(properties);

    // Create a producer record
    var producerRecord = new ProducerRecord<String, String>("first_topic", "hello world from Java");

    // Send data - asynchronous!
    producer.send(producerRecord);
    producer.close();
  }
}
