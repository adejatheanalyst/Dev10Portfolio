package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// @Repository
public class SolarPanelFileRepository implements SolarPanelRepository {
    private final String filePath;
    private final String delimiter = "~";

    public SolarPanelFileRepository(@Value("./data/solarfarm.txt") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        ArrayList<SolarPanel> result = new ArrayList<>();
        for (SolarPanel solarPanel : findAll()) {
            if (solarPanel.getSection().equalsIgnoreCase(section)) {
                result.add(solarPanel);
            }
        }
        return result;
    }

    @Override
    public SolarPanel findById(int id) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for (SolarPanel solarPanel : all) {
            if (solarPanel.getId() == id) {
                return solarPanel;
            }
        }
        return null;
    }

    @Override
    public SolarPanel create(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();
        int nextId = getNextId(all);
        solarPanel.setId(nextId);
        all.add(solarPanel);
        writeToFile(all);
        return solarPanel;
    }

    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            // Use the id to find the solar panel to update.
            // Using this approach allows the natural key of the solar panel to be updated.
            if (all.get(i).getId() == solarPanel.getId()) {
                all.set(i, solarPanel);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        List<SolarPanel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SolarPanel> findAll() throws DataAccessException {
        ArrayList<SolarPanel> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                SolarPanel sp = lineToSolarPanel(line);
                if (sp != null) {
                    result.add(sp);
                }
            }
        } catch (FileNotFoundException ex) {
            // If the file doesn't exist, no big deal.
            // We'll create it when we add a new solar panel.
            // No file just means no solar panels yet.
        } catch (IOException ex) {
            throw new DataAccessException("Could not open the file path: " + filePath, ex);
        }
        return result;
    }

    private int getNextId(List<SolarPanel> solarPanels) {
        int maxId = 0;
        for (SolarPanel solarPanel : solarPanels) {
            if (maxId < solarPanel.getId()) {
                maxId = solarPanel.getId();
            }
        }
        return maxId + 1;
    }

    private void writeToFile(List<SolarPanel> solarPanels) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (SolarPanel solarPanel : solarPanels) {
                writer.println(solarPanelToLine(solarPanel));
            }
        } catch (IOException ex) {
            throw new DataAccessException("Could not write to file path: " + filePath, ex);
        }
    }

    private SolarPanel lineToSolarPanel(String line) {
        String[] fields = line.split(delimiter);

        if (fields.length != 7) {
            return null;
        }

        return new SolarPanel(
                Integer.parseInt(fields[0]),
                fields[1],
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3]),
                Integer.parseInt(fields[4]),
                Material.valueOf(fields[5]),
                "true".equals(fields[6])
        );
    }

    private String solarPanelToLine(SolarPanel solarPanel) {
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(solarPanel.getId()).append(delimiter);
        buffer.append(cleanField(solarPanel.getSection())).append(delimiter);
        buffer.append(solarPanel.getRow()).append(delimiter);
        buffer.append(solarPanel.getColumn()).append(delimiter);
        buffer.append(solarPanel.getYearInstalled()).append(delimiter);
        buffer.append(solarPanel.getMaterial()).append(delimiter);
        buffer.append(solarPanel.isTracking());
        return buffer.toString();
    }

    private String cleanField(String field) {
        // If the file delimiter, a carriage return, or a newline was written to the file,
        // it would ruin our ability to read the solar panel.
        // Here, we insure those characters don't end up in the file.
        return field.replace(delimiter, "")
                .replace("/r", "")
                .replace("/n", "");
    }
}
