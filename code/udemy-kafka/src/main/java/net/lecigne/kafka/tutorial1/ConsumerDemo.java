package net.lecigne.kafka.tutorial1;

import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

@Slf4j
public class ConsumerDemo {

  public static void main(String[] args) {
    var properties = new Properties();
    properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(GROUP_ID_CONFIG, "my-java-app");
    properties.setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest");

    var consumer = new KafkaConsumer<String, String>(properties);
    consumer.subscribe(Collections.singleton("first_topic"));
    //noinspection InfiniteLoopStatement
    while (true) { // NOSONAR on the infinite loop either
      consumer.poll(Duration.ofMillis(100)).forEach(rec -> log.info("Partition: {}, offset: {}\nKey: {}, value: {}",
          rec.partition(), rec.offset(), rec.key(), rec.value()));
    }
  }

}
