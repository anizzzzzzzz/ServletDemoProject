package com.onlineproduct.servlet;

import com.onlineproduct.entity.Product;
import com.onlineproduct.utils.DBUtils;
import com.onlineproduct.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/productList"})
public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductListServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection= MyUtils.getStoredConnection(req);

        String errorString =null;
        List<Product> products=null;

        try{
            products= DBUtils.findAllProducts(connection);
        }
        catch (SQLException e){
            e.printStackTrace();
            errorString=e.getMessage();
        }

        // Store info in request attribute, before forward to views
        req.setAttribute("errorString",errorString);
        req.setAttribute("products",products);

        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/productListView.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
