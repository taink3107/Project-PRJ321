/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import dao.ProductDao;
import entity.Product;
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
@WebServlet(name = "ProcessPaging", urlPatterns = {"/processP"})
public class ProcessPaging extends HttpServlet {

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
            int id = NumberHelper.getID((request.getParameter("id")));
            int pageIndex = NumberHelper.getID(request.getParameter("pageIndex"));
            int pageSize = 4;
            List<Product> list = new ProductDao().getAllForPaging(id, pageSize, pageIndex);
            if (list.isEmpty() || list == null) {
                response.getWriter().println("1");
            } else {
                for (Product p : list) {
                    response.getWriter().println(
                            "<div class=\"col-12 col-sm-12 col-md-6 col-lg-3 item mb-3 product\" data-price=\""+p.getPrice()+"\">  \n"
                            + "                                <div class=\"imageNew\">\n"
                            + "                                    <a href=\"detail?proId=" + p.getId() + "\"><img src=\"img/male/" + p.getImage() + "\" alt=\"Avatar\" class=\"image\"></a>\n"
                            + "                                    <div class=\"overlay\" id=\"overlayText\">\n"
                            + "                                        <div class=\"text\" ><a href=\"detail?proId=" + p.getId() + "\">Xem chi tiết</a></div>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"descriptionImage\">\n"
                            + "                                    <div class=\"nameOfItem\">" + p.getName() + "</div>\n"
                            + "                                    <br>\n"
                            + "                                    <div class=\"priceOfItem\">\n"
                            + p.getDisplayPrice()
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                            </div>"
                    );
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
