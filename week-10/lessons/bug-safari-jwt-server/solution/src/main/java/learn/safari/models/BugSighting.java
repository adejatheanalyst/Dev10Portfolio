package learn.safari.models;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class BugSighting {

    @PositiveOrZero
    private int sightingId;

    @NotBlank(message = "bug type cannot be blank.")
    private String bugType;

    @NotNull(message = "bug order cannot be null.")
    private BugOrder order;

    @NotBlank(message = "description cannot be blank.")
    private String description;

    @NotNull(message = "date cannot be null.")
    @PastOrPresent(message = "date cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Positive(message = "interest must be positive.")
    private double interest;

    private String imageUrl;

    public BugSighting() { }

    public BugSighting(int sightingId, String bugType, BugOrder order, String description, LocalDate date, double interest, String imageUrl) {
        this.sightingId = sightingId;
        this.bugType = bugType;
        this.order = order;
        this.description = description;
        this.date = date;
        this.interest = interest;
        this.imageUrl = imageUrl;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public BugOrder getOrder() {
        return order;
    }

    public void setOrder(BugOrder order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugSighting that = (BugSighting) o;
        return sightingId == that.sightingId && Double.compare(that.interest, interest) == 0
                && bugType.equals(that.bugType) && order.equals(that.order)
                && description.equals(that.description) && date.equals(that.date)
                && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingId, bugType, order, description, date, interest, imageUrl);
    }
}
