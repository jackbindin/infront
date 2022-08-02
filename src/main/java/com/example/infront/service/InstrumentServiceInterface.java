package com.example.infront.service;

import com.example.infront.model.Instrument;

import java.time.LocalDate;
import java.util.Optional;

public interface InstrumentServiceInterface {
    Optional<Instrument> getInstrumentByIsin(String isin);
    Optional<Instrument> getInstrumentByIsinBetweenDate(String isin, LocalDate from, LocalDate to);
}
