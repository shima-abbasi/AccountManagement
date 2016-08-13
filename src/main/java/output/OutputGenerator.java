package output;

import dataAccess.entity.LegalCustomer;
import dataAccess.entity.RealCustomer;

/**
 * Created by Dotin school 5 on 8/13/2016.
 */
public class OutputGenerator {
    public static String generateLegalCustomer(LegalCustomer legalCustomer) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=fa>");
        stringBuilder.append("<head>");
        stringBuilder.append("    <meta charset=UTF-8>");
        stringBuilder.append("    <link href=css/style.css rel=stylesheet>");
        stringBuilder.append("    <title>اطلاعات مشتری جدید</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=box-in>");
        stringBuilder.append("  <br>");
        stringBuilder.append("    <h1>اطلاعات زیر برای مشتری جدید ثبت شد</h1>");
        stringBuilder.append("  </br>");
        stringBuilder.append("<table align=\"center\">");
        stringBuilder.append(   "<tr>");
        stringBuilder.append(       "<td> نام شرکت </td>");
        stringBuilder.append(       "<td>" + legalCustomer.getCompanyName() + "</td>");
        stringBuilder.append("     </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("      <td> تاریخ ثبت</td>");
        stringBuilder.append("          <td>" + legalCustomer.getRegistrationDate() + "</td>");
        stringBuilder.append("     </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("         <td>کد اقتصادی</td>");
        stringBuilder.append("          <td>" + legalCustomer.getEconomicID() + "</td>");
        stringBuilder.append("      </tr>");
        stringBuilder.append("      <tr>");
        stringBuilder.append("            <td>شماره مشتری</td>");
        stringBuilder.append("            <td>" + legalCustomer.getCustomerNumber() + "</td>");
        stringBuilder.append("       </tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("    <form action=\"legal_customer_management.html\">");
        stringBuilder.append("         <button class=\"button\" type=\"submit\"> بازگشت به صفحه قبل</button>");
        stringBuilder.append("    </form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }
    public static String generateRealCustomer(RealCustomer realCustomer) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=fa>");
        stringBuilder.append("<head>");
        stringBuilder.append("    <meta charset=UTF-8>");
        stringBuilder.append("    <link href=css/style.css rel=stylesheet>");
        stringBuilder.append("    <title>اطلاعات مشتری جدید</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=box-in>");
        stringBuilder.append("  <br>");
        stringBuilder.append("    <h1>اطلاعات زیر برای مشتری جدید ثبت شد</h1>");
        stringBuilder.append("  </br>");
        stringBuilder.append("<table align=\"center\">");
        stringBuilder.append(   "<tr>");
        stringBuilder.append(       "<td> نام و نام خوانوادگی مشتری </td>");
        stringBuilder.append(       "<td>" + realCustomer.getFirstName() + realCustomer.getLastName() + "</td>");
        stringBuilder.append("     </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("      <td>نام پدر</td>");
        stringBuilder.append("          <td>" + realCustomer.getFatherName() + "</td>");
        stringBuilder.append("     </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("         <td>تاریخ تولد</td>");
        stringBuilder.append("          <td>" + realCustomer.getDateOfBirth() + "</td>");
        stringBuilder.append("      </tr>");
        stringBuilder.append("     <tr>");
        stringBuilder.append("         <td>کد ملی</td>");
        stringBuilder.append("          <td>" + realCustomer.getInternationalID() + "</td>");
        stringBuilder.append("      </tr>");
        stringBuilder.append("      <tr>");
        stringBuilder.append("            <td>شماره مشتری</td>");
        stringBuilder.append("            <td>" + realCustomer.getInternationalID() + "</td>");
        stringBuilder.append("       </tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("    <form action=\"real_customer_management.html\">");
        stringBuilder.append("         <button class=\"button\" type=\"submit\"> بازگشت به صفحه قبل</button>");
        stringBuilder.append("    </form>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return stringBuilder.toString();
    }
}
