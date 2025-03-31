package learn.Model;

import java.util.Arrays;

public enum State {
    ALABAMA("AL", "Alabama", 1),
    ALASKA("AK", "Alaska", 2),
    ARIZONA("AZ", "Arizona" ,3),
    ARKANSAS("AR", "Arkansas", 4),
    CALIFORNIA("CA", "California",5),
    COLORADO("CO", "Colorado",6),
    CONNECTICUT("CT", "Connecticut",7),
    DELAWARE("DE", "Delaware",8),
    FLORIDA("FL", "Florida",9),
    GEORGIA("GA", "Georgia",10),
    HAWAII("HI", "Hawaii",11),
    IDAHO("ID", "Idaho",12),
    ILLINOIS("IL", "Illinois",13),
    INDIANA("IN", "Indiana",14),
    IOWA("IA", "Iowa",15),
    KANSAS("KS", "Kansas",16),
    KENTUCKY("KY", "Kentucky",17),
    LOUISIANA("LA", "Louisiana",18),
    MAINE("ME", "Maine",19),
    MARYLAND("MD", "Maryland",20),
    MASSACHUSETTS("MA", "Massachusetts",21),
    MICHIGAN("MI", "Michigan",22),
    MINNESOTA("MN", "Minnesota",23),
    MISSISSIPPI("MS", "Mississippi",24),
    MISSOURI("MO", "Missouri",25),
    MONTANA("MT", "Montana",26),
    NEBRASKA("NE", "Nebraska",27),
    NEVADA("NV", "Nevada",28),
    NEW_HAMPSHIRE("NH", "New Hampshire",29),
    NEW_JERSEY("NJ", "New Jersey",30),
    NEW_MEXICO("NM", "New Mexico",31),
    NEW_YORK("NY", "New York",32),
    NORTH_CAROLINA("NC", "North Carolina",33),
    NORTH_DAKOTA("ND", "North Dakota",34),
    OHIO("OH", "Ohio",35),
    OKLAHOMA("OK", "Oklahoma",36),
    OREGON("OR", "Oregon",37),
    PENNSYLVANIA("PA", "Pennsylvania",38),
    RHODE_ISLAND("RI", "Rhode Island",39),
    SOUTH_CAROLINA("SC", "South Carolina",40),
    SOUTH_DAKOTA("SD", "South Dakota",41),
    TENNESSEE("TN", "Tennessee",42),
    TEXAS("TX", "Texas",43),
    UTAH("UT", "Utah",44),
    VERMONT("VT", "Vermont", 45),
    VIRGINIA("VA", "Virginia", 46),
    WASHINGTON("WA", "Washington",47),
    WEST_VIRGINIA("WV", "West Virginia",48),
    WISCONSIN("WI", "Wisconsin",49),
    WYOMING("WY", "Wyoming",50);

    private String stateAbbr;
    private String stateName;
    private int state_id;

    private State(String stateAbbr, String stateName, int state_id) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
        this.state_id = state_id;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    }

