package logic;

import dataAccess.LegalCustomerCRUD;
import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class LegalCustomerLogic extends CustomerLogic
{
    static Connection connection = DBConnection.getDBConnection();

    public static boolean validateUniqueCustomer(String economicID) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM legal_customer WHERE economic_id=" + economicID);

        if (resultSet.next())
            return false;
        return true;
    }
    public  static boolean checkField( String companyName, String registrationDate, String economicID) throws RequiredFieldException, SQLException, NoValidatedCustomerException {
        if (companyName.trim().length() == 0 | registrationDate.trim().length() == 0 | economicID.trim().length() == 0)
           throw  new RequiredFieldException();
        if(!LegalCustomerLogic.validateUniqueCustomer(economicID))
            throw  new NoValidatedCustomerException();
        return true;
    }
    public static LegalCustomer retrieveCustomer(int id) throws SQLException {
        return LegalCustomerCRUD.retrieveCustomer(id);
    }
    public static void deleteCustomer(int id) throws SQLException {
        LegalCustomerCRUD.deleteCustomer(id);
    }
}
