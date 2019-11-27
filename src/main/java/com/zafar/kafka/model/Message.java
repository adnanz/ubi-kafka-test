package com.zafar.kafka.model;

/**
 * Author: Adnan Zafar
 * Date: Nov 27, 2019
 */
public class Message {
    private Header header;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header=" + header +
                '}';
    }
}
