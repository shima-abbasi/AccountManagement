package dataAccess.entity;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */
public class RealCustomer extends Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String dateOfBirth;
    private String internationalID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String setFirstName(String firstName) {
        this.firstName = firstName;
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String lastName) {
        this.lastName = lastName;
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return fatherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return dateOfBirth;
    }

    public String getInternationalID() {
        return internationalID;
    }

    public String setInternationalID(String internationalID) {
        this.internationalID = internationalID;
        return internationalID;
    }
}

