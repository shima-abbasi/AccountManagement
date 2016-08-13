package servlets;

import dataAccess.entity.LegalCustomer;
import logic.CustomerLogic;
import logic.LegalCustomerLogic;
import output.OutputGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dotin school 5 on 8/6/2016.
 */
public class SearchLegalCustomerServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            CustomerLogic customerLogic = new LegalCustomerLogic();
            String customerNumber = request.getParameter("customerNumber");
            String companyName = request.getParameter("companyName");
            String registrationDate = request.getParameter("registrationDate");
            String economicID = request.getParameter("economicID");
            String output="";
            try {
                ArrayList<LegalCustomer> legalCustomers = customerLogic.se(customerNumber, companyName, registrationDate, economicID);
                if(legalCustomers.size() == 0){
                  //  output = OutputGenerator.generateMessage("مشتری با اطلاعات وارد شده وجود ندارد.");
                }else {
                    output = OutputGenerator.generateLegalCustomerResult(legalCustomers);
                }
            }catch (SQLException e){
               // output = OutputGenerator.generateMessage(e.getMessage());
            }

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(output);

        }
}
