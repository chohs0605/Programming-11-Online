public class Teacher {
    String firstName;
    String lastName;
    String subject;

    public Teacher(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // print "Name: name Subject: subject"
    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + " Subject: " + this.subject;
    }
}
