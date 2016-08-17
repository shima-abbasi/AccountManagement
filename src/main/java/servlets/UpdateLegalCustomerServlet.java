package servlets;

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
public class UpdateLegalCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String companyName = request.getParameter("companyName");
        String registrationDate = request.getParameter("registrationDate");
        String economicID = request.getParameter("economicID");
        String output="";
        try {
            CustomerLogic.updateCustomer(id , companyName, registrationDate, economicID);
            output = OutputGenerator.generateMessage("اطلاعات مشتری با موفقیت اصلاح شد.","search_legal_customer.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);

    }
}
