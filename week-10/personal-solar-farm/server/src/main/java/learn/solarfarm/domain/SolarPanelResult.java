package learn.solarfarm.domain;

import learn.solarfarm.models.SolarPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolarPanelResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private SolarPanel solarPanel;
    private ResultType resultType = ResultType.SUCCESS;

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public void setNotFound(){
        this.resultType = ResultType.NOT_FOUND;
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        this.resultType = ResultType.INVALID;
        messages.add(message);
    }

    public void addErrorMessage(String format, Object... args) {
        this.resultType = resultType;
        messages.add(String.format(format, args));

    }

    public boolean isSuccess() {
        // If result type is success, the operation was successful;
        return this.resultType == ResultType.SUCCESS;
    }

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolarPanelResult that = (SolarPanelResult) o;

        if (!messages.equals(that.messages)) return false;
        return Objects.equals(solarPanel, that.solarPanel);
    }

    @Override
    public int hashCode() {
        int result = messages.hashCode();
        result = 31 * result + (solarPanel != null ? solarPanel.hashCode() : 0);
        return result;
    }
}
