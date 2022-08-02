package com.example.infront.service;


import com.example.infront.model.ActivePosition;
import com.example.infront.model.Event;
import com.example.infront.model.Instrument;
import com.example.infront.webclient.SsrWebClientInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstrumentServiceTest {
    @InjectMocks
    InstrumentService instrumentService;

    @Mock
    SsrWebClientInterface ssrWebClient;

    @Test
    public void testGetInstrumentsByIsin(){
        when(ssrWebClient.getAllInstruments()).thenReturn(Flux.fromIterable(getInstrumentList()));
        assertEquals(instrumentService.getInstrumentByIsin("isin1").get().getIssuerName(),"issuer1");
    }

    @Test
    public void testGetInstrumentsByIsinAndDate(){
        when(ssrWebClient.getAllInstruments()).thenReturn(Flux.fromIterable(getInstrumentList()));
        LocalDate from = LocalDate.of(2022, 1,1);
        LocalDate to = LocalDate.of(2022, 1,2);
        assertEquals(instrumentService.getInstrumentByIsinBetweenDate("isin1",from,to).get().getEvents().size(),2);
    }

    private List<Instrument> getInstrumentList(){
        ArrayList<ActivePosition> activePositions = new ArrayList<>();
        activePositions.add(new ActivePosition(
                LocalDate.of(2022, 1,1),0.25,2,"h1"));
        activePositions.add(new ActivePosition(
                LocalDate.of(2022,1,2),0.25,2,"h2"));
        activePositions.add(new ActivePosition(
                LocalDate.of(2022,1,3),0.25,2,"h3"));
        LinkedList<Event> events = new LinkedList<>();
        events.add(new Event(
                LocalDate.of(2022, 1,1),0.25,2,activePositions));
        events.add(new Event(
                LocalDate.of(2022, 1,2),0.25,2,activePositions));
        events.add(new Event(
                LocalDate.of(2022, 1,3),0.25,2,activePositions));
        Instrument instrument1 = new Instrument("isin1","issuer1",events);
        List<Instrument> instruments = new LinkedList<>();
        instruments.add(instrument1);
        return instruments;
    }

}
