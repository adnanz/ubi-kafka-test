package com.zafar.kafka.model;

import java.util.Date;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 */
public class Header {
    private String profileId;
    private String eventType;
    private Date serverDate;
    private int absolutePlaytime;
    private int relativePlaytime;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
        this.serverDate = serverDate;
    }

    public Integer getAbsolutePlaytime() {
        return absolutePlaytime;
    }

    public void setAbsolutePlaytime(Integer absolutePlaytime) {
        this.absolutePlaytime = absolutePlaytime;
    }

    public Integer getRelativePlaytime() {
        return relativePlaytime;
    }

    public void setRelativePlaytime(Integer relativePlaytime) {
        this.relativePlaytime = relativePlaytime;
    }

    @Override
    public String toString() {
        return "Header{" +
                "profileId='" + profileId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", serverDate=" + serverDate +
                ", absolutePlaytime=" + absolutePlaytime +
                ", relativePlaytime=" + relativePlaytime +
                '}';
    }
}
