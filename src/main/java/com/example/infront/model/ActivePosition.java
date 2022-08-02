package com.example.infront.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

public class ActivePosition extends AbstractEvent {
    String positionHolder;

    public ActivePosition(LocalDate date, Double shortPercent, int shares, String positionHolder){
        this.date = date;
        this.shortPercent = shortPercent;
        this.shares = shares;
        this.positionHolder = positionHolder;
    }
}
