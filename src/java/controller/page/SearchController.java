/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.page;

import dao.ProductDao;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ProductService;
import util.NumberHelper;

/**
 *
 * @author NGUYEN KHANH TAI
 */
@WebServlet(name = "SearchController", urlPatterns = {"/search-controller"})
public class SearchController extends HttpServlet {

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
            String raw_textSearch = request.getParameter("textSearch");
            String raw_typeSearch = request.getParameter("typeSearch");
            List<Category> categorys = new CategoryService().getAll();
            request.setAttribute("listCategory", categorys);
            List<Product> products = null;
            if (raw_typeSearch.isEmpty() || raw_typeSearch == null) {
                products = new ProductService().getAll();
            }
            if (raw_typeSearch.equalsIgnoreCase("name")) {
                products = new ProductDao().searchByName(raw_textSearch);
                if (products.isEmpty()) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if (raw_typeSearch.equalsIgnoreCase("price")) {
                int price = NumberHelper.getID(raw_textSearch);
                if (price == -1) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {

                }
            } else if (raw_typeSearch.equalsIgnoreCase("size")) {
                products = new ProductDao().searchBySize(raw_textSearch);
                if (products.isEmpty()) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            request.setAttribute("products", products);
            request.getRequestDispatcher("search.jsp").forward(request, response);
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
