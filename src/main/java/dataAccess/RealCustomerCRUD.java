package dataAccess;

import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomer;

import java.sql.*;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerCRUD extends CustomerCRUD {
    Connection connection = DBConnection.getDBConnection();

    public void createRealCustomer(RealCustomer realCustomer) throws SQLException {
        String customerNumber = realCustomer.getCustomerNumber();
        String firstName = realCustomer.getFirstName();
        String lastName = realCustomer.getLastName();
        String fatherName = realCustomer.getFatherName();
        String dateOfBirth = realCustomer.getDateOfBirth();
        String internationalID = realCustomer.getInternationalID();

        PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO customer (customer_number) VALUE (?)");
        preparedStatement1.setString(1, customerNumber);
        preparedStatement1.executeUpdate();

        ResultSet resultSet = preparedStatement1.executeQuery("SELECT id FROM customer");
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO real_customer (id,first_name, last_name_father_name,date_of_bith, international_id) VALUES (?,?,?,?,?)");
        preparedStatement2.setInt(1, id);
        preparedStatement2.setString(2, firstName);
        preparedStatement2.setString(3, lastName);
        preparedStatement2.setString(4, fatherName);
        preparedStatement2.setString(5, internationalID);
        preparedStatement2.executeUpdate();
        System.out.println("insert was successful");

    }

    public boolean validateCustomer(String internationalID) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM real_customer WHERE international_id=" + internationalID);
            if (resultSet.getString(5)==internationalID) {
                return false;
            }
        return true;
    }
}
