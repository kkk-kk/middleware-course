/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bms.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bms.ejb.Book;
/**
 *
 * @author kkk
 */
@WebServlet(name = "ListBooks", urlPatterns = {"/ListBooks"})
public class ListBooks extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        Book book = new Book();
        ArrayList<Book> books;
        if(title == null){
            books = book.queryAllRecords();
        }
        else{
            books = book.queryByTitle(title);
        }
        ServletContext context = getServletContext();
        Integer times = (Integer)context.getAttribute("times");
        if(times == null){
            times = 1;           
        }
        else
            times++;
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String html1 = "<html  class=\"no-js\">\n" +
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
"                </div></aside>\n" +
"        <div class=\"page-wrapper\">\n" +
"            <!-- Header -->\n" +
"            <header class=\"main-header \" id=\"header\">\n" +
"                <nav class=\"navbar navbar-static-top navbar-expand-lg\">\n" +
"                    <!-- Sidebar toggle button -->\n" +
"                    <button id=\"sidebar-toggler\" class=\"sidebar-toggle\">\n" +
"                        <span class=\"sr-only\">打印图书清单</span>\n" +
"                    </button>\n" +
"                    <div class=\"navbar-right \">\n" +
"                        <ul class=\"nav navbar-nav\">\n" +
"                            <!-- Github Link Button -->\n" +
"                            <!-- User Account -->\n" +
"                        </ul>\n" +
"                    </div>\n" +
"                </nav>\n" +
"            </header>\n" +
"            <div class=\"content-wrapper\">\n" +
"                <div class=\"content\">\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-lg-12\">\n" +
"                            <div class=\"card card-default\">\n" +
"                                <div class=\"card-header card-header-border-bottom\">\n" +
"                                    <h2>图书清单</h2>\n<div class='offset-1'>" +
                    
"                                     <form action='ListBooks' class='form-inline'><div class='input-group mb-2 mr-sm-2'>\n" +
"                                      <input id=\"title\" type=\"text\" class=\"form-control\" name=\"title\" placeholder=\"输入书名查询...\">\n" +
"                                    </div>\n" +
"                                      <button  type=\"submit\" class=\"btn btn-outline-primary mb-2\">查询</button>\n" +
"                                    </form></div>" + 
"                                </div>\n" +
"                                <div class=\"card-body\">\n" +
"                                    <table class=\"table\">\n" +
"                                        <thead>\n" +
"                                        <tr>\n" +
"                                            <th scope=\"col\">ISBN</th>\n" +
"                                            <th scope=\"col\">书名</th>\n" +
"                                            <th scope=\"col\">作者</th>\n" +
"                                            <th scope=\"col\">出版社</th>\n" +
"                                            <th scope=\"col\">价格</th>\n" +
"                                            <th scope=\"col\"></th>\n" +
"                                        </tr>\n" +
"                                        </thead>";
            out.println(html1);
            out.println("<tbody>");
            for(Book b:books){
                out.println("<tr>");
                out.println("<td scope='row'>" + b.getISBN() + "</td>");
                out.println("<td>" + b.getTitle() + "</td>");
                out.println("<td>" + b.getAuthor() + "</td>");
                out.println("<td>" + b.getPublisher() + "</td>");
                out.println("<td>" + b.getPrice() + "</td>");
                out.println("<td><a href='DeleteBook?isbn=" + b.getISBN() + "'><button type='button' class='mdi btn btn-sm btn-outline-primary'>删除</button>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table></div></div></div></div></div></div></div></div>\n" +
"  </body>\n" +
"</html>");
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
