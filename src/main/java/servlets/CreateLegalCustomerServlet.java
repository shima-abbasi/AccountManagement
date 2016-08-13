package servlets;

import dataAccess.entity.LegalCustomer;
import exceptions.NoValidatedCustomer;
import logic.CustomerLogic;
import logic.LegalCustomerLogic;
import output.OutputGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CreateLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CustomerLogic customerLogic = new LegalCustomerLogic();
        String companyName = request.getParameter("companyName");
        String registrationDate = request.getParameter("registrationDate");
        String economicID = request.getParameter("economicID");
        String output = "";
        try {
            LegalCustomer legalCustomer = customerLogic.setCustomerInfo(companyName, registrationDate, economicID);
            output = OutputGenerator.generateLegalCustomer(legalCustomer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoValidatedCustomer noValidatedCustomer) {
            output = OutputGenerator.generateMessage("مشتری مورد نظر مورد قبول نمیباشد" , "search_legal_customer.html");
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }
}

