package model;

import java.io.Serializable;

public class Event implements Serializable {
    public String id;
    public Long dateTime;
    public String type;

    public Event(String id, Long dateTime, String type) {
        this.id = id;
        this.dateTime = dateTime;
        this.type = type;
    }
}