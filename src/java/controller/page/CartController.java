/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.page;

import dao.ProductDao;
import dao.SizeDao;
import entity.Cart;
import entity.Category;
import entity.Product;
import entity.Size;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            int id = NumberHelper.getID(request.getParameter("proId"));
            int size = NumberHelper.getID(request.getParameter("size"));
            if (size == -1) {
                size = 1;
            }
            Product product = new ProductDao().getOne(id);
            Size sz = new SizeDao().getOne(size);
            HttpSession session = request.getSession();
            Cart c = Cart.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .productImg(product.getImage())
                    .productQuantity(product.getQuantity())
                    .size(sz)
                    .quantity(1)
                    .productPrice(product.getPrice())
                    .totalPrice(product.getPrice())
                    .build();
            List<Cart> carts = (List<Cart>) session.getAttribute("listCart");
            if (carts == null || carts.isEmpty()) {
                carts = new ArrayList<>();
                carts.add(c);
            } else {
                boolean isExisted = true;
                for (Cart cc : carts) {
                    if (cc.getProductId() == id && cc.getSize().getProId() == size) {
                        cc.setQuantity(cc.getQuantity() + 1);
                        isExisted = false;
                    }
                }
                if (isExisted) {
                    carts.add(c);
                }
            }
            session.setAttribute("listCart", carts);
            List<Category> categorys = new CategoryService().getAll();
            request.setAttribute("listCategory", categorys);
            response.sendRedirect("detail?proId=" + id);
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
