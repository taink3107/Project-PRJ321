/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.page;

import dao.ShippingInfoDAO;
import entity.Account;
import entity.Cart;
import entity.Order;
import entity.ShippingInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@WebServlet(name = "ProcessOrder", urlPatterns = {"/check-out-order"})
public class ProcessOrder extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String phone = request.getParameter("tel");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            ShippingInfo info = ShippingInfo.builder()
                    .name(name)
                    .phone(phone)
                    .email(email)
                    .address(address)
                    .build();
            int infoId = new ShippingInfoDAO().addShipingInfoReturnId(info);
            if (infoId == 0) {
                response.sendRedirect("error.jsp");
            } else {
                HttpSession session = request.getSession();
                List<Cart> carts = (List<Cart>) session.getAttribute("listCart");
                Account account = (Account) session.getAttribute("acc");
                double totalPrice = 0;
                for (Cart c : carts) {
                    totalPrice += (c.getProductPrice() * c.getQuantity());
                }
                Order order = Order.builder()
                        .ship_infoId(infoId)
                        .note(note)
                        .total_price(totalPrice)
                        .id_account(String.valueOf(account.getId()))
                        .status(1)
                        .build();
                int orderID = new ShippingInfoDAO().addOrderReturnId(order);
                if (orderID == 0) {
                    //xóa order
                    new ShippingInfoDAO().removeOrder(infoId);
                    response.sendRedirect("error.jsp");
                } else {
                    boolean check = new ShippingInfoDAO().addListCart(carts, orderID);
                    if (check) {
                        session.removeAttribute("listCart");
                        response.sendRedirect("sucess.jsp");
                    } else {
                        // xóa Order và shipping
                        new ShippingInfoDAO().removeShipping(infoId);
                        new ShippingInfoDAO().removeOrder(infoId);
                        response.sendRedirect("error.jsp");
                    }
                }
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
