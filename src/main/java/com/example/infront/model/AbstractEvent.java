package com.example.infront.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class AbstractEvent {
    LocalDate date;
    Double shortPercent;
    int shares;
}
