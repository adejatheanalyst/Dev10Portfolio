package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;

import java.util.List;

public interface InstrumentRepository {

    public List<Instrument> findAll() throws DataAccessException;

    public List<Instrument> findByType(InstrumentType instrumentType) throws DataAccessException;
    // Other find-bys?

    public Instrument findBySerialNumber(String serialNumber) throws DataAccessException;

    public Instrument add(Instrument instrument) throws DataAccessException;

    public boolean update(Instrument instrument) throws DataAccessException;

    public boolean deleteBySerialNumber(String serialNumber) throws DataAccessException;

}
