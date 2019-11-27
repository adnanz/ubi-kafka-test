package com.zafar.kafka.service;

import com.zafar.kafka.model.Message;
import com.zafar.kafka.model.PlayerStats;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 *
 * Created a Singleton static class to store state (PlayerStatsMap).
 *
 * This is definitely not the right implementation
 * but I used it to get the application working. The problem with using Singletons this way is that it's
 * very difficult to test its clients, as it's impossible to substitute a mock implementation for a singleton
 * unless it implements an interface that serves its type. I decided to forgo doing that to get the application
 * working quickly.
 */
public class PlayerStatsService {
    // State of Player Stats
    private static Map<String, PlayerStats> playerStatsMap = new ConcurrentHashMap<>();

    private PlayerStatsService() {
        throw new UnsupportedOperationException();
    }

    public static PlayerStats getPlayerStats(String id) {
        return playerStatsMap.get(id);
    }

    public static int getPlayerStatsCount() {
        return playerStatsMap.size();
    }

    public static void printPlayerStats() {
        for (PlayerStats playerStats : playerStatsMap.values()) {
            System.out.println(playerStats);
        }
    }

    /**
     * <p> Update the player stats by processing the attributes of the provided message </p>
     * @param message message to process to get player stats and update map
     */
    public synchronized static void updatePlayerStats(Message message) {
        String profileId = message.getHeader().getProfileId();

        PlayerStats playerStats = playerStatsMap.getOrDefault(profileId, new PlayerStats(profileId));

        playerStats.logInteractionDate(message.getHeader().getServerDate());

        if (message.getHeader().getEventType().equals("context.stop.MATCH"))
            playerStats.incrementNumOfMatchesPlayed();
        if (message.getHeader().getEventType().equals("custom.RoundWin"))
            playerStats.incrementNumOfRoundWins();
        if (message.getHeader().getEventType().equals("custom.player.MatchWin"))
            playerStats.incrementNumOfMatchWins();

        playerStatsMap.put(profileId, playerStats);
    }
}
