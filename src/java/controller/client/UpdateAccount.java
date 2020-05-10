/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.NumberHelper;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@WebServlet(name = "UpdateAccount", urlPatterns = {"/update-account"})
public class UpdateAccount extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

        }
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
        request.setCharacterEncoding("UTF-8");
        int flag = NumberHelper.getID(request.getParameter("flag"));
        int id = NumberHelper.getID(request.getParameter("id"));
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        int nRespon = 0;
        AccountDAO dAO = new AccountDAO();
        if (flag == -1) {
            response.sendRedirect("error.jsp");
        } else if (flag == 1) {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            account.setAddress(address);
            account.setEmail(email);
            account.setPhone(phone);
            account.setName(name);
            boolean check = dAO.update(account, id);
            if (check) {
                response.getWriter().println("1");

            } else {
                response.getWriter().print("2");

            }
        } else if (flag == 2) {
            String pass = request.getParameter("oldPass");
            if (account.getPassword().equals(pass)) {
                String newPass = request.getParameter("newPassword");
                account.setPassword(newPass);
                boolean check = dAO.update(account, id);
                if (check) {
                    response.getWriter().print("1");
                } else {
                    response.getWriter().print("2");
                }
            } else {
                response.getWriter().print("3");
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
