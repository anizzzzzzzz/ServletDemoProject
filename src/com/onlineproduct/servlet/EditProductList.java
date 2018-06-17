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

@WebServlet(urlPatterns = {"/editProduct"})
public class EditProductList extends HttpServlet {
    private static final long serialVersionUID=1L;

    public EditProductList(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection= MyUtils.getStoredConnection(req);

        String pCode=(String)req.getParameter("pCode");
        String errorString=null;
        Product product=null;

        try {
             product= DBUtils.findOneProduct(connection,pCode);
        }
        catch (SQLException e){
            e.printStackTrace();
            errorString=e.getMessage();
        }

        if(errorString !=null && product==null){
            resp.sendRedirect(req.getContextPath()+"/productList");
            return;
        }

        req.setAttribute("errorString",errorString);
        req.setAttribute("product",product);

        RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/updateProductView.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection= MyUtils.getStoredConnection(req);

        String pCode=req.getParameter("pCode");
        String name=req.getParameter("name");
        float price=Float.parseFloat(req.getParameter("price"));

        Product product=new Product(pCode,name,price);

        String errorString=null;

        try {
            DBUtils.updateProduct(connection,product);
        }
        catch (SQLException e){
            e.printStackTrace();
            errorString=e.getMessage();
        }

        req.setAttribute("errorString",errorString);
        req.setAttribute("product",product);

        if(errorString!=null){
            RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/WEB-INF/views/editProduct");
            dispatcher.forward(req,resp);
        }
        else{
            resp.sendRedirect(req.getContextPath()+"/productList");
        }
    }
}
