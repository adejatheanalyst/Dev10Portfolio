package learn.memories.ui;

public interface TextIO {

    void println(Object value);

    void print(Object value);

    void printf(String format, Object... values);

    String readString(String prompt);

    boolean readBoolean(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);
}

