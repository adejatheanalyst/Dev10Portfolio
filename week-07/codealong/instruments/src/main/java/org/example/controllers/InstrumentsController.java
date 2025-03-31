package org.example.controllers;

import org.example.data.DataAccessException;
import org.example.domain.InstrumentListResult;
import org.example.domain.InstrumentResult;
import org.example.domain.InstrumentService;
import org.example.domain.SerialNumberResult;
import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/instruments")
@RestController
public class InstrumentsController {

    private InstrumentService service;

    public InstrumentsController(InstrumentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Instrument>> findAll() throws DataAccessException {
        List<Instrument> allInstruments = service.findAll();
        return new ResponseEntity<>(allInstruments, HttpStatus.OK);
    }

    @GetMapping("/byType/{instrumentTypeString}")
    public ResponseEntity<Object> findByTypePathVariable(@PathVariable String instrumentTypeString) throws DataAccessException {
        InstrumentType instrumentType;

//        try{
            instrumentType = InstrumentType.valueOf(instrumentTypeString);
//        }catch(Illegal)

        return findInstrumentsByType(instrumentType);
    }

    @GetMapping("/byType")
    public ResponseEntity<Object> findByTypeRequestParam(@RequestParam InstrumentType instrumentType) throws DataAccessException {
        return findInstrumentsByType(instrumentType);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Instrument instrument) throws DataAccessException {
        InstrumentResult result = service.add(instrument);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getInstrument(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Object> update(@RequestBody Instrument instrument, @PathVariable String serialNumber) throws DataAccessException {
        if (!serialNumber.equals(instrument.getSerialNumber())) {
            return new ResponseEntity<>("Serial Numbers must match", HttpStatus.CONFLICT);
        }

        InstrumentResult result = service.update(instrument);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getInstrument(), HttpStatus.NO_CONTENT);
        } else {
            // todo: give results a resultType
            if (result.getErrorMessages().get(0).contains("does not exist")) {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Object> delete(@PathVariable String serialNumber) throws DataAccessException {
        SerialNumberResult result = service.deleteBySerialNumber(serialNumber);
        if (result.isSuccess()) {
            return new ResponseEntity<> (result.getSerialNumber(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Object> findInstrumentsByType(InstrumentType instrumentType) throws DataAccessException {
        InstrumentListResult result = service.findByType(instrumentType);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getInstrumentList(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
    }

}