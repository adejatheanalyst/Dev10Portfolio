package learn;

public class StudentSummary {
    String country;
    String major;
     double IQ;


    public StudentSummary(double IQ, String major, String country) {
        this.IQ = IQ;
        this.major = major;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getIQ() {
        return IQ;
    }

    public void setIQ(double IQ) {
        this.IQ = IQ;
    }
}
