package com.zafar.kafka.service;

import com.zafar.kafka.model.Header;
import com.zafar.kafka.model.Message;
import com.zafar.kafka.model.PlayerStats;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PlayerStatsServiceTest {
    @Before
    public void setup() {
        Header header = new Header();
        Message message = new Message();
        header.setProfileId("860cf53909d6f2a3bd3c9d9d5ac923a99329f407");

        message = setupMessage("context.stop.MATCH", "Mon Jun 03 22:21:15 EDT 2019");
        PlayerStatsService.updatePlayerStats(message);
        message = setupMessage("context.stop.MATCH", "Mon Jun 03 22:21:25 EDT 2019");
        PlayerStatsService.updatePlayerStats(message);
        message = setupMessage("context.stop.MATCH", "Mon Jun 03 22:21:33 EDT 2019");
        PlayerStatsService.updatePlayerStats(message);
        message = setupMessage("custom.player.MatchWin", "Mon Jun 03 22:21:25 EDT 2019");
        PlayerStatsService.updatePlayerStats(message);
        message = setupMessage("custom.player.MatchWin", "Mon Jun 03 22:21:33 EDT 2019");
        PlayerStatsService.updatePlayerStats(message);

        PlayerStatsService.updatePlayerStats(message);
    }

    private Message setupMessage (String eventType, String date) {
        Header header = new Header();
        Message message = new Message();

        header.setEventType(eventType);
        header.setServerDate(new Date(date));

        message.setHeader(header);
        return message;
    }

    @Test
    public void checkAllStats() {
        PlayerStats playerStats = PlayerStatsService.getPlayerStats("860cf53909d6f2a3bd3c9d9d5ac923a99329f407");

        Assert.assertEquals(3, playerStats.getNumOfMatchesPlayed());
        Assert.assertEquals(2, playerStats.getNumOfMatchWins());
        Assert.assertEquals(0, playerStats.getNumOfRoundWins());

        Assert.assertEquals(new Date("Mon Jun 03 22:21:15 EDT 2019"), playerStats.getFirstTimeSeen());
        Assert.assertEquals(new Date("Mon Jun 03 22:21:33 EDT 2019"), playerStats.getLastTimeSeen());
    }

    @Test
    public void checkCountOfPlayers() {
        Assert.assertEquals(1, PlayerStatsService.getPlayerStatsCount());
    }
}
