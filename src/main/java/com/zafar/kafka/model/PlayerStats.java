package com.zafar.kafka.model;

import java.util.Date;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 */
public class PlayerStats {
    private String profileId;
    private Date firstTimeSeen;
    private Date lastTimeSeen;
    private int numOfMatchesPlayed;
    private int numOfRoundWins;
    private int numOfMatchWins;

    public PlayerStats(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Date getFirstTimeSeen() {
        return firstTimeSeen;
    }

    private void setFirstTimeSeen(Date firstTimeSeen) {
        this.firstTimeSeen = firstTimeSeen;
    }

    public Date getLastTimeSeen() {
        return lastTimeSeen;
    }

    private void setLastTimeSeen(Date lastTimeSeen) {
        this.lastTimeSeen = lastTimeSeen;
    }

    public int getNumOfMatchesPlayed() {
        return numOfMatchesPlayed;
    }

    public synchronized void incrementNumOfMatchesPlayed() {
        numOfMatchesPlayed++;
    }

    public int getNumOfRoundWins() {
        return numOfRoundWins;
    }

    public int getNumOfMatchWins() {
        return numOfMatchWins;
    }

    public float getWinLossRatio() {
        return (numOfMatchesPlayed != 0 ? (float)numOfMatchWins / (float)numOfMatchesPlayed : 0);
    }

    public synchronized void incrementNumOfMatchWins() {
        numOfMatchWins++;
    }

    public synchronized void incrementNumOfRoundWins() {
        numOfRoundWins++;
    }

    public synchronized void logInteractionDate (Date date) {
        if (getFirstTimeSeen() == null || date.before(getFirstTimeSeen())) {
            setFirstTimeSeen(date);
        }
        if (getLastTimeSeen() == null || date.after(getLastTimeSeen())) {
            setLastTimeSeen(date);
        }
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "profileId='" + profileId + '\'' +
                ", firstTimeSeen=" + firstTimeSeen +
                ", lastTimeSeen=" + lastTimeSeen +
                ", numOfMatchesPlayed=" + numOfMatchesPlayed +
                ", numOfRoundWins=" + numOfRoundWins +
                ", numOfMatchWins=" + numOfMatchWins +
                ", Ratio=" + getWinLossRatio() +
                '}';
    }
}
