package net.lecigne.kafka.tutorial1;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

@Slf4j
public class ProducerDemoWithKeys {

  public static void main(String[] args) {
    var properties = new Properties();
    properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // WSL: use the correct IP
    properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    var producer = new KafkaProducer<String, String>(properties);

    IntStream.rangeClosed(1, 10).forEach(i -> {
      var topic = "first_topic";
      var value = "hello world from Java #" + i;
      var key = "id_" + i;
      var producerRecord = new ProducerRecord<>(topic, key, value);
      log.info("Key: {}", key);
      try {
        producer.send(producerRecord, ((metadata, e) -> {
          if (e == null) {
            // The record was successfully sent
            log.info("Received new metadata\nTopic: {}\nPartition: {}\nOffset: {}\nTimestamp: {}",
                metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
          } else {
            log.error("Error while producing", e);
          }
        })).get(); // Block the send to make it synchronous - don't do this in production!
      } catch (InterruptedException | ExecutionException e) {
        log.error("Oops", e);
        Thread.currentThread().interrupt();
      }
    });

    producer.close();
  }

}
