package logic;

        import dataAccess.LegalCustomerCRUD;
        import dataAccess.RealCustomerCRUD;
        import dataAccess.connectionutil.DBConnection;
        import dataAccess.entity.LegalCustomer;
        import dataAccess.entity.RealCustomer;
        import exceptions.NoValidatedCustomer;

        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class CustomerLogic {

    public LegalCustomer setCustomerInfo(String companyName, String registrationDate, String economicID) throws SQLException, NoValidatedCustomer {

        LegalCustomer legalCustomer = new LegalCustomer();
        LegalCustomerCRUD legalCustomerCRUD = new LegalCustomerCRUD();
        if (legalCustomerCRUD.validateCustomer(economicID)) {
            legalCustomer.setCustomerNumber(generateCustomerNumber());
            legalCustomer.setCompanyName(companyName);
            legalCustomer.setRegistrationDate(registrationDate);
            legalCustomer.setEconomicID(economicID);
            legalCustomerCRUD.createLegalCustomer(legalCustomer);
        } else throw new NoValidatedCustomer("this Legal Customer already exist");
        return legalCustomer;
    }

    public RealCustomer setCustomerInfo(String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException, NoValidatedCustomer {
        RealCustomer realCustomer = new RealCustomer();
        RealCustomerCRUD realCustomerCRUD = new RealCustomerCRUD();
        if (realCustomerCRUD.validateCustomer(internationalID)) {
            realCustomer.setCustomerNumber(generateCustomerNumber());
            realCustomer.setFirstName(firstName);
            realCustomer.setLastName(lastName);
            realCustomer.setFatherName(fatherName);
            realCustomer.setDateOfBirth(dateOfBirth);
            realCustomer.setInternationalID(internationalID);
            realCustomerCRUD.createRealCustomer(realCustomer);
        } else throw new NoValidatedCustomer("this Real Customer already exist");

        return realCustomer;
    }

    public String generateCustomerNumber() throws SQLException {
        String customerNumber = "";
        ResultSet resultSet = DBConnection.getDBConnection().createStatement().executeQuery("SELECT customer_number FROM customer");
        System.out.println(resultSet);
        while (resultSet.next()) {
            customerNumber = resultSet.getString(1);
        }
        if (customerNumber == "") {
            System.out.println("The first customer number created");
            return "1000";
        } else {
            System.out.println("customer number created");
            return String.valueOf((Integer.parseInt(customerNumber) + 1));
        }
    }

    public ArrayList<LegalCustomer> searchCustomer(String customerNumber, String companyName, String registrationDate, String economicID) throws SQLException {
        LegalCustomerCRUD legalCustomerCRUD = new LegalCustomerCRUD();
        return legalCustomerCRUD.searchCustomer(customerNumber, companyName, registrationDate, economicID);

    }
    public ArrayList<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        RealCustomerCRUD realCustomerCRUD = new RealCustomerCRUD();
        return realCustomerCRUD.searchCustomer(customerNumber,firstName,lastName,fatherName,dateOfBirth , internationalID);

    }
    public void deleteCustomer (int id) throws SQLException {
         LegalCustomerCRUD.deleteCustomer(id);
    }

}
