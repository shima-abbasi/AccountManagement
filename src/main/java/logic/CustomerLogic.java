package logic;

import dataAccess.LegalCustomerCRUD;
import dataAccess.RealCustomerCRUD;
import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class CustomerLogic {

    public void setCustomerInfo(String companyName, String registrationDate, String economicID) throws SQLException, NoValidatedCustomer {

        LegalCustomer legalCustomer = new LegalCustomer();
        LegalCustomerCRUD legalCustomerCRUD = new LegalCustomerCRUD();
        if(legalCustomerCRUD.validateCustomer(economicID)) {
            legalCustomer.setCustomerNumber(generateCustomerNumber());
            legalCustomer.setCompanyName(companyName);
            legalCustomer.setRegistrationDate(registrationDate);
            legalCustomer.setEconomicID(economicID);
            legalCustomerCRUD.createLegalCustomer(legalCustomer);
        }
        else throw new NoValidatedCustomer("noooooooooo");
    }

    public void setCustomerInfo(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, NoValidatedCustomer {
        RealCustomer realCustomer = new RealCustomer();
        RealCustomerCRUD realCustomerCRUD = new RealCustomerCRUD();
        if(realCustomerCRUD.validateCustomer(internationalID)) {
            realCustomer.setCustomerNumber(generateCustomerNumber());
            realCustomer.setFirstName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setFatherName(fatherName);
            realCustomer.setDateOfBirth(dateOfBirth);
            realCustomer.setInternationalID(internationalID);
        }
        else throw  new NoValidatedCustomer("noooooo");

    }

    public String generateCustomerNumber() throws SQLException {
        String customerNumber = "";
        ResultSet resultSet = DBConnection.getDBConnection().createStatement().executeQuery("SELECT customer_number FROM customer");
        System.out.println(resultSet);
        while (resultSet.next()) {
            customerNumber = resultSet.getString(1);
        }
        if (customerNumber == "") {
            System.out.println("ooooooooo");
            return "1000";
        } else {
            System.out.println("haaaaaaaaa");
            return String.valueOf((Integer.parseInt(customerNumber)+1));
        }
    }
}
