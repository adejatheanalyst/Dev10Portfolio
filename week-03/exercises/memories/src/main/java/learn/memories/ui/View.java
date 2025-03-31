package learn.memories.ui;

import learn.memories.models.Memory;

import java.util.List;

public class View {

    private final TextIO io;

    public View(TextIO io) {
        this.io = io;
    }

    public int chooseMenuOption() {
        displayHeader("Main Menu");
        io.println("0. Exit");
        io.println("1. View Memories");
        io.println("2. Add a Memory");
        io.println("3. Update a Memory");
        io.println("4. Delete a Memory");
        return io.readInt("Choose [0-4]:", 0, 4);
    }

    public Memory chooseMemory(List<Memory> memories) {
        displayMemories(memories);
        Memory result = null;
        if (memories.size() > 0) {
            int memoryId = io.readInt("Choose a Memory ID:");
            for (Memory m : memories) {
                if (m.getId() == memoryId) {
                    result = m;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isPublic() {
        io.println("1. Public");
        io.println("2. Private");
        return io.readInt("Choose [1-2]:", 1, 2) == 1;
    }

    public void displayHeader(String message) {
        int length = message.length();
        io.println("");
        io.println(message);
        io.println("=".repeat(length));
    }

    public void displayErrors(List<String> errors) {
        displayHeader("Errors:");
        for (String error : errors) {
            io.println(error);
        }
    }

    public void displayMemories(List<Memory> memories) {
        if (memories.size() == 0) {
            displayHeader("No Memories Found.");
        } else {
            displayHeader("Memories:");
            for (Memory m : memories) {
                io.printf("ID: %s, From: %s%n%s%n", m.getId(), m.getFrom(), m.getContent());
            }
        }
    }

    public void displayMessage(String message) {
        io.println("");
        io.println(message);
    }

    public Memory createMemory() {
        displayHeader("Add a Memory");
        Memory result = new Memory();
        result.setFrom(io.readString("From: "));
        result.setContent(io.readString("Content: "));
        result.setShareable(io.readBoolean("Shareable [y/n]: "));
        return result;
    }

    public Memory editMemory(Memory m) {
        displayHeader("Update");

        String from = io.readString("From (" + m.getFrom() + "): ");
        // only update if it changed
        if (from.trim().length() > 0) {
            m.setFrom(from);
        }

        String content = io.readString("Content (" + m.getContent() + "): ");
        if (content.trim().length() > 0) {
            m.setContent(content);
        }

        String shareable = io.readString("Shareable (" + (m.isShareable() ? "y" : "n") + ") [y/n]: ");
        if (shareable.trim().length() > 0) {
            m.setShareable(shareable.equalsIgnoreCase("y"));
        }
        return m;
    }
}
