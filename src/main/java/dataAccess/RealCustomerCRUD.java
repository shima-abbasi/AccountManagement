package dataAccess;

import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class RealCustomerCRUD extends CustomerCRUD {
    static Connection connection = DBConnection.getDBConnection();

    public static void createRealCustomer(RealCustomer realCustomer) throws SQLException {
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
        System.out.println("Real customer inserted");

    }

    public static ArrayList<RealCustomer> searchCustomer(String customerNumber, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        PreparedStatement preparedStatement = generatePreparedStatement(customerNumber, firstName, lastName, fatherName, dateOfBirth, internationalID);
        ResultSet resultset = preparedStatement.executeQuery();
        ArrayList<RealCustomer> realCustomerResult = new ArrayList<RealCustomer>();
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

    public static PreparedStatement generatePreparedStatement(String customerNumber, String firstName, String
            lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        if (customerNumber!=""| firstName != "" | lastName != "" | fatherName != "" | dateOfBirth != "" | internationalID != "") {
            stringBuilder.append("SELECT * FROM account.real_customer rc JOIN account.customer c ON c.id = rc.id WHERE");
            int count = 0;
            if (customerNumber != "") {
                stringBuilder.append(" c.customer_number ='" + customerNumber + "'");
                count++;
            }
            if (firstName != "") {
                if (count == 1)
                    stringBuilder.append(" AND rc.first_name ='" + firstName + "'");
                else stringBuilder.append(" rc.first_name ='" + firstName + "'");
                count++;
            }
            if (lastName != "") {
                if (count == 2)
                    stringBuilder.append(" AND rc.last_name ='" + lastName + "'");
                else stringBuilder.append(" rc.last_name ='" + lastName + "'");
                count++;
            }
            if (fatherName != "") {
                if (count == 3)
                    stringBuilder.append(" AND rc.father_name ='" + fatherName + "'");
                else stringBuilder.append(" rc.father_name ='" + fatherName + "'");
                count++;
            }
            if (dateOfBirth != "") {
                if (count == 4)
                    stringBuilder.append(" AND rc.date_of_birth ='" + dateOfBirth + "'");
                else stringBuilder.append(" rc.date_of_birth ='" + dateOfBirth + "'");

                count++;
            }
            if (internationalID != "") {
                if (count == 5)
                    stringBuilder.append("AND rc.international_id ='" + internationalID + "'");
                else stringBuilder.append(" rc.international_id ='" + internationalID + "'");

            }
        } else
            stringBuilder.append("SELECT * FROM account.real_customer rc JOIN account.customer c ON c.id = rc.id");
        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        return preparedStatement;
    }

    public static RealCustomer retrieveCustomer(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account.real_customer rc JOIN account.customer c ON rc.id = c.id WHERE  c.id =?");
        preparedStatement.setInt(1, id);
        ResultSet results = preparedStatement.executeQuery();
        RealCustomer realCustomer = new RealCustomer();
        if (results.next()) {
            realCustomer.setId(results.getInt("id"));
            realCustomer.setCustomerNumber(results.getString("customer_number"));
            realCustomer.setFirstName(results.getString("first_name"));
            realCustomer.setLastName(results.getString("last_name"));
            realCustomer.setFatherName(results.getString("father_name"));
            realCustomer.setDateOfBirth(results.getString("date_of_birth"));
            realCustomer.setInternationalID(results.getString("international_id"));
        }
        return realCustomer;
    }

    public static void updateCustomer(int id, String firstName, String lastName, String fatherName, String dateOfBirth, String internationalID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE real_customer SET first_name = ? , last_name =  ? ,  father_name = ? , date_of_birth =? , international_id=?  WHERE id=?");
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, fatherName);
        preparedStatement.setString(4, dateOfBirth);
        preparedStatement.setString(5, internationalID);
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
    }

    public static void deleteCustomer(int id) throws SQLException {

        PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE  FROM real_customer WHERE id =?");
        preparedStatement1.setInt(1, id);
        preparedStatement1.executeUpdate();

        PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE  FROM customer WHERE id =?");
        preparedStatement2.setInt(1, id);
        preparedStatement2.executeUpdate();
    }
}
