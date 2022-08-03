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
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

@RestController
public class InstrumentController {
    @Autowired
    InstrumentServiceInterface instrumentService;

    @GetMapping("instrument/{isin}")
    public ResponseEntity<Instrument> getByInstrumentIsin(@PathVariable String isin) {
        try {
            return new ResponseEntity<>(instrumentService.getInstrumentByIsin(isin).get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new Instrument(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("instrument/{isin}/{fromDate}/{toDate}")
    public ResponseEntity<Instrument> getByInstrumentIsinAndDate(@PathVariable String isin, @PathVariable String fromDate, @PathVariable String toDate) throws ParseException {
        try {
            LocalDate formattedFromDate = LocalDate.parse(fromDate, DateTimeFormatter.ISO_DATE);
            LocalDate formattedToDate = LocalDate.parse(toDate, DateTimeFormatter.ISO_DATE);
            return new ResponseEntity<>(instrumentService.getInstrumentByIsinBetweenDate(isin, formattedFromDate, formattedToDate).get(), HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(new Instrument(), HttpStatus.BAD_REQUEST);
        }
    }
}
