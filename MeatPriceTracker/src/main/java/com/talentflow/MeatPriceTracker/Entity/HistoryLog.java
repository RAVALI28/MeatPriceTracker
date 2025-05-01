package com.talentflow.MeatPriceTracker.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_logs")
public class HistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private String actionType;  //ADD, UPDATE, DELETE
    private String section;   //PRODUCT, VENDOR, PRICE

    @Column(columnDefinition = "TEXT")
    private String details;

    private LocalDateTime timeStamp = LocalDateTime.now();

    //Getter and Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    //ARGS-Constructor

    public HistoryLog(long id, long userId, String actionType, String section, String details, LocalDateTime timeStamp) {
        this.id = id;
        this.userId = userId;
        this.actionType = actionType;
        this.section = section;
        this.details = details;
        this.timeStamp = timeStamp;
    }

    //NOARGS-Constructor
    public HistoryLog(){}

}
