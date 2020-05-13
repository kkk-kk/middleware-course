/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bms.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
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
@WebServlet(name = "RegisterBook", urlPatterns = {"/RegisterBook"})
public class RegisterBook extends HttpServlet {

    @Resource(mappedName = "jms/bms")
    private Queue bms;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

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
        //request.setCharacterEncoding("utf-8");
        String ISBN,title,author,publisher;
        float price;
        ISBN = request.getParameter("isbn");
        title = request.getParameter("title");
        //String t = new String(title.getBytes("iso-8859-1"),"utf-8");
        author = request.getParameter("author");
        publisher = request.getParameter("publisher");
        price = Float.parseFloat(request.getParameter("price"));
        if((ISBN != null) && (title != null)){
            try{
                Book book = new Book();
                book.setISBN(ISBN);
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setPrice(price);
                ObjectMessage message = context.createObjectMessage();
                message.setObject(book);
                this.sendJMSMessageToBms(message);
                response.sendRedirect("ListBooks");
            } catch (JMSException e){
                e.printStackTrace();
            }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterBook</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8");
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
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
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



    private void sendJMSMessageToBms(ObjectMessage messageData) {
        context.createProducer().send(bms, messageData);
    }
}