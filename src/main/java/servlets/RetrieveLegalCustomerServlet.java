package servlets;

import dataAccess.entity.LegalCustomer;
import logic.CustomerLogic;
import output.OutputGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */
public class RetrieveLegalCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerLogic customerLogic = new CustomerLogic();

        int id = Integer.parseInt(request.getParameter("id"));
        String output="";
        try {
            LegalCustomer legalCustomer = customerLogic.retrieveCustomer(id);
            output = OutputGenerator.generateUpdatePage(legalCustomer);

        } catch (SQLException e) {
            output = OutputGenerator.generateMessage("Problem in connection to the database", "search_legal_customer.html");
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);

    }
}
