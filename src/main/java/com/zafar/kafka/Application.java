package com.zafar.kafka;

import com.zafar.kafka.service.PlayerStatsService;
import com.zafar.kafka.utils.PropertiesTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 */
public class Application {
    private static final String GROUP_ID = "kafka.group.id";
    private static final String TOPICS = "kafka.topics";
    private static final String NUM_CONSUMERS = "kafka.partitions";
    private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) {
        System.out.println("Application Started");

        String groupId = PropertiesTool.getParameter(GROUP_ID);
        List<String> topics = Arrays.asList(PropertiesTool.getParameter(TOPICS).split(COMMA_DELIMITER));
        int numOfPartitions = Integer.parseInt(PropertiesTool.getParameter(NUM_CONSUMERS));

        ExecutorService executor = Executors.newFixedThreadPool(numOfPartitions);

        final List<MyKafkaConsumer> consumers = new ArrayList<>();

        for (int i = 0; i < numOfPartitions; i++) {
            MyKafkaConsumer consumer = new MyKafkaConsumer(i, groupId, topics);
            consumers.add(consumer);
            executor.submit(consumer);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (MyKafkaConsumer consumer : consumers) {
                consumer.shutdown();
            }
            PlayerStatsService.printPlayerStats();
            executor.shutdown();
            try {
                executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
