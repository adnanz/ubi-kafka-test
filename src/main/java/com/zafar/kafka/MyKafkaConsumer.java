package com.zafar.kafka;

import com.google.gson.Gson;
import com.zafar.kafka.model.Message;
import com.zafar.kafka.service.PlayerStatsService;
import com.zafar.kafka.utils.PropertiesTool;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 */
public class MyKafkaConsumer implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(MyKafkaConsumer.class);

    public final Consumer<Long, String> consumer;

    private final List<String> topics;
    private final int id;
    private static final String BOOTSTRAP_SERVERS = "kafka.bootstrap.servers";
    private Gson gson;

    public MyKafkaConsumer(int id, String groupId, List<String> topics) {
        this.id = id;
        this.topics = topics;

        gson = new Gson();

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, PropertiesTool.getParameter(BOOTSTRAP_SERVERS));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        logger.debug("{}", props);

        // Create the consumer using props.
        this.consumer = new KafkaConsumer<>(props);
    }

    @Override
    public void run() {
        logger.info("Starting kafka consumer {}", id);

        try {
            // Subscribe to the topic.
            consumer.subscribe(topics);
            consumer.poll(Duration.ofMillis(0));
            consumer.seekToBeginning(consumer.assignment());

            while (true) {
                ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord record : consumerRecords) {
                    try {
                        Message message = gson.fromJson((String) record.value(), Message.class);
                        PlayerStatsService.updatePlayerStats(message);
                    }
                    catch (Exception e) {
                        logger.error("Error with json : {}", record.value());
                    }
                }

                consumer.commitAsync();
            }
        }
        catch (WakeupException e) {
            // ignore for shutdown
        }
        finally {
            logger.info("Closing kafka consumer {}", id);
            consumer.close();
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }
}
