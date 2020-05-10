/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dao.ShippingInfoDAO;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.NumberHelper;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@WebServlet(name = "DetailRequestOrder", urlPatterns = {"/detail-request-oder"})
public class DetailRequestOrder extends HttpServlet {

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
            ShippingInfoDAO dAO = new ShippingInfoDAO();
            int countOrderRequest = dAO.getSizeOrder(1);
            int countOrderConfirm = dAO.getSizeOrder(2);
            int countOrderShipped = dAO.getSizeOrder(3);
            request.setAttribute("countOrderRequest", countOrderRequest);
            request.setAttribute("countOrderConfirm", countOrderConfirm);
            request.setAttribute("countOrderShipped", countOrderShipped);
            int status = Integer.valueOf(request.getParameter("status"));
            List<Order> allByStatus = dAO.getAllByStatus(status);
            request.setAttribute("listOrder", allByStatus);
            request.getRequestDispatcher("detailRequestOrder.jsp").forward(request, response);
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
        ShippingInfoDAO dAO = new ShippingInfoDAO();
        int status = NumberHelper.getID(request.getParameter("status"));
        int id = NumberHelper.getID(request.getParameter("id"));
        boolean x = false;
        if (status == -1) {
            response.sendRedirect("error.jsp");
        } else if (status <= 1) {

             x =  dAO.updateStatusOrder(id, 2);
        } else if (status == 2) {
              x =  dAO.updateStatusOrder(id, 3);
            // response.getWriter().print(id + " va " + status);
        }
        response.sendRedirect("admin");

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
