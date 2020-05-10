/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import dao.BillDAO;
import entity.Account;
import entity.Bill;
import entity.BillElement;
import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.CategoryService;
import util.NumberHelper;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@WebServlet(name = "InforAccount", urlPatterns = {"/info-account"})
public class InforAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Category> categorys = new CategoryService().getAll();
        request.setAttribute("listCategory", categorys);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        List<Bill> bills = new BillDAO().getAllBillById(account.getId());
        request.setAttribute("bills", bills);
        request.getRequestDispatcher("infoAccount.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int billId = NumberHelper.getID(request.getParameter("billId"));
        if (billId != -1) {
            List<BillElement> elements = new BillDAO().getAllByBillId(billId);
            for (BillElement element : elements) {
                response.getWriter().print("<tr>\n"
                        + "                        <td> <img src=\"img/male/" + element.getImage() + "\" style=\"width: 80px;\"></td>\n"
                        + "                        <td>" + element.getName() + "</td>\n"
                        + "                        <td>" + element.getSize() + "</td>\n"
                        + "                        <td>" + element.getPrice() + "</td>\n"
                        + "                         <td>"+element.getQuantity() + "</td>\n"
                        + "                    </tr>");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
