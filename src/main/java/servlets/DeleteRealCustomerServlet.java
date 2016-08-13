package servlets;

import logic.CustomerLogic;
import logic.LegalCustomerLogic;
import logic.RealCustomerLogic;
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
public class DeleteRealCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CustomerLogic customerLogic = new RealCustomerLogic();
        int id = Integer.parseInt(request.getParameter("id"));
        String output = "";
        try {
            customerLogic.deleteCustomer(id);
        } catch (SQLException e) {
        output = OutputGenerator.generateMessage("مشتری مورد نظر حذف شد" , "search_real_customer.html");
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
    }
}
