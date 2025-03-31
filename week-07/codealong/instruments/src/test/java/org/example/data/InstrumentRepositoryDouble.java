package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;

import java.util.ArrayList;
import java.util.List;

public class InstrumentRepositoryDouble implements InstrumentRepository {

    public static String EXISTING_SERIAL_NUMBER = "abc";

    private List<Instrument> instruments = new ArrayList<>();

    public InstrumentRepositoryDouble() {
        instruments.add(new Instrument(EXISTING_SERIAL_NUMBER, InstrumentType.TUBA, 500, "Suzie", false));
        instruments.add(new Instrument("123", InstrumentType.VIOLA, 200, "Bobby", true));
    }

    @Override
    public List<Instrument> findAll() throws DataAccessException {
        return instruments;
    }

    @Override
    public List<Instrument> findByType(InstrumentType instrumentType) throws DataAccessException {
        if (instrumentType == InstrumentType.TUBA) {
            return List.of(instruments.get(0));
        } else {
            return List.of();
        }

//        List<Instrument> output = new ArrayList<>();
//
//        for (Instrument instrument : instruments) {
//            if (instrument.getInstrumentType() == instrumentType) {
//                output.add(instrument);
//            }
//        }
//
//        return output;
    }

    @Override
    public Instrument findBySerialNumber(String serialNumber) throws DataAccessException {
        return null;
    }

    @Override
    public Instrument add(Instrument instrument) throws DataAccessException {
        instruments.add(instrument);
        return instrument;
    }

    @Override
    public boolean update(Instrument instrument) throws DataAccessException {
        for (Instrument existingInstrument : findAll()) {
            if (existingInstrument.getSerialNumber().equals(instrument.getSerialNumber())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBySerialNumber(String serialNumber) throws DataAccessException {
        for (Instrument existingInstrument : findAll()) {
            if (existingInstrument.getSerialNumber().equals(serialNumber)) {
                return true;
            }
        }
        return false;
    }
}
