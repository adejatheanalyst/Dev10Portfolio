package org.example.domain;

import org.example.data.DataAccessException;
import org.example.data.InstrumentRepository;
import org.example.models.Instrument;
import org.example.models.InstrumentType;

import java.util.List;

public class InstrumentService {

    InstrumentRepository repository;

    public InstrumentService(InstrumentRepository repository) {
        this.repository = repository;
    }

    public List<Instrument> findAll() throws DataAccessException  {
        return repository.findAll();
    }

    public InstrumentListResult findByType(InstrumentType instrumentType) throws DataAccessException {
        List<Instrument> instruments = repository.findByType(instrumentType);
        InstrumentListResult result = new InstrumentListResult();
        if (instruments.size() == 0) {
            result.addErrorMessage("No instruments were found of type " + instrumentType);
        } else {
            result.setInstrumentList(instruments);
        }
        return result;
    }

    public InstrumentResult findBySerialNumber(String serialNumber) throws DataAccessException {
        return null;
    }

    public InstrumentResult add(Instrument instrument) throws DataAccessException {
        InstrumentResult result = new InstrumentResult();

        validate(instrument, result);

        for (Instrument existingInstrument : findAll()) {
            if (existingInstrument.getSerialNumber().equals(instrument.getSerialNumber())) {
                result.addErrorMessage("Serial number must be unique");
            }
        }

        if (result.isSuccess()) {
            repository.add(instrument);
            result.setInstrument(instrument);
        }

        return result;
    }

    public InstrumentResult update(Instrument instrument) throws DataAccessException {
        InstrumentResult result = new InstrumentResult();

        validate(instrument, result);

        InstrumentType proposedNewType = instrument.getInstrumentType();
        for (Instrument existingInstrument : findAll()) {
            if (existingInstrument.getSerialNumber().equals(instrument.getSerialNumber())) {
                if (existingInstrument.getInstrumentType() != proposedNewType) {
                    result.addErrorMessage("Cannot change instrument type");
                }
            }
        }

        if (result.isSuccess()) {
            boolean didFindForUpdate = repository.update(instrument);
            if (!didFindForUpdate) {
                result.addErrorMessage("Instrument does not exist");
            } else {
                result.setInstrument(instrument);
            }
        }

        return result;
    }

    public SerialNumberResult deleteBySerialNumber(String serialNumber) throws DataAccessException {
        boolean didFindForDeletion = repository.deleteBySerialNumber(serialNumber);

        if (didFindForDeletion) {
            SerialNumberResult result = new SerialNumberResult();
            result.setSerialNumber(serialNumber);
            return result;
        } else {
            SerialNumberResult result = new SerialNumberResult();
            result.addErrorMessage("Could not find instrument for deletion");
            return result;
        }
    }

    private InstrumentResult validate(Instrument instrument, InstrumentResult result) throws DataAccessException {
        if (instrument.getSerialNumber() == null) {
            result.addErrorMessage("Serial number cannot be null");
            return result;
        }

        if (instrument.getSerialNumber().isBlank()) {
            result.addErrorMessage("Serial number cannot be blank");
        }

        if (instrument.getCost() < 0) {
            result.addErrorMessage("Cost must be 0 or positive");
        }

        if (instrument.getStudent() == null) {
            result.addErrorMessage("Student cannot be null");
        }

        return result;
    }
}