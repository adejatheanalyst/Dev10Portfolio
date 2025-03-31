package learn.fund.ui;

import java.util.ArrayList;
import java.util.HashMap;

public class TerminalTable {

    private ArrayList<String[]> rows = new ArrayList<>();
    private String[] header;
    private HashMap<Integer, Integer> widths = new HashMap<>();
    private int maxRows = 0;

    public void addHeader(String... items) {
        setWidth(items);
        header = items;
    }

    public void addRow(String... items) {
        setWidth(items);
        rows.add(items);
    }

    public String render() {
        StringBuilder buffer = new StringBuilder();
        merge(buffer, header);
        buffer.append("_".repeat(buffer.length()));
        buffer.append("\n");
        for (String[] items : rows) {
            merge(buffer, items);
        }
        return buffer.toString();
    }

    private void setWidth(String... items) {

        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("at least one argument is required");
        }

        maxRows = Math.max(maxRows, items.length);

        for (int i = 0; i < items.length; i++) {
            if (!widths.containsKey(i)) {
                widths.put(i, 0);
            }
            widths.put(i, Math.max(widths.get(i), items[i].length()));
        }
    }

    private void merge(StringBuilder buffer, String[] items) {
        for (int i = 0; i < maxRows; i++) {
            if (i > 0) {
                buffer.append(" | ");
            }
            int width = widths.get(i);
            if (i >= items.length) {
                buffer.append(" ".repeat(width));
            } else {
                buffer.append(items[i]);
                if (items[i].length() < width) {
                    buffer.append(" ".repeat(width - items[i].length()));
                }
            }
        }
        buffer.append("\n");
    }
}
