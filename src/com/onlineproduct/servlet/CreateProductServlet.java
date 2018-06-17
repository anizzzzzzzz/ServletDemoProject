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

@WebServlet(urlPatterns = {"/createProduct"})
public class CreateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateProductServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection= MyUtils.getStoredConnection(req);

        String code=req.getParameter("pCode");
        String name=req.getParameter("name");
        float price= Float.parseFloat(req.getParameter("price"));

        Product product=new Product(code,name,price);

        String errorString =null;
        String regex="\\w+";

        if(code == null || !code.matches(regex)){
            errorString = "Product Code Invalid";
        }

        if(errorString ==null){
            try {
                DBUtils.insertProduct(connection,product);
            }
            catch (SQLException e){
                e.printStackTrace();
                errorString = e.getMessage();
            }
            finally {

            }
        }
        req.setAttribute("errorString", errorString);
        req.setAttribute("product", product);

        if(errorString!=null && errorString.length()>0){
            RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(req,resp);
        }
        else {
            resp.sendRedirect(req.getContextPath()+"/productList");
        }

    }
}
