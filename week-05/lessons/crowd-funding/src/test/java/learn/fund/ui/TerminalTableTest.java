package learn.fund.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerminalTableTest {

    @Test
    void shouldRender() {
        TerminalTable tt = new TerminalTable();
        tt.addHeader("One", "Two", "Three");
        tt.addRow("Apple", "Orange");
        tt.addRow("Hydrogen", "Helium", "Lithium", "Carbon");
        String content = tt.render();
    }

}