/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import dao.AccountDAO;
import dao.AddressDAO;
import dao.ProvinctDAO;
import entity.Account;
import entity.District;
import entity.Provinct;
import entity.Town;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@WebServlet(name = "registerController", urlPatterns = {"/register"})
public class registerController extends HttpServlet {

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
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String user = request.getParameter("user");
            String phone = request.getParameter("phone");
            String provinc = request.getParameter("provin");
            Provinct p = new ProvinctDAO().getOne(Integer.valueOf(provinc));
            String district = request.getParameter("district");
            District d = new AddressDAO().getOneDistrict(district);
            String town = request.getParameter("town");
            Town t = new AddressDAO().getOneTown(town);
            String address = p.getName() + "-" + d.getName() + "-" + t.getName();
            Account a = new AccountDAO().getOne(email, pass);
            if (a != null) {
                response.getWriter().print("0");
            } else {
                Account account = Account.builder()
                        .email(email)
                        .address(address)
                        .name(user)
                        .phone(phone)
                        .password(pass)
                        .admin(false)
                        .build();
                boolean check = new AccountDAO().insertAccount(account);
                response.getWriter().print("1");

            }
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
        processRequest(request, response);
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
