package com.example.infront.service;

import com.example.infront.model.Instrument;
import com.example.infront.webclient.SsrWebClientInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class InstrumentService implements InstrumentServiceInterface{
    @Autowired
    SsrWebClientInterface ssrWebClient;

    public Optional<Instrument> getInstrumentByIsin(String isin){
        List<Instrument> instruments = getAllInstruments();
        if(instruments==null){
            return Optional.empty();
        }
        return instruments.stream().filter(i -> i.getIsin().equals(isin)).findFirst();
    }

    public Optional<Instrument> getInstrumentByIsinBetweenDate(String isin, LocalDate from, LocalDate to){
        Optional<Instrument> instrument = getInstrumentByIsin(isin);
        if(instrument.isEmpty()){
            return instrument;
        }
        instrument.get().getEvents().removeIf(
                event -> event.getDate().isBefore(from) || event.getDate().isAfter(to));
        return instrument;
    }

    private List<Instrument> getAllInstruments(){
        return ssrWebClient.getAllInstruments().buffer().blockFirst();
    }
}
