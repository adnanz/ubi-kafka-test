package com.zafar.kafka;

import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyKafkaConsumerTest {
    MockConsumer<Long, String> consumer;

    @Before
    public void setup() {
        consumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST);
    }

    @Test
    public void testConsumer() {
        /*myKafkaConsumer.consumer = consumer;

        consumer.assign(Arrays.asList(new TopicPartition("my_topic", 0)));

        HashMap<TopicPartition, Long> beginningOffsets = new HashMap<>();
        beginningOffsets.put(new TopicPartition("my_topic", 0), 0L);
        consumer.updateBeginningOffsets(beginningOffsets);

        consumer.addRecord(new ConsumerRecord<>("my_topic", 0, 0L, 123L, "myvalue0"));
        consumer.addRecord(new ConsumerRecord<>("my_topic", 0, 1L, 123L, "myvalue1"));
        consumer.addRecord(new ConsumerRecord<>("my_topic", 0, 2L, 123L, "myvalue2"));
        consumer.addRecord(new ConsumerRecord<>("my_topic", 0, 3L, 123L, "myvalue3"));
        consumer.addRecord(new ConsumerRecord<>("my_topic", 0, 4L, 123L, "myvalue4"));

        myKafkaConsumer.run();*/
    }
}
