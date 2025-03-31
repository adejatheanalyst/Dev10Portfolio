package learn.solarfarm.data;
import learn.solarfarm.models.SolarPanel;
import learn.solarfarm.models.Material;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolarPanelFileRepository implements SolarPanelRepository {
    private final String filePath;
    private final String delimiter = "~";

    // Constructor to initialize file path
    public SolarPanelFileRepository(String filePath) {
        this.filePath = filePath;
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

    @Override
    public SolarPanel findByKey(String section, int row, int column) throws DataAccessException {
        List<SolarPanel> panels = findAll();
        for (SolarPanel panel : panels) {
            if (panel.getSection().equals(section) && panel.getRow() == row && panel.getColumn() == column) {
                return panel;
            }
        }
        return null; // Not found
    }

    @Override
    public List<SolarPanel> findBySection(String section) throws DataAccessException {
        List<SolarPanel> panels = findAll();
        List<SolarPanel> result = new ArrayList<>();

        // Loop through the panels and add those that match the section
        for (SolarPanel panel : panels) {
            if (panel.getSection().equals(section)) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public boolean create(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> panels = findAll();
        solarPanel.setId(getNextId(panels));
        panels.add(solarPanel);
        writeToFile(panels);
        return false;
    }


    @Override
    public boolean update(SolarPanel solarPanel) throws DataAccessException {
        List<SolarPanel> panels = findAll();
        for (int i = 0; i < panels.size(); i++) {
            if (panels.get(i).getId() == solarPanel.getId()) {
                panels.set(i, solarPanel); // Update the panel
                writeToFile(panels);
                return true;
            }
        }return false;
    }

    @Override
    public boolean deleteById(int id) throws DataAccessException {
        List<SolarPanel> panels = findAll();
        panels.removeIf(panel -> panel.getId() == id);
        writeToFile(panels);
        return true;
    }

    // Returns the next available ID (one greater than the current maximum ID)
    @Override
    public int getNextId(List<SolarPanel> panels) {
        int nextId = 1;
        for (SolarPanel panel : panels) {
            if (panel.getId() >= nextId) {
                nextId = panel.getId() + 1;
            }
        }
        return nextId;
    }

    // Load solar panels from a file
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




