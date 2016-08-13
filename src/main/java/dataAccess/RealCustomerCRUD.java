package dataAccess;

import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;
import exceptions.NoValidatedCustomer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerCRUD extends CustomerCRUD {
    Connection connection = DBConnection.getDBConnection();

    public boolean validateCustomer(String internationalID) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM real_customer WHERE international_id=" + internationalID);
        if (resultSet.next()) {
            return false;
        }
        return true;
    }

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
        PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO real_customer (id , first_name , last_name ,father_name , date_of_birth ,international_id) VALUES (?,?,?,?,?,?)");
        preparedStatement2.setInt(1, id);
        preparedStatement2.setString(2, firstName);
        preparedStatement2.setString(3, lastName);
        preparedStatement2.setString(4, fatherName);
        preparedStatement2.setString(5, dateOfBirth);
        preparedStatement2.setString(6, internationalID);
        preparedStatement2.executeUpdate();
        System.out.println("Real customer number created");

    }

    public ArrayList<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        PreparedStatement preparedStatement = generatePreparedStatement(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);
        ArrayList<RealCustomer> realCustomerResult = new ArrayList<RealCustomer>();
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()) {
            RealCustomer realCustomer = new RealCustomer();
            realCustomer.setId(resultset.getInt("id"));
            realCustomer.setCustomerNumber(resultset.getString("customer_number"));
            realCustomer.setFirstName(resultset.getString("first_name"));
            realCustomer.setLastName(resultset.getString("last_name"));
            realCustomer.setFatherName(resultset.getString("father_name"));
            realCustomer.setDateOfBirth(resultset.getString("date_of_birth"));
            realCustomer.setInternationalID(resultset.getString("international_id"));
            realCustomerResult.add(realCustomer);
        }
        return realCustomerResult;
    }

    public PreparedStatement generatePreparedStatement(String customerNumber, String firstName, String
            lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        if (customerNumber != "" | firstName != "" | lastName != "" | fatherName != "" | dateOfBirth != "" | internationalID != "") {
            stringBuilder.append("SELECT * FROM (real_customer JOIN customer ON customer.id = real_customer.id) WHERE ");
            if (customerNumber != "" && customerNumber != null) {
                stringBuilder.append(" customer_number =" + customerNumber + " AND");
            }
            if (firstName != "") {
                stringBuilder.append(" first_name =" + firstName + " AND");
            }
            if (lastName != "") {
                stringBuilder.append(" last_name =" + lastName + " AND");
            }
            if (fatherName != "") {
                stringBuilder.append(" father_name =" + fatherName + " AND");
            }
            if (dateOfBirth != "") {
                stringBuilder.append(" date_of_birth =" + dateOfBirth + " AND");
            }
            if (internationalID != "") {
                stringBuilder.append(" international_id =" + internationalID);
            }
        } else
            stringBuilder.append("SELECT * FROM real_customer JOIN customer ON customer.id = real_customer.id");
        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        return preparedStatement;
    }
}
