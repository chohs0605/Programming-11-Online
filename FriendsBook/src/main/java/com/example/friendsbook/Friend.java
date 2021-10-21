package com.example.friendsbook;

public class Friend {
    private String firstName;
    private String lastName;
    private String email;
    private String school;

    public Friend() {
        this.firstName = new String("");
        this.lastName = new String("");
        this.email = new String("");
        this.school = new String("");
    }

    public Friend(String lName, String fName, String email, String school) {
        this.firstName = new String(fName);
        this.lastName = new String(lName);
        this.email = new String(email);
        this.school = new String(school);
    }

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String fName) {
        this.lastName = fName;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public String toString() {
        return String.format("FirstName: %s\nLastName: %s\nEmail: %s\nSchool: %s",
                this.firstName, this.lastName, this.email, this.school);
    }
}