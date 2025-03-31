package learn.field_agent.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agent {

    private int agentId;

    private String middleName;



    private List<AgentAgency> agencies = new ArrayList<>();
    private List<Alias> Aliases = new ArrayList<>();
//created constructor for testing
    public Agent(int agentId,String firstName,String middleName, String lastName, LocalDate dob,int heightInInches) {
        this.heightInInches = heightInInches;
        this.agentId = agentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
    }
    @NotBlank(message = "First Name is required")
    @Size(max = 50, message = "First Name must be less than 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;

    @NotNull(message = "Date of Birth is required")
    @PastOrPresent(message = "Date of Birth must be in the past and cannot be in the future.")
    @Past(message = "Date of birth cannot be younger than 12")
    private LocalDate dob;

    @Min(value = 36, message = "Height must be greater than 36 inches")
    @Max(value = 96, message = "Height must be less than 96 inches")
    private int heightInInches;


    public Agent() {
    }

    public List<Alias> getAliases() {
        return Aliases;
    }

    public void setAliases(List<Alias> aliases) {
        Aliases = aliases;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(int heightInInches) {
        this.heightInInches = heightInInches;
    }

    public List<AgentAgency> getAgencies() {
        return new ArrayList<>(agencies);
    }

    public void setAgencies(List<AgentAgency> agencies) {
        this.agencies = agencies;
    }


}
