package servlets;

import dataAccess.entity.LegalCustomer;
import exceptions.NoValidatedCustomerException;
import exceptions.RequiredFieldException;
import logic.CustomerLogic;
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
        String companyName = request.getParameter("companyName");
        String registrationDate = request.getParameter("registrationDate");
        String economicID = request.getParameter("economicID");
        String output = "";
        try {
            LegalCustomer legalCustomer = CustomerLogic.setCustomerInfo(companyName, registrationDate, economicID);
            output = OutputGenerator.generateLegalCustomer(legalCustomer);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoValidatedCustomerException noValidatedCustomer) {
            output = OutputGenerator.generateMessage("مشتری با کد اقتصادی وارد شده در سیستم موجوداست"  , "create_legal_customer.html");
        } catch (RequiredFieldException e) {
           output = OutputGenerator.generateMessage( "لطفا اطلاعات ضروری را تکمیل کنید" , "create_legal_customer.html");
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }
}

