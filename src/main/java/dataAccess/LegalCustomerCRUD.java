package dataAccess;

import dataAccess.connectionutil.DBConnection;
import dataAccess.entity.LegalCustomer;
import exceptions.NoValidatedCustomer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/7/2016.
 */
public class LegalCustomerCRUD extends CustomerCRUD {
    Connection connection = DBConnection.getDBConnection();

    public boolean validateCustomer(String economicID) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM legal_customer WHERE economic_id=" + economicID);

        if (resultSet.next()) {
            return false;
        }
        return true;
    }

    public void createLegalCustomer(LegalCustomer legalCustomer) throws SQLException {
        String customerNumber = legalCustomer.getCustomerNumber();
        String companyName = legalCustomer.getCompanyName();
        String registrationDate = legalCustomer.getRegistrationDate();
        String economicID = legalCustomer.getEconomicID();

        PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO customer (customer_number) VALUE (?)");
        preparedStatement1.setString(1, customerNumber);
        preparedStatement1.executeUpdate();

        ResultSet resultSet = preparedStatement1.executeQuery("SELECT id FROM customer");
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO legal_customer (id , company_name ,registration_date ,economic_id) VALUES (?,?,?,?)");
        preparedStatement2.setInt(1, id);
        preparedStatement2.setString(2, companyName);
        preparedStatement2.setString(3, registrationDate);
        preparedStatement2.setString(4, economicID);
        preparedStatement2.executeUpdate();
        System.out.println("Legal customer inserted");


    }
//    public ArrayList searchCustomer ()
//    {
//
//
//    }

}
