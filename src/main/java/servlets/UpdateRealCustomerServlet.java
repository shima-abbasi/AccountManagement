package servlets;

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

/**
 * Created by Dotin school 5 on 8/15/2016.
 */
public class UpdateRealCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest theRequest, HttpServletResponse theResponse)
            throws ServletException, IOException {
        int id = Integer.parseInt(theRequest.getParameter("id"));
        String firstName = theRequest.getParameter("firstName");
        String lastName = theRequest.getParameter("lastName");
        String fatherName = theRequest.getParameter("fatherName");
        String dateOfBirth = theRequest.getParameter("dateOfBirth");
        String internationalID = theRequest.getParameter("internationalID");
        String output="";
        try {
            CustomerLogic.updateCustomer(id ,firstName, lastName, fatherName , dateOfBirth , internationalID);
            output = OutputGenerator.generateMessage("اطلاعات مشتری با موفقیت اصلاح شد.","search_legal_customer.html");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RequiredFieldException e) {
            output = OutputGenerator.generateMessage("لطفا اطلاعات ضروری را تکمیل کنید","search_real_customer.html");
        }
        theResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter out = theResponse.getWriter();
        out.println(output);

    }
}
