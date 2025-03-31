package learn;

import java.util.Objects;

public class Registration {

    private String course;
    private double pointPercent;
    private GradeType gradType;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getPointPercent() {
        return pointPercent;
    }

    public void setPointPercent(double pointPercent) {
        this.pointPercent = pointPercent;
    }

    public GradeType getGradType() {
        return gradType;
    }

    public void setGradType(GradeType gradType) {
        this.gradType = gradType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Double.compare(that.pointPercent, pointPercent) == 0 &&
                Objects.equals(course, that.course) &&
                gradType == that.gradType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, pointPercent, gradType);
    }

    @Override
    public String toString() {
        return "Registration{" + "course=" + course + ", pointPercent=" + pointPercent + ", gradType=" + gradType + '}';
    }

}
