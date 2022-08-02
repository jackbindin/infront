package com.example.infront.controller;

import com.example.infront.model.Instrument;
import com.example.infront.service.InstrumentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
public class InstrumentController {
    @Autowired
    InstrumentServiceInterface instrumentService;

    @GetMapping("instrument/{isin}")
    public ResponseEntity<Optional<Instrument>> getByInstrumentIsin(@PathVariable String isin){
        return new ResponseEntity<>(instrumentService.getInstrumentByIsin(isin), HttpStatus.OK);
    }

    @GetMapping("instrument/{isin}/{fromDate}/{toDate}")
    public ResponseEntity<Optional<Instrument>> getByInstrumentIsinAndDate(@PathVariable String isin,@PathVariable String fromDate,@PathVariable String toDate) throws ParseException {
        LocalDate formattedFromDate = LocalDate.parse(fromDate,DateTimeFormatter.ISO_DATE);
        LocalDate formattedToDate = LocalDate.parse(toDate,DateTimeFormatter.ISO_DATE);
        return new ResponseEntity<>(instrumentService.getInstrumentByIsinBetweenDate(isin,formattedFromDate,formattedToDate), HttpStatus.OK);
    }
}
