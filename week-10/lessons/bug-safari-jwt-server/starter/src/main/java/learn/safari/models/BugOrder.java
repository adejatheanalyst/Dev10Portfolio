package learn.safari.models;

import java.util.Objects;

public class BugOrder {

    private int bugOrderId;
    private String name;
    private String description;

    public BugOrder() { }

    public BugOrder(int bugOrderId, String name, String description) {
        this.bugOrderId = bugOrderId;
        this.name = name;
        this.description = description;
    }

    public int getBugOrderId() {
        return bugOrderId;
    }

    public void setBugOrderId(int bugOrderId) {
        this.bugOrderId = bugOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugOrder bugOrder = (BugOrder) o;
        return bugOrderId == bugOrder.bugOrderId && name.equals(bugOrder.name) && description.equals(bugOrder.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bugOrderId, name, description);
    }
}
