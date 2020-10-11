package com.example.demo.activemqApp;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private String id;
    private Date timestamp;

    public Order() {
    }

    public Order(String id, Date timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}