package dataAccess.entity;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */

public class LegalCustomer extends Customer {
    private int id;
    private String companyName;
    private String registrationDate;
    private String economicID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String setCompanyName(String companyName) {
        this.companyName = companyName;
        return companyName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
        return registrationDate;
    }

    public String getEconomicID() {
        return economicID;
    }

    public String setEconomicID(String economicID) {
        this.economicID = economicID;
        return economicID;
    }
}
