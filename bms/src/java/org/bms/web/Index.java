/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bms.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kkk
 */
@WebServlet(name = "Index", urlPatterns = {"/Index"})
public class Index extends HttpServlet {

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
                ServletContext context = getServletContext();
        Integer times = (Integer)context.getAttribute("times");
        if(times == null){
            times = 1;           
        }
        else
            times++;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String html = "<html  class=\"no-js\">\n" +
"<head>\n" +
"  <meta charset=\"utf-8\" />\n" +
"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
"  <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,500|Poppins:400,500,600,700|Roboto:400,500\" rel=\"stylesheet\"/>\n" +
"  <link href=\"https://cdn.materialdesignicons.com/3.0.39/css/materialdesignicons.min.css\" rel=\"stylesheet\" /> \n" +
"  <link id=\"sleek-css\" rel=\"stylesheet\" href=\"assets/css/sleek.css\" />\n" +
"  <link href=\"assets/favicon.png\" rel=\"shortcut icon\" />\n" +
"</head>\n" +
"  <body class=\"sidebar-fixed sidebar-dark header-light header-fixed\" id=\"body\">\n" +
"    <div class=\"mobile-sticky-body-overlay\"></div>\n" +
"    <div class=\"wrapper\">\n" +
"        <aside class=\"left-sidebar bg-sidebar\">\n" +
"          <div id=\"sidebar\" class=\"sidebar sidebar-with-footer\">\n" +
"            <div class=\"app-brand\">\n" +
"              <a href=\"Index\">\n" +
"                <svg class=\"brand-icon\" preserveAspectRatio=\"xMidYMid\" width=\"30\" height=\"33\" viewBox=\"0 0 30 33\">\n" +
"                  <g fill=\"none\" fill-rule=\"evenodd\">\n" +
"                    <path class=\"logo-fill-blue\" fill=\"#7DBCFF\" d=\"M0 4v25l8 4V0zM22 4v25l8 4V0z\" />\n" +
"                    <path class=\"logo-fill-white\" fill=\"#FFF\" d=\"M11 4v25l8 4V0z\" />\n" +
"                  </g></svg>\n" +
"                <span class=\"brand-name\">图书管理系统</span>\n" +
"              </a></div>\n" +
"            <div class=\"sidebar-scrollbar\">\n" +
"              <ul class=\"nav sidebar-inner\" id=\"sidebar-menu\">\n" +
"                  <li  class=\"has-sub\" >\n" +
"                    <a class=\"sidenav-item-link\" href=\"AddBook\" aria-expanded=\"false\" aria-controls=\"dashboard\">\n" +
"                      <i class=\"mdi mdi-book-open-page-variant\"></i>\n" +
"                      <span class=\"nav-text\">录入</span> <b class=\"caret\"></b>\n" +
"                    </a></li>\n" +
"                  <li  class=\"has-sub\" >\n" +
"                    <a class=\"sidenav-item-link\" href=\"ListBooks\" >\n" +
"                      <i class=\"mdi mdi-library-books\"></i>\n" +
"                      <span class=\"nav-text\">打印清单</span> <b class=\"caret\"></b>\n" +
"                    </a></li></ul></div>\n" +
"            <hr class=\"separator\" /><div class=\"sidebar-footer\">\n" +
"              <div class=\"sidebar-footer-content\">\n" +
"                <h6 class=\"text-uppercase\">\n" +
"                  访问次数： <span>" +times+ "</span>\n" +
"                </h6></div></div>" +
"        </div></aside>\n" +
"\n" +
"    </div>\n" +
"  </body>\n" +
"</html>";
            out.println(html);
            context.setAttribute("times",times);
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
