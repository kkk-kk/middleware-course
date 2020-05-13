/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bms.ejb;

import java.io.Serializable;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kk
 */
@Entity
public class Book implements Serializable {
    
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.bms.web.Book[ id=" + id + " ]";
    }
    
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private float price;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    private final String Driver="com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String password = "123456";
    private final String url="jdbc:mysql://localhost:3306/mid?characterEncoding=UTF-8";
    public void insertToDB(){
        PreparedStatement stmt = null;
        String insert="INSERT INTO mid.book (isbn, title, author, publisher, price) \n" +
"	VALUES (?, ?, ?, ?, ?)";
        try {
            
            Class.forName(this.Driver).newInstance();
            Connection conn = DriverManager.getConnection(url,user,password);
            if(conn != null)System.out.println("Connection established!");
            else System.out.println("Connection failed!");
            stmt = conn.prepareStatement(insert);
            stmt.setString(1,ISBN);
            stmt.setString(2,title);
            stmt.setString(3, author);
            stmt.setString(4, publisher);
            stmt.setFloat(5, price);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Book(String i,String t,String a,String p,float pr){
        this.ISBN = i;
        this.title = t;
        this.author = a;
        this.publisher = p;
        this.price = pr;
    }
    public Book(){
        
    }
    public ArrayList<Book> queryAllRecords() {
        String i,t,a,p;
        float pr;
        ArrayList<Book> books = new ArrayList(); 
        try {
            String query = "select * from mid.book";
            Class.forName(Driver).newInstance();
            Connection conn = DriverManager.getConnection(url, user, password);
            if(conn!=null) System.out.println("Connection established!");
        else System.out.println("Connection failed!");
        Statement st=conn.createStatement();
        ResultSet ret=st.executeQuery(query);
        while(ret.next())
        {
            i=ret.getString(1);t=ret.getString(2);a=ret.getString(3);p = ret.getString(4);
            pr=ret.getFloat(5);
            Book bk = new Book(i,t,a,p,pr);
            books.add(bk);
        }
        st.close();
        conn.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return books;
    }

    public ArrayList<Book> queryByTitle(String title){
        ArrayList<Book> books = new ArrayList();
        String query = "select * from mid.book where title like ?;";
        String i,t,a,p;
        float pr;
        PreparedStatement stmt = null;
         try {
            Class.forName(Driver).newInstance();
            Connection conn = DriverManager.getConnection(url, user, password);
            if(conn!=null) System.out.println("Connection established!");
            else System.out.println("Connection failed!");
            stmt = conn.prepareStatement(query);
            stmt.setString(1,"%" + title + "%");
            ResultSet ret = stmt.executeQuery();
            while(ret.next())
            {
                i=ret.getString(1);t=ret.getString(2);a=ret.getString(3);p = ret.getString(4);
                pr=ret.getFloat(5);
                Book bk = new Book(i,t,a,p,pr);
                books.add(bk);
               System.out.println(i);
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }
    
    public void deleteByISBN(String isbn){
        String delete = "delete from mid.book where isbn=?";
        PreparedStatement stmt = null;
        try {
            Class.forName(Driver).newInstance();
            Connection conn = DriverManager.getConnection(url, user, password);
            if(conn!=null) System.out.println("Connection established!");
            else System.out.println("Connection failed!");
            stmt = conn.prepareStatement(delete);
            stmt.setString(1,isbn);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
