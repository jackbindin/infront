package com.example.infront.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
public class Event extends AbstractEvent{
    ArrayList<ActivePosition> activePositions;

    public Event(LocalDate date, Double shortPercent, int shares, ArrayList<ActivePosition> activePositions) {
        this.date = date;
        this.shortPercent = shortPercent;
        this.shares = shares;
        this.activePositions = activePositions;
    }
}
