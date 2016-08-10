package servlets;

import logic.CustomerLogic;
import logic.LegalCustomerLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CustomerLogic customerLogic= new LegalCustomerLogic();
        String companyName = request.getParameter("companyName");
        String registrationDate = request.getParameter("registrationDate");
        String economicID = request.getParameter("economicID");
        try {
            customerLogic.setCustomerInfo(companyName, registrationDate, economicID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

