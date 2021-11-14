package net.lecigne.kafka.tutorial1;

import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

@Slf4j
public class ConsumerDemoAssignAndSeek {

  public static void main(String[] args) {
    var properties = new Properties();
    properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest");

    var consumer = new KafkaConsumer<String, String>(properties);

    // Assign
    var partitionToReadFrom = new TopicPartition("first_topic", 0);
    consumer.assign(Collections.singletonList(partitionToReadFrom));

    // Seek
    consumer.seek(partitionToReadFrom, 15L);

    var keepOnReading = true;
    var numberOfMessagesRead = 0;
    var messageLimit = 5;

    while (keepOnReading) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      for (var rec : records) {
        numberOfMessagesRead++;
        log.info("Partition: {}, offset: {}\nKey: {}, value: {}",
            rec.partition(), rec.offset(), rec.key(), rec.value());
        if (numberOfMessagesRead >= messageLimit) {
          keepOnReading = false;
          break;
        }
      }
    }

    log.info("Exiting the application");

  }
}
