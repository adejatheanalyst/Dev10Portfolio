package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InstrumentFileRepository implements InstrumentRepository {

    private String filePath;

    private String delimiter = ",";

    private String header = "serialNumber,instrumentType,cost,student,needsRepair";

    public InstrumentFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Instrument> findAll() throws DataAccessException{
        ArrayList<Instrument> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Instrument m = convertLineToInstrument(line);
                if (m != null) {
                    result.add(m);
                }
            }
        } catch (FileNotFoundException ex) {
            // If the file doesn't exist, no big deal.
            // We'll create it when we add a new memory.
            // No file just means no memories yet.
        } catch (IOException ex) {
            throw new DataAccessException("Could not open the file path: " + filePath, ex);
        }
        return result;
    }

    @Override
    public List<Instrument> findByType(InstrumentType instrumentType) throws DataAccessException {
        List<Instrument> output = new ArrayList<>();
        for (Instrument instrument : findAll()) {
            if (instrument.getInstrumentType() == instrumentType) {
                output.add(instrument);
            }
        }
        return output;
    }

    @Override
    public Instrument findBySerialNumber(String serialNumber) {
        return null;
    }

    @Override
    public Instrument add(Instrument instrument) throws DataAccessException {
        List<Instrument> all = findAll();
        all.add(instrument);
        writeToFile(all);
        return instrument;
    }

    @Override
    public boolean update(Instrument instrument) throws DataAccessException {
        List<Instrument> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getSerialNumber().equals(instrument.getSerialNumber())) {
                all.set(i, instrument);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBySerialNumber(String serialNumber) throws DataAccessException {
        List<Instrument> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getSerialNumber().equals(serialNumber)) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    private Instrument convertLineToInstrument(String line) {
        if (line.equals(header)) {
            return null;
        }

        String[] fields = line.split(delimiter);

        if (fields.length != 5) {
            return null;
        }

        return new Instrument(
                fields[0],
                InstrumentType.valueOf(fields[1]),
                Integer.parseInt(fields[2]),
                fields[3],
                "true".equals(fields[4])
        );
    }

    private void writeToFile(List<Instrument> instruments) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(header);

            for (Instrument instrument : instruments) {
                writer.println(convertInstrumentToLine(instrument));
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not write to file path: " + filePath, ex);
        }
    }

    private String convertInstrumentToLine(Instrument instrument) {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(instrument.getSerialNumber()).append(delimiter);
        buffer.append(instrument.getInstrumentType()).append(delimiter);
        buffer.append(instrument.getCost()).append(delimiter);
        buffer.append(instrument.getStudent()).append(delimiter);
        buffer.append(instrument.isNeedsRepair());
        return buffer.toString();
    }
}